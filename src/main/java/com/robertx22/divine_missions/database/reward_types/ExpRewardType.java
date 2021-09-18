package com.robertx22.divine_missions.database.reward_types;

import com.robertx22.divine_missions.database.RewardTypeIds;
import com.robertx22.divine_missions.database.db_types.RewardType;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.RewardData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class ExpRewardType extends RewardType {

    public ExpRewardType() {
        super(RewardTypeIds.EXP);
    }

    @Override
    public void giveReward(PlayerEntity player, RewardData data) {
        player.giveExperiencePoints(data.count);
    }

    @Override
    public IFormattableTextComponent getTranslatable(RewardData data) {
        return new StringTextComponent(data.count + " ").append(DivineMissions.ofTranslation("exp"))
            .withStyle(TextFormatting.GREEN);
    }
}

