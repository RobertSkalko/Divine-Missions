package com.robertx22.divine_missions.commands;

import com.google.common.base.Preconditions;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.robertx22.divine_missions.components.PlayerReputation;
import com.robertx22.divine_missions.database.MissionsDB;
import com.robertx22.divine_missions.database.db_types.God;
import com.robertx22.divine_missions.main.DivineMissions;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class GiveReputation {

    public static void register(CommandDispatcher<ServerCommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(DivineMissions.MODID)
                .then(literal("give_reputation").requires(e -> e.hasPermissionLevel(2))
                    .requires(e -> e.hasPermissionLevel(2))
                    .then(argument("target", EntityArgumentType.player())
                        .then(argument("god", StringArgumentType.word())
                            .then(argument("amount", IntegerArgumentType.integer())
                                .executes(e -> {
                                    return execute(
                                        EntityArgumentType.getPlayer(e, "target"),
                                        StringArgumentType.getString(e, "god"),
                                        IntegerArgumentType.getInteger(e, "amount"));
                                }))))));
    }

    private static int execute(PlayerEntity player, String godid, int amount) {

        God god = MissionsDB.Gods()
            .get(godid);

        Preconditions.checkNotNull(god);

        PlayerReputation.KEY.get(player)
            .addReputation(amount, god);

        return 0;
    }
}