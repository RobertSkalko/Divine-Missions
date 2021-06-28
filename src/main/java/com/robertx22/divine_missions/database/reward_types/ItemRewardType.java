package com.robertx22.divine_missions.database.reward_types;

import com.robertx22.divine_missions.database.RewardTypeIds;
import com.robertx22.divine_missions.database.db_types.RewardType;
import com.robertx22.divine_missions.saving.RewardData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

public class ItemRewardType extends RewardType {

    public ItemRewardType() {
        super(RewardTypeIds.ITEM);
    }

    @Override
    public void giveReward(PlayerEntity player, RewardData data) {
        player.inventory.offerOrDrop(player.world, data.getStack());
    }

    @Override
    public MutableText getTranslatable(RewardData data) {
        return new LiteralText(data.count + "x ").append(data.getStack()
                .getName())
            .formatted(Formatting.AQUA);
    }
}
