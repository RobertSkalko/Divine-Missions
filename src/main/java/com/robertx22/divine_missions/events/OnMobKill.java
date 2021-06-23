package com.robertx22.divine_missions.events;

import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.saving.MissionItemData;
import com.robertx22.divine_missions.saving.TaskData;
import com.robertx22.divine_missions.util.MissionUtil;
import com.robertx22.library_of_exile.events.base.EventConsumer;
import com.robertx22.library_of_exile.events.base.ExileEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

public class OnMobKill extends EventConsumer<ExileEvents.OnMobDeath> {

    @Override
    public void accept(ExileEvents.OnMobDeath event) {
        if (event.killer instanceof PlayerEntity) {

            String mobId = Registry.ENTITY_TYPE.getId(event.mob.getType())
                .toString();

            for (ItemStack stack : MissionUtil.getCurrentMissions((PlayerEntity) event.killer)) {

                MissionItemData data = MissionItemData.SAVER.loadFrom(stack);

                if (data != null) {

                    for (TaskData task : data.tasks) {

                        if (task.isDone((PlayerEntity) event.killer)) {
                            continue;
                        }

                        if (task.getTaskType()
                            .GUID()
                            .equals(TaskTypeIds.SPECIFIC_MOB_KILL)) {

                            if (task.getTaskEntry().data.equals(mobId)) {
                                task.increaseProgress();
                                MissionItemData.SAVER.saveTo(stack, data);
                                return;
                            }
                        }
                        if (task.getTaskType()
                            .GUID()
                            .equals(TaskTypeIds.ANY_MOB_KILL)) {
                            task.increaseProgress();
                            MissionItemData.SAVER.saveTo(stack, data);
                            return;
                        }
                    }
                }
            }

        }
    }
}
