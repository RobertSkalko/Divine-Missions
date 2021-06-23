package com.robertx22.divine_missions.main;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.divine_missions.commands.GiveMission;
import com.robertx22.divine_missions.configs.MissionsConfig;
import com.robertx22.divine_missions.data_gen.AddToSeriazables;
import com.robertx22.divine_missions.database.AddRegistryContainers;
import com.robertx22.divine_missions.database.RegistryTypes;
import com.robertx22.divine_missions.database.TaskTypesAdder;
import com.robertx22.divine_missions.events.OnMobKill;
import com.robertx22.library_of_exile.events.base.EventConsumer;
import com.robertx22.library_of_exile.events.base.ExileEvents;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;

public class CommonInit implements ModInitializer {

    @Override
    public void onInitialize() {

        ModItems.INSTANCE = new ModItems();

        ExileEvents.CHECK_IF_DEV_TOOLS_SHOULD_RUN.register(new EventConsumer<ExileEvents.OnCheckIsDevToolsRunning>() {
            @Override
            public void accept(ExileEvents.OnCheckIsDevToolsRunning event) {
                event.run = DivineMissions.RUN_DEV_TOOLS();
            }
        });

        ServerLifecycleEvents.SERVER_STARTED.register(new ServerLifecycleEvents.ServerStarted() {
            @Override
            public void onServerStarted(MinecraftServer server) {

                CommandDispatcher<ServerCommandSource> dispatcher = server.getCommandManager()
                    .getDispatcher();

                GiveMission.register(dispatcher);
            }
        });

        ExileEvents.MOB_DEATH.register(new OnMobKill());

        AutoConfig.register(MissionsConfig.class, JanksonConfigSerializer::new);

        AddRegistryContainers.addAll();
        RegistryTypes.init();

        new TaskTypesAdder().registerAll();

        if (DivineMissions.RUN_DEV_TOOLS()) {
            AddToSeriazables.addAll();
        }

        System.out.println("Divine Missions loaded.");
    }

}
