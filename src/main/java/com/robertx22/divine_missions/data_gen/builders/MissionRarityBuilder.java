package com.robertx22.divine_missions.data_gen.builders;

import com.robertx22.divine_missions.database.db_types.MissionRarity;
import net.minecraft.util.Formatting;

public class MissionRarityBuilder {

    public static MissionRarity of(String id, int rank, int weight, Formatting format, float rewardMulti, float difficultyMulti, int mintasks, int maxtasks) {

        MissionRarity r = new MissionRarity();
        r.weight = weight;
        r.id = id;
        r.format = format.getName();
        r.rank = rank;
        r.reward_multi = rewardMulti;
        r.diff_multi = difficultyMulti;

        r.min_tasks = mintasks;
        r.max_tasks = maxtasks;

        r.addToSerializables();
        return r;
    }
}
