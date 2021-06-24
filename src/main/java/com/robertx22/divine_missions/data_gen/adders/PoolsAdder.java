package com.robertx22.divine_missions.data_gen.adders;

import com.robertx22.divine_missions.data_gen.builders.PoolBuilder;
import com.robertx22.divine_missions.database.db_types.Pool;
import com.robertx22.library_of_exile.registry.ExileRegistryInit;

import java.util.Arrays;

public class PoolsAdder implements ExileRegistryInit {

    public static String GENERAL_REWARDS = "general_rewards";
    public static String GENERAL_TASKS = "general_tasks";
    public static String HARVEST_TASKS = "harvest_tasks";
    public static String WISDOM_TASKS = "wisdom_tasks";
    public static String WAR_TASKS = "war_tasks";
    public static String OCEAN_TASKS = "ocean_tasks";

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
                TaskEntryAdder.GET_IRON,
                TaskEntryAdder.GET_GOLD,
                TaskEntryAdder.GET_LAPIS,

                TaskEntryAdder.KILL_SPIDER,
                TaskEntryAdder.KILL_ENDERMAN,
                TaskEntryAdder.KILL_ZOMBIE,
                TaskEntryAdder.KILL_WITHER
            )
        );

        PoolBuilder.of(HARVEST_TASKS, 1000, Pool.PoolType.TASKS, Pool.PickType.ALWAYS_INCLUDE,
            Arrays.asList(
                TaskEntryAdder.GET_BEETROOT,
                TaskEntryAdder.GET_COOKIES,
                TaskEntryAdder.GET_CARROTS,
                TaskEntryAdder.GET_HONEYCOMB,
                TaskEntryAdder.GET_POTATOES,
                TaskEntryAdder.GET_MELONS,
                TaskEntryAdder.GET_CAKE,
                TaskEntryAdder.GET_BREAD
            )
        );

        PoolBuilder.of(WISDOM_TASKS, 1000, Pool.PoolType.TASKS, Pool.PickType.ALWAYS_INCLUDE,
            Arrays.asList(
                TaskEntryAdder.GET_WRITABLE_BOOK,
                TaskEntryAdder.GET_PAPER,
                TaskEntryAdder.GET_INK,
                TaskEntryAdder.GET_BOOKS
            )
        );

        PoolBuilder.of(OCEAN_TASKS, 1000, Pool.PoolType.TASKS, Pool.PickType.ALWAYS_INCLUDE,
            Arrays.asList(
                TaskEntryAdder.GET_COD,
                TaskEntryAdder.GET_PUFFER,
                TaskEntryAdder.GET_TROPICAL,
                TaskEntryAdder.GET_SALMON,
                TaskEntryAdder.GET_NAUT_SHELL
            )
        );
        PoolBuilder.of(WAR_TASKS, 1000, Pool.PoolType.TASKS, Pool.PickType.ALWAYS_INCLUDE,
            Arrays.asList(
                TaskEntryAdder.GET_TOTEM_OF_UDNYING,
                TaskEntryAdder.KILL_MANY,
                TaskEntryAdder.KILL_GHAST,
                TaskEntryAdder.KILL_WITHER_SKELE,
                TaskEntryAdder.KILL_PILLAGER
            )
        );

    }
}
