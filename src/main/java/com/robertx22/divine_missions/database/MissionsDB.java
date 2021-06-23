package com.robertx22.divine_missions.database;

import com.robertx22.divine_missions.database.db_types.*;
import com.robertx22.library_of_exile.registry.Database;
import com.robertx22.library_of_exile.registry.ExileRegistryContainer;

public class MissionsDB {

    public static ExileRegistryContainer<TaskType> TaskTypes() {
        return Database.getRegistry(RegistryTypes.TASK_TYPE);
    }

    public static ExileRegistryContainer<Reward> Rewards() {
        return Database.getRegistry(RegistryTypes.REWARD);
    }

    public static ExileRegistryContainer<ReputationLevel> ReputationLevels() {
        return Database.getRegistry(RegistryTypes.REPUTATION);
    }

    public static ExileRegistryContainer<God> Gods() {
        return Database.getRegistry(RegistryTypes.GOD);
    }

    public static ExileRegistryContainer<TaskEntry> TaskEntries() {
        return Database.getRegistry(RegistryTypes.TASK_ENTRY);
    }

    public static ExileRegistryContainer<MissionRarity> MissionRarities() {
        return Database.getRegistry(RegistryTypes.MISSION_RARITY);
    }

    public static ExileRegistryContainer<Pool> Pools() {
        return Database.getRegistry(RegistryTypes.POOL);
    }

}
