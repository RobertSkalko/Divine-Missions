package com.robertx22.divine_missions.database.db_types;

import com.robertx22.divine_missions.database.MissionsDB;
import com.robertx22.divine_missions.database.RegistryTypes;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.util.FormatUtils;
import com.robertx22.library_of_exile.registry.ExileRegistryType;
import com.robertx22.library_of_exile.registry.IAutoGson;
import com.robertx22.library_of_exile.registry.JsonExileRegistry;
import com.robertx22.library_of_exile.utils.RandomUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class God implements JsonExileRegistry<God>, IAutoGson<God> {
    public static God SERIALIZER = new God();

    public int weight = 1000;
    public String id = "";

    public String format = "";

    public Formatting getFormat() {
        return FormatUtils.of(format);
    }

    List<Pool> getPoolsForThisGod(PlayerEntity player, Pool.PoolType type) {
        List<Pool> list = new ArrayList<>();

        MissionsDB.Pools()
            .getList()
            .forEach(x -> {
                if (x.gods.contains(id)) {
                    if (x.meetsConditions(player)) {
                        if (x.type == type) {
                            list.add(x);
                        }
                    }
                }
            });

        return list;
    }

    public List<Pool> getRandomPoolsToUse(PlayerEntity player, Pool.PoolType type, MissionRarity rar) {
        List<Pool> pools = getPoolsForThisGod(player, type);

        int amount = RandomUtils.RandomRange(rar.min_tasks, rar.max_tasks);

        List<Pool> touse = new ArrayList<>();

        List<Pool> pickrandom = new ArrayList<>();
        pools
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
