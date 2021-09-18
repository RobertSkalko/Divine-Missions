package com.robertx22.divine_missions.data_gen.adders;

import com.robertx22.divine_missions.data_gen.builders.ReputationBuilder;
import com.robertx22.library_of_exile.registry.ExileRegistryInit;
import net.minecraft.util.text.TextFormatting;

public class ReputationAdders implements ExileRegistryInit {

    @Override
    public void registerAll() {

        ReputationBuilder.of("hated", TextFormatting.DARK_RED, -3, -5000, 0.25F, 0);
        ReputationBuilder.of("hostile", TextFormatting.RED, -2, -2500, 0.5F, 0);
        ReputationBuilder.of("unfriendly", TextFormatting.GOLD, -1, -1000, 0.75F, 0);

        ReputationBuilder.of("neutral", TextFormatting.YELLOW, 0, 0, 1F, 0);
        ReputationBuilder.of("friendly", TextFormatting.GREEN, 1, 250, 1.1F, 10);
        ReputationBuilder.of("honored", TextFormatting.AQUA, 2, 1000, 1.25F, 25);
        ReputationBuilder.of("exalted", TextFormatting.LIGHT_PURPLE, 3, 2500, 1.5F, 35);
        ReputationBuilder.of("paragon", TextFormatting.DARK_PURPLE, 4, 10000, 2F, 50);

    }
}
