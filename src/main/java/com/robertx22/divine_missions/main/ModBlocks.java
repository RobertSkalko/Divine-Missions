package com.robertx22.divine_missions.main;

import com.robertx22.divine_missions.block.ShrineBlock;
import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static ModBlocks INSTANCE;

    public Block SHRINE = Registry.register(Registry.BLOCK, DivineMissions.id("shrine"), new ShrineBlock());
}
