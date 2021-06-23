package com.robertx22.divine_missions.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.util.MissionUtil;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class GiveMission {

    public static void register(CommandDispatcher<ServerCommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(DivineMissions.MODID)
                .then(literal("give_random_mission").requires(e -> e.hasPermissionLevel(2))
                    .requires(e -> e.hasPermissionLevel(2))
                    .then(argument("target", EntityArgumentType.player())
                        .executes(e -> execute(e.getSource(), EntityArgumentType.getPlayer(e, "target"))))));
    }

    private static int execute(ServerCommandSource commandSource, PlayerEntity player) {

        player.giveItemStack(MissionUtil.createRandomMissionItem(player));

        return 0;
    }
}