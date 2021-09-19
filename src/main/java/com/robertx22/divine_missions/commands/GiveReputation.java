package com.robertx22.divine_missions.commands;

import com.google.common.base.Preconditions;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.robertx22.divine_missions.components.PlayerMissionCap;
import com.robertx22.divine_missions.database.MissionsDB;
import com.robertx22.divine_missions.database.db_types.God;
import com.robertx22.divine_missions.main.DivineMissions;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class GiveReputation {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(DivineMissions.MODID)
                .then(literal("give_reputation").requires(e -> e.hasPermission(2))
                    .requires(e -> e.hasPermission(2))
                    .then(argument("target", EntityArgument.player())
                        .then(argument("god", StringArgumentType.word())
                            .then(argument("amount", IntegerArgumentType.integer())
                                .executes(e -> {
                                    return execute(
                                        EntityArgument.getPlayer(e, "target"),
                                        StringArgumentType.getString(e, "god"),
                                        IntegerArgumentType.getInteger(e, "amount"));
                                }))))));
    }

    private static int execute(PlayerEntity player, String godid, int amount) {

        God god = MissionsDB.Gods()
            .get(godid);

        Preconditions.checkNotNull(god);

        PlayerMissionCap.get(player)
            .addReputation(amount, god);

        return 0;
    }
}