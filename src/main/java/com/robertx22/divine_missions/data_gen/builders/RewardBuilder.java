package com.robertx22.divine_missions.data_gen.builders;

import com.robertx22.divine_missions.database.RewardTypeIds;
import com.robertx22.divine_missions.database.db_types.Reward;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RewardBuilder {

    Reward reward = new Reward();

    public static Reward lootTable(int worth, int weight, Identifier loottable) {
        RewardBuilder b = new RewardBuilder();
        b.reward.id = loottable.toString()
            .replace(":", "_");
        b.reward.weight = weight;
        b.reward.type = RewardTypeIds.LOOT_TABLE;
        b.reward.min = 1;
        b.reward.max = 1;
        b.reward.worth = worth;
        b.reward.data = loottable.toString();
        b.reward.addToSerializables();
        return b.reward;
    }

    public static Reward exp(int worth, String id, int min, int max) {
        RewardBuilder b = new RewardBuilder();
        b.reward.id = id;
        b.reward.type = RewardTypeIds.EXP;
        b.reward.min = min;
        b.reward.max = max;
        b.reward.worth = worth;

        b.reward.addToSerializables();
        return b.reward;
    }

    public static RewardBuilder item(int worth) {
        RewardBuilder b = new RewardBuilder();
        b.reward.type = RewardTypeIds.ITEM;
        b.reward.worth = worth;
        return b;
    }

    public Reward build(Item item, int min, int max) {
        reward.id = Registry.ITEM.getId(item)
            .toString()
            .replace(":", "_");
        return build(Registry.ITEM.getId(item)
            .toString(), min, max);
    }

    public Reward build(String itemId, int min, int max) {
        reward.data = itemId;
        reward.min = min;
        reward.max = max;

        reward.addToSerializables();
        return reward;
    }

}
