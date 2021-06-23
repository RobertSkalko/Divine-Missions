package com.robertx22.divine_missions.database.db_types;

import com.robertx22.divine_missions.database.MissionsDB;
import com.robertx22.divine_missions.database.RegistryTypes;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.library_of_exile.registry.ExileRegistryType;
import com.robertx22.library_of_exile.registry.IAutoGson;
import com.robertx22.library_of_exile.registry.JsonExileRegistry;
import com.robertx22.library_of_exile.utils.RandomUtils;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class God implements JsonExileRegistry<God>, IAutoGson<God> {
    public static God SERIALIZER = new God();

    public int weight = 1000;
    public String id = "";

    public String format = "";

    public List<String> reward_pools = new ArrayList<>();
    public List<String> task_pools = new ArrayList<>();

    public List<Pool> getTaskPools() {
        return getPools(task_pools);
    }

    public List<Pool> getRewardPools() {
        return getPools(reward_pools);
    }

    public Formatting getFormat() {
        return Formatting.byName(format);
    }

    public List<Pool> getPools(List<String> list) {
        List<Pool> result = new ArrayList<>();
        for (String x : list) {
            Pool pool = MissionsDB.Pools()
                .get(x);

            if (pool.hasRequiredMod()) {
                result.add(pool);
            }
        }
        return result;
    }

    public List<Pool> getRandomPoolsToUse(List<String> pools, MissionRarity rar) {
        int amount = RandomUtils.RandomRange(rar.min_tasks, rar.max_tasks);

        List<Pool> touse = new ArrayList<>();

        List<Pool> pickrandom = new ArrayList<>();
        getPools(pools)
            .forEach(x -> {
                if (x.pick_type == Pool.PickType.ALWAYS_INCLUDE) {
                    touse.add(x);
                } else {
                    pickrandom.add(x);
                }
            });

        if (!pickrandom.isEmpty()) {
            while (touse.size() < amount) {
                touse.add(RandomUtils.weightedRandom(pickrandom));
            }
        }

        return touse;
    }

    public TranslatableText getTranslatable() {
        return new TranslatableText(DivineMissions.MODID + ".god." + id);
    }

    @Override
    public ExileRegistryType getExileRegistryType() {
        return RegistryTypes.GOD;
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
    public Class<God> getClassForSerialization() {
        return God.class;
    }

}
