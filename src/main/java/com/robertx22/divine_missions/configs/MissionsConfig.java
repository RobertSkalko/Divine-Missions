package com.robertx22.divine_missions.configs;

public class MissionsConfig {

    public int MINUTES_OF_COOLDOWN_TO_REFRESH_MISSIONS = 60;
    public float MISSION_REFRESH_COOLDOWN_VARIATION = 0.5F;

    public int MIN_MISSIONS_TO_PICK = 2;
    public int MAX_MISSIONS_TO_PICK = 4;

    public int MISSIONS_TO_GENERATE_EACH_TIME = 9;

    public static MissionsConfig get() {
        return new MissionsConfig(); // TODO
    }

}
