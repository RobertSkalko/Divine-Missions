package com.robertx22.divine_missions.database.reward_types;

import com.robertx22.divine_missions.database.RewardTypeIds;
import com.robertx22.divine_missions.database.db_types.RewardType;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.RewardData;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class LootTableRewardType extends RewardType {

    public LootTableRewardType() {
        super(RewardTypeIds.LOOT_TABLE);
    }

    @Override
    public void giveReward(PlayerEntity player, RewardData data) {
        ResourceLocation loottableId = new ResourceLocation(data.getReward().data);

        LootContext lootContext = new LootContext.Builder((ServerWorld) player.level)
            .withParameter(LootParameters.THIS_ENTITY, player)
            .withParameter(LootParameters.ORIGIN, player.position())
            .withParameter(LootParameters.TOOL, ItemStack.EMPTY)
            .withParameter(LootParameters.BLOCK_STATE, Blocks.AIR.defaultBlockState())
            .create(LootContextParamSets.BLOCK);
        ServerWorld serverWorld = lootContext.getLevel();
        LootTable lootTable = serverWorld.getServer()
            .getLootTables()
            .get(loottableId);

        List<ItemStack> drops = lootTable.getRandomItems(lootContext);

        if (drops.isEmpty()) {
            System.out.println(loottableId.toString() + " loot table dropped ZERO items!");
        }

        drops.forEach(x -> player.inventory.placeItemBackInInventory(player.level, x));
    }

    @Override
    public IFormattableTextComponent getTranslatable(RewardData data) {
        return new StringTextComponent(data.count + "x ").append(DivineMissions.ofTranslation("loot_table." + data.id))
            .withStyle(TextFormatting.LIGHT_PURPLE);
    }
}

