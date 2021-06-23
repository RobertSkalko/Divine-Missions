package com.robertx22.divine_missions.data_gen.adders;

import com.robertx22.divine_missions.data_gen.builders.RewardBuilder;
import com.robertx22.divine_missions.database.db_types.Reward;
import net.minecraft.item.Items;

public class RewardsAdder {

    public static Reward DIAMONDS = RewardBuilder.item("diamond", 1000)
        .build(Items.DIAMOND, 1, 3);
    public static Reward IRON = RewardBuilder.item("iron", 100)
        .build(Items.IRON_INGOT, 3, 5);
    public static Reward GOLD = RewardBuilder.item("gold", 200)
        .build(Items.GOLD_INGOT, 2, 4);

    public static void init() {

    }
}
