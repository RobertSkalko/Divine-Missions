package com.robertx22.divine_missions.data_gen.adders;

import com.robertx22.divine_missions.data_gen.builders.MissionRarityBuilder;
import com.robertx22.library_of_exile.registry.ExileRegistryInit;
import net.minecraft.util.text.TextFormatting;

public class RarityAdders implements ExileRegistryInit {

    @Override
    public void registerAll() {

        MissionRarityBuilder.of("common", 0, 2500, TextFormatting.GRAY, 1F, 1F, 1, 2);
        MissionRarityBuilder.of("uncommon", 1, 1000, TextFormatting.GREEN, 1.1F, 1.25F, 2, 2);
        MissionRarityBuilder.of("rare", 2, 250, TextFormatting.YELLOW, 1.2F, 1.5F, 2, 3);
        MissionRarityBuilder.of("epic", 3, 100, TextFormatting.LIGHT_PURPLE, 1.3F, 2F, 3, 4);

    }
}
