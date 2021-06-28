package com.robertx22.divine_missions.util;

import com.robertx22.divine_missions.gui.MissionsScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public class ClientOnly {
    public static PlayerEntity getPlayer() {
        return MinecraftClient.getInstance().player;
    }

    public static void openMissionsScreen() {
        MinecraftClient.getInstance()
            .openScreen(new MissionsScreen());
    }
}
