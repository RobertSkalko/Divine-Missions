package com.robertx22.divine_missions.item;

import com.robertx22.divine_missions.components.PlayerMissionCap;
import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.mission_gen.MissionUtil;
import com.robertx22.divine_missions.saving.MissionItemData;
import com.robertx22.divine_missions.util.ClientOnly;
import com.robertx22.library_of_exile.utils.SoundUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;

import java.util.List;

public class MissionItem extends Item {

    public MissionItem() {
        super(new Item.Properties().stacksTo(1)
            .tab(DivineMissions.CreativeTab));

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
                stack.shrink(1);
            }

        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity.tickCount % 20 == 0) {
            try {
                if (entity instanceof ServerPlayerEntity) {
                    if (!MissionItemData.SAVER.has(stack)) {
                        MissionItemData data = MissionUtil.createRandom((PlayerEntity) entity);
                        MissionItemData.SAVER.saveTo(stack, data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            tick(stack);
        }
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack stack = user.getItemInHand(hand);

        if (world.isClientSide) {
            return ActionResult.pass(stack);
        }

        try {

            MissionItemData data = MissionItemData.SAVER.loadFrom(stack);
            data.spendItems(user);
            MissionItemData.SAVER.saveTo(stack, data);

            if (data.canFinish(user)) {
                try {
                    data.giveRewards(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                stack.shrink(1);

                SoundUtils.ding(world, user.blockPosition());
            } else {
                SoundUtils.playSound(user, SoundEvents.VILLAGER_NO, 1, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ActionResult.pass(stack);

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag context) {

        try {

            MissionItemData data = MissionItemData.SAVER.loadFrom(stack);

            if (data != null) {
                tooltip.clear();

                IFormattableTextComponent name = data.getRarity()
                    .getTranslated()
                    .append(" ")
                    .append(new TranslationTextComponent("divine_missions.mission"))
                    .withStyle(data.getRarity()
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

                tooltip.add(new StringTextComponent(""));

                tooltip.add(new TranslationTextComponent(DivineMissions.MODID + ".required").append(":")
                    .withStyle(TextFormatting.GOLD));

                data.tasks.forEach(x -> {
                    tooltip.add(x.getTranslated(ClientOnly.getPlayer()));
                });
                tooltip.add(new StringTextComponent(""));

                tooltip.add(new TranslationTextComponent(DivineMissions.MODID + ".rewards").append(":")
                    .withStyle(TextFormatting.GOLD));

                data.rewards.forEach(x -> {
                    tooltip.add(x.getTranslated());
                });

                tooltip.add(new StringTextComponent(""));

                tooltip.add(new TranslationTextComponent(DivineMissions.MODID + ".aspect").append(": ")
                    .append(data.getGod()
                        .getTranslatable())
                    .withStyle(data.getGod()
                        .getFormat()));

                if (data.canFinishTooltip(ClientOnly.getPlayer())) {
                    tooltip.add(new StringTextComponent(""));
                    tooltip.add(DivineMissions.ofTranslation("click_to_turn_in")
                        .withStyle(TextFormatting.BLUE));
                }

                if (ModList.get()
                    .isLoaded("mmorpg")) {
                    if (data.tasks.stream()
                        .anyMatch(x -> x.getTaskType().id.equals(TaskTypeIds.ANY_MOB_KILL))) {
                        if (data.tasks.stream()
                            .allMatch(x -> x.curr == 0)) {
                            if (PlayerMissionCap.get(ClientOnly.getPlayer())
                                .getReputationLevel(data.getGod()).rank == 0) {
                                tooltip.add(DivineMissions.ofTranslation("aoe_level_range_tip")
                                    .withStyle(TextFormatting.BLUE));
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
