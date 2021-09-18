package com.robertx22.divine_missions.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.mission_gen.MissionUtil;
import com.robertx22.divine_missions.saving.MissionItemData;
import com.robertx22.divine_missions.saving.TaskData;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

public class CompleteAllMissions {

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {

        commandDispatcher.register(
            literal(DivineMissions.MODID)
                .then(literal("complete_all").requires(e -> e.hasPermission(2))
                    .requires(e -> e.hasPermission(2))
                    .then(argument("target", EntityArgument.player())
                        .executes(e -> execute(e.getSource(), EntityArgument.getPlayer(e, "target"))))));
    }

    private static int execute(CommandSource commandSource, PlayerEntity player) {

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