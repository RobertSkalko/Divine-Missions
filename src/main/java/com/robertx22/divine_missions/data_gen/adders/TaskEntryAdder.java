package com.robertx22.divine_missions.data_gen.adders;

import com.robertx22.divine_missions.data_gen.builders.TaskEntryBuilder;
import com.robertx22.divine_missions.database.db_types.TaskEntry;
import com.robertx22.divine_missions.util.MinMax;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;

public class TaskEntryAdder {

    public static TaskEntry KILL_ANY = TaskEntryBuilder.ofAnyMobKill(60, "kill_any_mob", 40, new MinMax(6, 20));
    public static TaskEntry KILL_MANY = TaskEntryBuilder.ofAnyMobKill(60, "kill_many_mobs", 30, new MinMax(15, 30));
    public static TaskEntry GET_TOTEM_OF_UDNYING = TaskEntryBuilder.collect(50, Items.TOTEM_OF_UNDYING, 1000, new MinMax(1, 1));
    public static TaskEntry KILL_GHAST = TaskEntryBuilder.ofMobKill(200, 500, EntityType.GHAST, 250, new MinMax(1, 3));
    public static TaskEntry KILL_WITHER_SKELE = TaskEntryBuilder.ofMobKill(100, 1000, EntityType.WITHER_SKELETON, 100, new MinMax(2, 6));
    public static TaskEntry KILL_PILLAGER = TaskEntryBuilder.ofMobKill(120, 1000, EntityType.PILLAGER, 50, new MinMax(2, 6));

    public static TaskEntry KILL_SPIDER = TaskEntryBuilder.ofMobKill(120, 1000, EntityType.SPIDER, 100, new MinMax(2, 6));
    public static TaskEntry KILL_ENDERMAN = TaskEntryBuilder.ofMobKill(500, 200, EntityType.ENDERMAN, 250, new MinMax(1, 3));
    public static TaskEntry KILL_ZOMBIE = TaskEntryBuilder.ofMobKill(100, 1000, EntityType.ZOMBIE, 50, new MinMax(4, 10));
    public static TaskEntry KILL_WITHER = TaskEntryBuilder.ofMobKill(600, 50, EntityType.WITHER, 2000, new MinMax(1, 1));

    public static TaskEntry GET_DIAMONDS = TaskEntryBuilder.collect(750, Items.DIAMOND, 500, new MinMax(1, 3));
    public static TaskEntry GET_IRON = TaskEntryBuilder.collect(1000, Items.IRON_INGOT, 100, new MinMax(4, 8));
    public static TaskEntry GET_GOLD = TaskEntryBuilder.collect(900, Items.GOLD_INGOT, 150, new MinMax(2, 5));
    public static TaskEntry GET_LAPIS = TaskEntryBuilder.collect(500, Items.LAPIS_LAZULI, 50, new MinMax(5, 15));

    public static TaskEntry GET_BOOKS = TaskEntryBuilder.collect(500, Items.BOOK, 100, new MinMax(2, 6));
    public static TaskEntry GET_PAPER = TaskEntryBuilder.collect(500, Items.PAPER, 25, new MinMax(5, 15));
    public static TaskEntry GET_INK = TaskEntryBuilder.collect(500, Items.INK_SAC, 50, new MinMax(2, 6));
    public static TaskEntry GET_WRITABLE_BOOK = TaskEntryBuilder.collect(500, Items.WRITABLE_BOOK, 200, new MinMax(1, 3));

    public static TaskEntry GET_BREAD = TaskEntryBuilder.collect(500, Items.BREAD, 20, new MinMax(5, 15));
    public static TaskEntry GET_MELONS = TaskEntryBuilder.collect(500, Items.MELON_SLICE, 10, new MinMax(20, 60));
    public static TaskEntry GET_POTATOES = TaskEntryBuilder.collect(500, Items.BAKED_POTATO, 25, new MinMax(4, 14));
    public static TaskEntry GET_BEETROOT = TaskEntryBuilder.collect(500, Items.BEETROOT, 15, new MinMax(4, 14));
    public static TaskEntry GET_CAKE = TaskEntryBuilder.collect(250, Items.CAKE, 100, new MinMax(1, 3));
    public static TaskEntry GET_COOKIES = TaskEntryBuilder.collect(200, Items.COOKIE, 25, new MinMax(5, 15));
    public static TaskEntry GET_CARROTS = TaskEntryBuilder.collect(200, Items.CARROT, 15, new MinMax(4, 12));
    public static TaskEntry GET_HONEYCOMB = TaskEntryBuilder.collect(200, Items.HONEYCOMB, 25, new MinMax(2, 5));

    public static TaskEntry GET_SALMON = TaskEntryBuilder.collect(200, Items.COOKED_SALMON, 50, new MinMax(2, 5));
    public static TaskEntry GET_PUFFER = TaskEntryBuilder.collect(200, Items.PUFFERFISH, 25, new MinMax(1, 3));
    public static TaskEntry GET_TROPICAL = TaskEntryBuilder.collect(200, Items.TROPICAL_FISH, 25, new MinMax(2, 5));
    public static TaskEntry GET_COD = TaskEntryBuilder.collect(200, Items.COOKED_COD, 25, new MinMax(2, 5));
    public static TaskEntry GET_NAUT_SHELL = TaskEntryBuilder.collect(50, Items.NAUTILUS_SHELL, 100, new MinMax(1, 1));

    public static void init() {
    }

}
