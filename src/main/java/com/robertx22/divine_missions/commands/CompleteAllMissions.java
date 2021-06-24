package com.robertx22.divine_missions.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.MissionItemData;
import com.robertx22.divine_missions.saving.TaskData;
import com.robertx22.divine_missions.util.MissionUtil;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CompleteAllMissions {

    public static void register(CommandDispatcher<ServerCommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(DivineMissions.MODID)
                .then(literal("complete_all").requires(e -> e.hasPermissionLevel(2))
                    .requires(e -> e.hasPermissionLevel(2))
                    .then(argument("target", EntityArgumentType.player())
                        .executes(e -> execute(e.getSource(), EntityArgumentType.getPlayer(e, "target"))))));
    }

    private static int execute(ServerCommandSource commandSource, PlayerEntity player) {

        for (ItemStack stack : MissionUtil.getCurrentMissions(player)) {

            MissionItemData data = MissionItemData.SAVER.loadFrom(stack);

            if (data != null) {

                for (TaskData task : data.tasks) {
                    for (int i = 0; i < 100; i++) {
                        task.increaseProgress();
                    }
                }

                MissionItemData.SAVER.saveTo(stack, data);
            }
        }

        return 0;
    }
}