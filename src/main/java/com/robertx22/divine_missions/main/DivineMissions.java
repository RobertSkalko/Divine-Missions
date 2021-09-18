package com.robertx22.divine_missions.main;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class DivineMissions {

    public static boolean RUN_DEV_TOOLS() {
        return false;
    }

    public static String MODID = "divine_missions";

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }

    public static TranslationTextComponent ofTranslation(String path) {
        return new TranslationTextComponent(MODID + "." + path);
    }

    public static ItemGroup CreativeTab = new ItemGroup("creative_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.INSTANCE.SHRINE);
        }
    };

}
