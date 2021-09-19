package com.robertx22.divine_missions.main;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class ClientInit {

    public static void onInitializeClient() {

        RenderTypeLookup.setRenderLayer(ModBlocks.SHRINE.get(), RenderType.translucent());

    }
}
