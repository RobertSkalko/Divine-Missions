package com.robertx22.divine_missions.util;

import com.robertx22.divine_missions.gui.MissionsScreen;
import net.minecraft.client.MinecraftClient;

public class ClientOnly {

    public static void openMissionsScreen() {
        MinecraftClient.getInstance()
            .openScreen(new MissionsScreen());
    }
}
