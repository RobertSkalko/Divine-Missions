package com.robertx22.divine_missions.database.reward_types;

import com.robertx22.divine_missions.database.RewardTypeIds;
import com.robertx22.divine_missions.database.db_types.RewardType;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.RewardData;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class LootTableRewardType extends RewardType {

    public LootTableRewardType() {
        this.id = RewardTypeIds.LOOT_TABLE;
    }

    @Override
    public void giveReward(PlayerEntity player, RewardData data) {
        Identifier loottableId = new Identifier(data.getReward().data);

        LootContext lootContext = new LootContext.Builder((ServerWorld) player.world)
            .parameter(LootContextParameters.THIS_ENTITY, player)
            .parameter(LootContextParameters.ORIGIN, player.getPos())
            .parameter(LootContextParameters.TOOL, ItemStack.EMPTY)
            .parameter(LootContextParameters.BLOCK_STATE, Blocks.AIR.getDefaultState())
            .build(LootContextTypes.BLOCK);
        ServerWorld serverWorld = lootContext.getWorld();
        LootTable lootTable = serverWorld.getServer()
            .getLootManager()
            .getTable(loottableId);

        List<ItemStack> drops = lootTable.generateLoot(lootContext);

        if (drops.isEmpty()) {
            System.out.println(loottableId.toString() + " loot table dropped ZERO items!");
        }

        drops.forEach(x -> player.inventory.offerOrDrop(player.world, x));
    }

    @Override
    public MutableText getTranslatable(RewardData data) {
        return new LiteralText(data.count + "x ").append(DivineMissions.ofTranslation("loot_table." + data.id))
            .formatted(Formatting.LIGHT_PURPLE);
    }
}

