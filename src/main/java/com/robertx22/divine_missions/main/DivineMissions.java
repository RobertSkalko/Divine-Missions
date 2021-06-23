package com.robertx22.divine_missions.main;

import net.minecraft.util.Identifier;

public class DivineMissions {

    public static boolean RUN_DEV_TOOLS() {
        return true;
    }

    public static String MODID = "divine_missions";

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
