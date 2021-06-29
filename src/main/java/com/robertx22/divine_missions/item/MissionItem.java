package com.robertx22.divine_missions.item;

import com.robertx22.divine_missions.components.PlayerReputation;
import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.mission_gen.MissionUtil;
import com.robertx22.divine_missions.saving.MissionItemData;
import com.robertx22.divine_missions.util.ClientOnly;
import com.robertx22.library_of_exile.utils.SoundUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MissionItem extends Item {

    public MissionItem() {
        super(new Settings().maxCount(1)
            .group(DivineMissions.CreativeTab));

    }

    public static String TICKS = "ticks";

    public static void tick(ItemStack stack) {
        if (stack.hasTag() && stack.getTag()
            .contains(TICKS)) {

            int ticksleft = stack.getTag()
                .getInt(TICKS) - 20;

            stack.getTag()
                .putInt(TICKS, ticksleft);

            if (ticksleft < 0) {
                stack.decrement(1);
            }

        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity.age % 20 == 0) {

            if (entity instanceof ServerPlayerEntity) {
                if (!MissionItemData.SAVER.has(stack)) {
                    MissionItemData data = MissionUtil.createRandom((PlayerEntity) entity);
                    MissionItemData.SAVER.saveTo(stack, data);
                }
            }

            tick(stack);
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack stack = user.getStackInHand(hand);

        if (world.isClient) {
            return TypedActionResult.pass(stack);
        }

        try {

            MissionItemData data = MissionItemData.SAVER.loadFrom(stack);
            data.spendItems(user);
            MissionItemData.SAVER.saveTo(stack, data);

            if (data.canFinish(user)) {
                data.giveRewards(user);
                stack.decrement(1);

                SoundUtils.ding(world, user.getBlockPos());
            } else {
                SoundUtils.playSound(user, SoundEvents.ENTITY_VILLAGER_NO, 1, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TypedActionResult.pass(stack);

    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        try {

            MissionItemData data = MissionItemData.SAVER.loadFrom(stack);

            if (data != null) {
                tooltip.clear();

                MutableText name = data.getRarity()
                    .getTranslated()
                    .append(" ")
                    .append(new TranslatableText("divine_missions.mission"))
                    .formatted(data.getRarity()
                        .getFormat());

                int sec = stack.getTag()
                    .getInt(TICKS) / 20;

                int min = sec / 60;

                sec -= min * 60;

                String time = " (";

                if (min > 0) {
                    time += min + "m";
                }

                if (sec > 0) {
                    if (min > 0) {
                        time += " ";
                    }
                    time += sec + "s";
                }

                if (sec > 0 || min > 0) {
                    name.append(time + ")");
                }

                tooltip.add(name);

                tooltip.add(new LiteralText(""));

                tooltip.add(new TranslatableText(DivineMissions.MODID + ".required").append(":")
                    .formatted(Formatting.GOLD));

                data.tasks.forEach(x -> {
                    tooltip.add(x.getTranslated(ClientOnly.getPlayer()));
                });
                tooltip.add(new LiteralText(""));

                tooltip.add(new TranslatableText(DivineMissions.MODID + ".rewards").append(":")
                    .formatted(Formatting.GOLD));

                data.rewards.forEach(x -> {
                    tooltip.add(x.getTranslated());
                });

                tooltip.add(new LiteralText(""));

                tooltip.add(new TranslatableText(DivineMissions.MODID + ".aspect").append(": ")
                    .append(data.getGod()
                        .getTranslatable())
                    .formatted(data.getGod()
                        .getFormat()));

                if (data.canFinishTooltip(ClientOnly.getPlayer())) {
                    tooltip.add(new LiteralText(""));
                    tooltip.add(DivineMissions.ofTranslation("click_to_turn_in")
                        .formatted(Formatting.BLUE));
                }

                if (FabricLoader.getInstance()
                    .isModLoaded("mmorpg")) {
                    if (data.tasks.stream()
                        .anyMatch(x -> x.getTaskType().id.equals(TaskTypeIds.ANY_MOB_KILL))) {
                        if (data.tasks.stream()
                            .allMatch(x -> x.curr == 0)) {
                            if (PlayerReputation.KEY.get(ClientOnly.getPlayer())
                                .getReputationLevel(data.getGod()).rank == 0) {
                                tooltip.add(DivineMissions.ofTranslation("aoe_level_range_tip")
                                    .formatted(Formatting.BLUE));
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
