package com.robertx22.divine_missions.saving;

import com.robertx22.divine_missions.database.MissionsDB;
import com.robertx22.divine_missions.database.db_types.Reward;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.IFormattableTextComponent;

@Storable
public class RewardData {

    @Store
    public String id = "";
    @Store
    public int count = 1;

    public int getWorth() {
        return count * getReward().worth;
    }

    public int getSingleWorth() {
        return count * getReward().worth;
    }

    public ItemStack getStack() {
        return new ItemStack(Registry.ITEM.get(new ResourceLocation(getReward().data)), count);
    }

    public IFormattableTextComponent getTranslated() {
        return getReward().getTranslated(this);
    }

    public void give(PlayerEntity player) {
        getReward().getRewardType()
            .giveReward(player, this);
    }

    public Reward getReward() {
        return MissionsDB.Rewards()
            .get(id);
    }
}
