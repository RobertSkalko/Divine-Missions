package com.robertx22.divine_missions.data_gen.builders;

import com.robertx22.divine_missions.data_gen.adders.PoolsAdder;
import com.robertx22.divine_missions.database.db_types.God;
import net.minecraft.util.Formatting;

import java.util.List;

public class GodBuilder {

    public static God of(String id, Formatting format, List<String> tasksPools, List<String> rewardPools) {

        God god = new God();
        god.format = format.getName();
        god.task_pools.addAll(tasksPools);
        god.task_pools.add(PoolsAdder.GENERAL_TASKS);

        god.reward_pools.addAll(rewardPools);
        god.reward_pools.add(PoolsAdder.GENERAL_REWARDS);

        god.id = id;

        god.addToSerializables();
        return god;
    }
}
