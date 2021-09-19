package com.robertx22.divine_missions.main;

import com.robertx22.divine_missions.configs.MissionsConfig;
import com.robertx22.divine_missions.db_init.RegistryInit;
import com.robertx22.divine_missions.packets.PickMissionPacket;
import com.robertx22.library_of_exile.main.Packets;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@Mod(DivineMissions.MODID)
public class CommonInit {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(DivineMissions.MODID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );

    public CommonInit() {

        DivineDeffered.registerDefferedAtStartOfModLoading();
        ModLoadingContext.get()
            .registerConfig(ModConfig.Type.COMMON, MissionsConfig.commonSpec);

        final IEventBus bus = FMLJavaModLoadingContext.get()
            .getModEventBus();

        bus.addListener(this::commonSetupEvent);
        bus.addListener(this::interMod);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            bus.addListener(this::clientSetup);
        });

        DivineEvents.reg();

        Packets.registerClientToServerPacket(NETWORK, new PickMissionPacket(0), 0);

        System.out.println("Divine Missions loaded.");
    }

    public void interMod(InterModProcessEvent event) {
        RegistryInit.init();
    }

    public void commonSetupEvent(FMLCommonSetupEvent event) {
        DivineCapabilities.reg();
    }

    public void clientSetup(final FMLClientSetupEvent event) {
        ClientInit.onInitializeClient();
    }

}
