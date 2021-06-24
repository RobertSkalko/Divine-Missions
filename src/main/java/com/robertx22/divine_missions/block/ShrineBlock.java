package com.robertx22.divine_missions.block;

import com.robertx22.divine_missions.util.ClientOnly;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ShrineBlock extends Block {

    public ShrineBlock() {
        super(AbstractBlock.Settings.of(Material.STONE)
            .nonOpaque()
            .luminance(x -> 15));
    }

    @Override
    @Deprecated
    public List<ItemStack> getDroppedStacks(BlockState blockstate, LootContext.Builder context) {
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        items.add(new ItemStack(this));
        return items;
    }

    @Override
    @Deprecated
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                              Hand hand, BlockHitResult ray) {
        if (world.isClient) {
            ClientOnly.openMissionsScreen();
            return ActionResult.CONSUME;
        }

        return ActionResult.CONSUME;
    }
}
