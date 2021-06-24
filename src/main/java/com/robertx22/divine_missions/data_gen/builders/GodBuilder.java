package com.robertx22.divine_missions.data_gen.builders;

import com.robertx22.divine_missions.database.db_types.God;
import net.minecraft.util.Formatting;

public class GodBuilder {

    public static God of(String id, Formatting format) {

        God god = new God();
        god.format = format.getName();

        god.id = id;

        god.addToSerializables();
        return god;
    }
}
