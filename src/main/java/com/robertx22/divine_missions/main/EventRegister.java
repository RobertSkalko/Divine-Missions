package com.robertx22.divine_missions.main;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.divine_missions.commands.CompleteAllMissions;
import com.robertx22.divine_missions.commands.GiveMission;
import com.robertx22.divine_missions.commands.RefreshMissions;
import com.robertx22.divine_missions.components.PlayerMissions;
import com.robertx22.divine_missions.events.OnChestLooted;
import com.robertx22.divine_missions.events.OnMobKill;
import com.robertx22.library_of_exile.events.base.EventConsumer;
import com.robertx22.library_of_exile.events.base.ExileEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.command.ServerCommandSource;

public class EventRegister {

    static int ticks = 0;

    public static void reg() {
        ServerTickEvents.END_SERVER_TICK.register(x -> {
            ticks++;

            if (ticks % 20 == 0) {
                x.getPlayerManager()
                    .getPlayerList()
                    .forEach(p -> {
                        PlayerMissions.KEY.get(p).data.onTick(20, p);
                    });
            }
        });

        ExileEvents.CHECK_IF_DEV_TOOLS_SHOULD_RUN.register(new EventConsumer<ExileEvents.OnCheckIsDevToolsRunning>() {
            @Override
            public void accept(ExileEvents.OnCheckIsDevToolsRunning event) {
                event.run = DivineMissions.RUN_DEV_TOOLS();
            }
        });

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            CommandDispatcher<ServerCommandSource> dispatcher = server.getCommandManager()
                .getDispatcher();
            GiveMission.register(dispatcher);
            CompleteAllMissions.register(dispatcher);
            RefreshMissions.register(dispatcher);
        });

        ExileEvents.MOB_DEATH.register(new OnMobKill());
        ExileEvents.ON_CHEST_LOOTED.register(new OnChestLooted());
    }
}
