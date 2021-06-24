package com.robertx22.divine_missions.configs;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;

@me.sargunvohra.mcmods.autoconfig1u.annotation.Config(name = "divine_missions")
public class MissionsConfig implements ConfigData {

    public int MINUTES_OF_COOLDOWN_TO_REFRESH_MISSIONS = 60;

    public boolean TEST = false;

    public static MissionsConfig get() {
        return AutoConfig.getConfigHolder(MissionsConfig.class)
            .getConfig();
    }

}
