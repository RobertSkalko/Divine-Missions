package com.robertx22.divine_missions.database.db_types;

import com.robertx22.divine_missions.database.RegistryTypes;
import com.robertx22.library_of_exile.registry.ExileRegistryType;
import com.robertx22.library_of_exile.registry.IAutoGson;
import com.robertx22.library_of_exile.registry.JsonExileRegistry;

public class ReputationLevel implements JsonExileRegistry<ReputationLevel>, IAutoGson<ReputationLevel> {
    public static ReputationLevel SERIALIZER = new ReputationLevel();

    public int weight = 1000;
    public String id = "";
    public int rep_needed = 0;
    public int rank = 0; // bigger the better
    public float weight_multi = 1F;
    public float chance_for_higher_rar = 0;

    @Override
    public ExileRegistryType getExileRegistryType() {
        return RegistryTypes.REPUTATION;
    }

    @Override
    public String GUID() {
        return id;
    }

    @Override
    public int Weight() {
        return weight;
    }

    @Override
    public Class<ReputationLevel> getClassForSerialization() {
        return ReputationLevel.class;
    }
}


