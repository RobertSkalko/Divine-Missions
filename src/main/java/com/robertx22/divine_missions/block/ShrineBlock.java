package com.robertx22.divine_missions.block;

import com.robertx22.divine_missions.util.ClientOnly;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ShrineBlock extends Block {

    public ShrineBlock() {
        super(AbstractBlock.Properties.of(Material.STONE)
            .noOcclusion()
            .lightLevel(x -> 15));
    }

    @Override
    @Deprecated
    public List<ItemStack> getDrops(BlockState blockstate, LootContext.Builder context) {
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        items.add(new ItemStack(this));
        return items;
    }

    @Override
    @Deprecated
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player,
                                Hand hand, BlockRayTraceResult ray) {
        if (world.isClientSide) {
            ClientOnly.openMissionsScreen();
            return ActionResultType.CONSUME;
        }

        return ActionResultType.CONSUME;
    }
}
