package com.robertx22.divine_missions.data_gen.builders;

import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.database.db_types.TaskEntry;
import com.robertx22.divine_missions.util.MinMax;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class TaskEntryBuilder {

    public static TaskEntry ofMobKill(int seconds, int weight, EntityType type, int worth, MinMax minmax) {
        TaskEntry en = new TaskEntry();
        en.id = "kill_" + Registry.ENTITY_TYPE.getId(type)
            .getPath();
        en.worth = worth;
        en.min = minmax.min;
        en.max = minmax.max;
        en.weight = weight;
        en.seconds = seconds;
        en.data = Registry.ENTITY_TYPE.getId(type)
            .toString();
        en.task_type = TaskTypeIds.SPECIFIC_MOB_KILL;
        en.addToSerializables();
        return en;
    }

    public static TaskEntry ofAnyMobKill(int seconds, String id, int worth, MinMax minmax) {
        TaskEntry en = new TaskEntry();
        en.weight = 2000;
        en.id = id;
        en.worth = worth;
        en.seconds = seconds;
        en.min = minmax.min;
        en.max = minmax.max;
        en.task_type = TaskTypeIds.ANY_MOB_KILL;
        en.addToSerializables();
        return en;
    }

    public static TaskEntry collect(int weight, String id, Item item, int worth, MinMax minmax) {
        TaskEntry en = new TaskEntry();
        en.id = id;
        en.weight = weight;
        en.data = Registry.ITEM.getId(item)
            .toString();
        en.worth = worth;
        en.min = minmax.min;
        en.max = minmax.max;
        en.seconds = 60;
        en.task_type = TaskTypeIds.BRING_ITEM;
        en.addToSerializables();
        return en;
    }

}
