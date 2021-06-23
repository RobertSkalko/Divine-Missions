package com.robertx22.divine_missions.main;

import com.robertx22.divine_missions.configs.MissionsConfig;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (screen) -> {
            return AutoConfig.getConfigScreen(MissionsConfig.class, screen)
                .get();
        };
    }

    @Override
    public String getModId() {
        return "divine_missions";
    }
}
