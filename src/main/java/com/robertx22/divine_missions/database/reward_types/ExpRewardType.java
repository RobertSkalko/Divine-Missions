package com.robertx22.divine_missions.database.reward_types;

import com.robertx22.divine_missions.database.RewardTypeIds;
import com.robertx22.divine_missions.database.db_types.RewardType;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.RewardData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

public class ExpRewardType extends RewardType {

    public ExpRewardType() {
        this.id = RewardTypeIds.EXP;
    }

    @Override
    public void giveReward(PlayerEntity player, RewardData data) {
        player.addExperience(data.count);
    }

    @Override
    public MutableText getTranslatable(RewardData data) {
        return new LiteralText(data.count + " ").append(DivineMissions.ofTranslation("exp"))
            .formatted(Formatting.GREEN);
    }
}

