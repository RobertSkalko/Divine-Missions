package com.robertx22.divine_missions.database.reward_types;

import com.robertx22.divine_missions.database.RewardTypeIds;
import com.robertx22.divine_missions.database.db_types.RewardType;
import com.robertx22.divine_missions.saving.RewardData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class ItemRewardType extends RewardType {

    public ItemRewardType() {
        super(RewardTypeIds.ITEM);
    }

    @Override
    public void giveReward(PlayerEntity player, RewardData data) {
        player.inventory.placeItemBackInInventory(player.level, data.getStack());
    }

    @Override
    public IFormattableTextComponent getTranslatable(RewardData data) {
        return new StringTextComponent(data.count + "x ").append(data.getStack()
                .getHoverName())
            .withStyle(TextFormatting.AQUA);
    }
}
