package com.robertx22.divine_missions.database.db_types;

import com.robertx22.divine_missions.db_init.RegistryTypes;
import com.robertx22.divine_missions.saving.RewardData;
import com.robertx22.library_of_exile.registry.ExileRegistry;
import com.robertx22.library_of_exile.registry.ExileRegistryType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;

public abstract class RewardType implements ExileRegistry<RewardType> {

    public static RewardType SERIALIZER = new RewardType("") {
        @Override
        public void giveReward(PlayerEntity player, RewardData data) {

        }

        @Override
        public MutableText getTranslatable(RewardData data) {
            return null;
        }
    };

    public int weight = 1000;
    public String id = "";

    public RewardType(String id) {
        this.id = id;
    }

    public abstract void giveReward(PlayerEntity player, RewardData data);

    public abstract MutableText getTranslatable(RewardData data);

    @Override
    public ExileRegistryType getExileRegistryType() {
        return RegistryTypes.REWARD_TYPE;
    }

    @Override
    public String GUID() {
        return id;
    }

    @Override
    public int Weight() {
        return weight;
    }

}
