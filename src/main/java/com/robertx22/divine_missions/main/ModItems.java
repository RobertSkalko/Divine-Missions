package com.robertx22.divine_missions.main;

import com.robertx22.divine_missions.item.MissionItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

    public static RegistryObject<Item> MISSION_ITEM = DivineDeffered.ITEMS.register("mission", () -> new MissionItem());
    public static RegistryObject<Item> SHRINE = DivineDeffered.ITEMS.register("shrine", () -> new BlockItem(ModBlocks.SHRINE.get(), new Item.Properties().tab(DivineMissions.CreativeTab)));

    public static void init() {

    }
}
