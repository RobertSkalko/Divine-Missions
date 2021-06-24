package com.robertx22.divine_missions.main;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class DivineMissions {

    public static boolean RUN_DEV_TOOLS() {
        return true;
    }

    public static String MODID = "divine_missions";

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }

    public static ItemGroup CreativeTab = FabricItemGroupBuilder.build(
        DivineMissions.id("creative_tab"),
        () -> new ItemStack(ModItems.INSTANCE.SHRINE));

}
