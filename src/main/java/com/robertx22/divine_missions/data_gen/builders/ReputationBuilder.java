package com.robertx22.divine_missions.data_gen.builders;

import com.robertx22.divine_missions.database.db_types.ReputationLevel;
import net.minecraft.util.text.TextFormatting;

public class ReputationBuilder {

    public static ReputationLevel of(String id, TextFormatting format, int rank, int needed, float weightMulti, int higherRarChance) {

        ReputationLevel rep = new ReputationLevel();
        rep.id = id;
        rep.format = format.getName();
        rep.chance_for_higher_rar = higherRarChance;
        rep.rep_needed = needed;
        rep.rank = rank;
        rep.weight_multi = weightMulti;
        rep.addToSerializables();
        return rep;

    }
}
