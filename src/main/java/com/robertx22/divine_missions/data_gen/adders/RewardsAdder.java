package com.robertx22.divine_missions.data_gen.adders;

import com.robertx22.divine_missions.data_gen.builders.RewardBuilder;
import com.robertx22.divine_missions.database.db_types.Reward;
import com.robertx22.divine_missions.main.DivineMissions;
import net.minecraft.item.Items;

public class RewardsAdder {

    public static Reward DIAMONDS = RewardBuilder.item(500)
        .build(Items.DIAMOND, 1, 3);
    public static Reward IRON = RewardBuilder.item(100)
        .build(Items.IRON_INGOT, 3, 5);
    public static Reward GOLD = RewardBuilder.item(150)
        .build(Items.GOLD_INGOT, 2, 4);
    public static Reward ENDER_PEARL = RewardBuilder.item(300)
        .build(Items.ENDER_PEARL, 1, 3);

    public static Reward EXPERIENCE = RewardBuilder.exp(2, "exp", 1, 1000);
    public static Reward BIG_EXP = RewardBuilder.exp(1, "exp", 200, 1500);

    public static Reward ENCHANTED_BOOK_LOOT_TABLE = RewardBuilder.lootTable(1000, 100, DivineMissions.id("enchanted_book"));
    public static Reward ENCHANTED_FISHING_ROD = RewardBuilder.lootTable(1000, 100, DivineMissions.id("enchanted_rod"));

    public static void init() {

    }
}
