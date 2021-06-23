package com.robertx22.divine_missions.data_gen.builders;

import com.robertx22.divine_missions.database.db_types.Reward;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class RewardBuilder {

    Reward reward = new Reward();

    public static RewardBuilder item(String id, int worth) {
        RewardBuilder b = new RewardBuilder();
        b.reward.type = Reward.Type.ITEM;
        b.reward.id = id;
        b.reward.worth = worth;
        return b;
    }

    public Reward build(Item item, int min, int max) {
        return build(Registry.ITEM.getId(item)
            .toString(), min, max);
    }

    public Reward build(String itemId, int min, int max) {
        reward.item_id = itemId;
        reward.min = min;
        reward.max = max;

        reward.addToSerializables();
        return reward;
    }

}
