package com.robertx22.divine_missions.data_gen.adders;

import com.robertx22.divine_missions.data_gen.builders.TaskEntryBuilder;
import com.robertx22.divine_missions.database.db_types.TaskEntry;
import com.robertx22.divine_missions.util.MinMax;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;

public class TaskEntryAdder {

    public static TaskEntry KILL_ANY = TaskEntryBuilder.ofAnyMobKill(60, "kill_any_mob", 40, new MinMax(6, 20));

    public static TaskEntry KILL_SPIDER = TaskEntryBuilder.ofMobKill(120, 1000, EntityType.SPIDER, 100, new MinMax(2, 6));
    public static TaskEntry KILL_ENDERMAN = TaskEntryBuilder.ofMobKill(500, 200, EntityType.ENDERMAN, 250, new MinMax(1, 3));
    public static TaskEntry KILL_ZOMBIE = TaskEntryBuilder.ofMobKill(100, 1000, EntityType.ZOMBIE, 50, new MinMax(4, 10));
    public static TaskEntry KILL_WITHER = TaskEntryBuilder.ofMobKill(600, 50, EntityType.WITHER, 2000, new MinMax(1, 1));

    public static TaskEntry GET_DIAMONDS = TaskEntryBuilder.collect(750, "get_diamonds", Items.DIAMOND, 250, new MinMax(1, 3));
    public static TaskEntry GET_BOOKS = TaskEntryBuilder.collect(500, "get_books", Items.BOOK, 100, new MinMax(2, 6));

    public static void init() {
    }

}
