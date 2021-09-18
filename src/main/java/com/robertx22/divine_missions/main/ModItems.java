package com.robertx22.divine_missions.main;

import com.robertx22.divine_missions.item.MissionItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static ModItems INSTANCE;

    public Item MISSION_ITEM = Registry.register(Registry.ITEM, DivineMissions.id("mission"), new MissionItem());
    public Item SHRINE = Registry.register(Registry.ITEM, DivineMissions.id("shrine"), new BlockItem(ModBlocks.INSTANCE.SHRINE, new Item.Properties().tab(DivineMissions.CreativeTab)));

}
