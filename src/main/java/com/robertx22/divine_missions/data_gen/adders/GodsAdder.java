package com.robertx22.divine_missions.data_gen.adders;

import com.robertx22.divine_missions.data_gen.builders.GodBuilder;
import com.robertx22.library_of_exile.registry.ExileRegistryInit;
import net.minecraft.util.Formatting;

import java.util.Arrays;

public class GodsAdder implements ExileRegistryInit {

    @Override
    public void registerAll() {

        GodBuilder.of("ocean", Formatting.AQUA, Arrays.asList(PoolsAdder.OCEAN_TASKS), Arrays.asList());
        GodBuilder.of("forge", Formatting.GRAY, Arrays.asList(), Arrays.asList());
        GodBuilder.of("hunt", Formatting.DARK_GREEN, Arrays.asList(), Arrays.asList());
        GodBuilder.of("wisdom", Formatting.LIGHT_PURPLE, Arrays.asList(PoolsAdder.WISDOM_TASKS), Arrays.asList());
        GodBuilder.of("war", Formatting.RED, Arrays.asList(PoolsAdder.WAR_TASKS), Arrays.asList());
        GodBuilder.of("harvest", Formatting.YELLOW, Arrays.asList(PoolsAdder.HARVEST_TASKS), Arrays.asList());

    }
}
