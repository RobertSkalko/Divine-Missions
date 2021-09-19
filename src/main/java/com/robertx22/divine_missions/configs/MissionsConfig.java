package com.robertx22.divine_missions.configs;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class MissionsConfig {

    public static final ForgeConfigSpec commonSpec;
    public static final MissionsConfig COMMON;

    static {
        final Pair<MissionsConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(MissionsConfig::new);
        commonSpec = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    MissionsConfig(ForgeConfigSpec.Builder b) {
        b.comment("Settings")
            .push("general");

        MINUTES_OF_COOLDOWN_TO_REFRESH_MISSIONS = b
            .comment("Minutes it takes to get a new mission")
            .defineInRange("gain_mission_cooldown_minutes", 20, 1, 100000);
        MISSION_REFRESH_COOLDOWN_VARIATION = b
            .comment("0 means every x minutes while 0.5 means every x-x/2 to x+x/2 minutes, (random time variation)")
            .defineInRange("cd_refresh_time_variation", 0.5D, 0D, 1D);

        b.pop();
    }

    public ForgeConfigSpec.IntValue MINUTES_OF_COOLDOWN_TO_REFRESH_MISSIONS;
    public ForgeConfigSpec.DoubleValue MISSION_REFRESH_COOLDOWN_VARIATION;

    public static MissionsConfig get() {
        return COMMON;
    }

}
