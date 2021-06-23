package com.robertx22.divine_missions.saving;

import com.robertx22.divine_missions.database.MissionsDB;
import com.robertx22.divine_missions.database.db_types.Reward;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.MutableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Storable
public class RewardData {

    @Store
    public String id = "";
    @Store
    public int count = 1;

    public ItemStack getStack() {
        if (getReward().type == Reward.Type.ITEM) {
            return new ItemStack(Registry.ITEM.get(new Identifier(getReward().item_id)), count);
        }

        return new ItemStack(Items.CHEST); // todo
    }

    public MutableText getTranslated() {
        return getReward().getTranslated(this);
    }

    public void give(PlayerEntity player) {
        if (getReward().type == Reward.Type.ITEM) {
            ItemStack stack = getStack();

            player.giveItemStack(new ItemStack(Items.IRON_INGOT));

            player.inventory.offerOrDrop(player.world, stack);

        }
        // todo loottable give
    }

    public Reward getReward() {
        return MissionsDB.Rewards()
            .get(id);
    }
}
