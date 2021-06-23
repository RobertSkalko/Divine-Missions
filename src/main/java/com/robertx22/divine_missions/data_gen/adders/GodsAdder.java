package com.robertx22.divine_missions.data_gen.adders;

import com.robertx22.divine_missions.data_gen.builders.GodBuilder;
import com.robertx22.library_of_exile.registry.ExileRegistryInit;
import net.minecraft.util.Formatting;

import java.util.Arrays;

public class GodsAdder implements ExileRegistryInit {

    @Override
    public void registerAll() {

        GodBuilder.of("sea", Formatting.AQUA, Arrays.asList(), Arrays.asList());
        GodBuilder.of("forge", Formatting.GRAY, Arrays.asList(), Arrays.asList());
        GodBuilder.of("hunt", Formatting.DARK_GREEN, Arrays.asList(), Arrays.asList());

    }
}
