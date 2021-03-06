package com.robertx22.divine_missions.events;

import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.mission_gen.MissionUtil;
import com.robertx22.library_of_exile.events.base.EventConsumer;
import com.robertx22.library_of_exile.events.base.ExileEvents;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.registry.Registry;

public class OnMobKill extends EventConsumer<ExileEvents.OnMobDeath> {

    @Override
    public void accept(ExileEvents.OnMobDeath event) {
        if (event.killer instanceof PlayerEntity) {

            if (event.mob instanceof MobEntity || event.mob instanceof IMob || !event.mob.getType()
                .getCategory()
                .isFriendly()) {

                String mobId = Registry.ENTITY_TYPE.getKey(event.mob.getType())
                    .toString();

                boolean validKill = ExileEvents.IS_KILLED_ENTITY_VALID.callEvents(new ExileEvents.IsEntityKilledValid(event.mob, event.killer)).isValid;
                // Age of Exile can call and say " you killed mob too low level, false! "

                MissionUtil.tryDoMissions((PlayerEntity) event.killer, x -> {
                    if (x.getTaskType()
                        .GUID()
                        .equals(TaskTypeIds.SPECIFIC_MOB_KILL)) {
                        if (x.getTaskEntry().data.equals(mobId)) {

                            x.increaseProgress();
                            return;
                        }
                    }
                });

                if (validKill) {
                    // only non specific mob kills can be penalized for killing different levels,
                    // because some mob types can only be of certain level ranges.
                    MissionUtil.tryDoMissions((PlayerEntity) event.killer, x -> {
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
    }
}
