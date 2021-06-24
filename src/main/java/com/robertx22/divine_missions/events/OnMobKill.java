package com.robertx22.divine_missions.events;

import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.util.MissionUtil;
import com.robertx22.library_of_exile.events.base.EventConsumer;
import com.robertx22.library_of_exile.events.base.ExileEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.registry.Registry;

public class OnMobKill extends EventConsumer<ExileEvents.OnMobDeath> {

    @Override
    public void accept(ExileEvents.OnMobDeath event) {
        if (event.killer instanceof PlayerEntity) {

            String mobId = Registry.ENTITY_TYPE.getId(event.mob.getType())
                .toString();

            MissionUtil.tryDoMissions((PlayerEntity) event.killer, x -> {
                if (x.getTaskType()
                    .GUID()
                    .equals(TaskTypeIds.SPECIFIC_MOB_KILL)) {
                    if (x.getTaskEntry().data.equals(mobId)) {
                        x.increaseProgress();
                        return;
                    }
                }
                if (x.getTaskType()
                    .GUID()
                    .equals(TaskTypeIds.ANY_MOB_KILL)) {
                    x.increaseProgress();
                    return;
                }
            });

        }
    }
}
