package com.robertx22.divine_missions.database.reward_types;

import com.robertx22.divine_missions.database.RewardTypeIds;
import com.robertx22.divine_missions.database.db_types.RewardType;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.RewardData;
import com.robertx22.library_of_exile.utils.CommandUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

public class CommandRewardType extends RewardType {

    public CommandRewardType() {
        super(RewardTypeIds.COMMAND);
    }

    @Override
    public void giveReward(PlayerEntity player, RewardData data) {
        for (int i = 0; i < data.count; i++) {
            CommandUtils.execute(player, data.getReward().data);
        }
    }

    @Override
    public MutableText getTranslatable(RewardData data) {
        return new LiteralText(data.count + "x ").append(DivineMissions.ofTranslation("command." + id))
            .formatted(Formatting.DARK_PURPLE);
    }
}

