package com.robertx22.divine_missions.database.db_types;

import com.robertx22.divine_missions.database.MissionsDB;
import com.robertx22.divine_missions.database.RegistryTypes;
import com.robertx22.divine_missions.saving.RewardData;
import com.robertx22.library_of_exile.registry.ExileRegistryType;
import com.robertx22.library_of_exile.registry.IAutoGson;
import com.robertx22.library_of_exile.registry.JsonExileRegistry;
import net.minecraft.text.MutableText;

public class Reward implements JsonExileRegistry<Reward>, IAutoGson<Reward> {
    public static Reward SERIALIZER = new Reward();

    public int weight = 1000;
    public String id = "";

    public int worth = 1;
    public String type = "";

    public String data = "";
    public int min = 1;
    public int max = 3;

    public MutableText getTranslated(RewardData data) {
        return getRewardType().getTranslatable(data);

    }

    public RewardType getRewardType() {
        return MissionsDB.RewardTypes()
            .get(type);
    }

    @Override
    public ExileRegistryType getExileRegistryType() {
        return RegistryTypes.REWARD;
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
    public Class<Reward> getClassForSerialization() {
        return Reward.class;
    }
}

