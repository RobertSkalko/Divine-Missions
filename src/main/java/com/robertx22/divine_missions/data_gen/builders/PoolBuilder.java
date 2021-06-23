package com.robertx22.divine_missions.data_gen.builders;

import com.robertx22.divine_missions.database.db_types.Pool;
import com.robertx22.library_of_exile.registry.IGUID;

import java.util.List;
import java.util.stream.Collectors;

public class PoolBuilder {

    public static <T extends IGUID> Pool of(String id, int weight, Pool.PoolType type, Pool.PickType pickType, List<T> entries) {

        Pool pool = new Pool();

        pool.id = id;
        pool.weight = weight;

        pool.type = type;
        pool.pick_type = pickType;

        pool.entries = entries.stream()
            .map(x -> x.GUID())
            .collect(Collectors.toList());

        pool.addToSerializables();

        return pool;

    }
}
