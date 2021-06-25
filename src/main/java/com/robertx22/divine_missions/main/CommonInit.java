package com.robertx22.divine_missions.main;

import com.robertx22.divine_missions.configs.MissionsConfig;
import com.robertx22.divine_missions.db_init.RegistryInit;
import com.robertx22.divine_missions.packets.PickMissionPacket;
import com.robertx22.library_of_exile.main.Packets;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class CommonInit implements ModInitializer {

    @Override
    public void onInitialize() {

        EventRegister.reg();

        ModBlocks.INSTANCE = new ModBlocks();
        ModItems.INSTANCE = new ModItems();

        Packets.registerClientToServerPacket(new PickMissionPacket(0));

        AutoConfig.register(MissionsConfig.class, JanksonConfigSerializer::new);

        RegistryInit.init();

        System.out.println("Divine Missions loaded.");
    }

}
