package com.robertx22.divine_missions.data_gen.adders;

import com.robertx22.divine_missions.data_gen.builders.PoolBuilder;
import com.robertx22.divine_missions.database.db_types.Pool;
import com.robertx22.library_of_exile.registry.ExileRegistryInit;

import java.util.Arrays;

public class PoolsAdder implements ExileRegistryInit {

    public static String GENERAL_REWARDS = "general_rewards";
    public static String GENERAL_TASKS = "general_tasks";

    @Override
    public void registerAll() {

        PoolBuilder.of(GENERAL_REWARDS, 1000, Pool.PoolType.REWARDS, Pool.PickType.PICK_ONE,
            Arrays.asList(
                RewardsAdder.DIAMONDS,
                RewardsAdder.IRON,
                RewardsAdder.GOLD
            )
        );

        PoolBuilder.of(GENERAL_TASKS, 1000, Pool.PoolType.TASKS, Pool.PickType.PICK_ONE,
            Arrays.asList(
                TaskEntryAdder.KILL_ANY,

                TaskEntryAdder.GET_DIAMONDS,
                TaskEntryAdder.GET_BOOKS,
                TaskEntryAdder.KILL_SPIDER,
                TaskEntryAdder.KILL_ENDERMAN,
                TaskEntryAdder.KILL_ZOMBIE,
                TaskEntryAdder.KILL_WITHER
            )
        );
    }
}
