package com.robertx22.divine_missions.util;

import com.robertx22.divine_missions.gui.MissionsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;

public class ClientOnly {
    public static PlayerEntity getPlayer() {
        return Minecraft.getInstance().player;
    }

    public static void openMissionsScreen() {
        Minecraft.getInstance()
            .setScreen(new MissionsScreen());
    }
}
