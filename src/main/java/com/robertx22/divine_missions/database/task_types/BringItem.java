package com.robertx22.divine_missions.database.task_types;

import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.database.db_types.TaskType;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.TaskData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class BringItem extends TaskType {

    public BringItem() {
        super(TaskTypeIds.BRING_ITEM);
    }

    @Override
    public boolean isTaskDone(TaskData data, PlayerEntity player) {

        return super.isTaskDone(data, player);
    }

    @Override
    public void spendItems(PlayerEntity player, TaskData data) {

        Item item = Registry.ITEM.get(new ResourceLocation(data.getTaskEntry().data));

        for (ItemStack stack : player.inventory.items) {
            if (data.curr < data.req) {
                int tospend = data.req - data.curr;

                if (stack.getItem() == item) {
                    int spend = MathHelper.clamp(stack.getCount(), 0, tospend);
                    stack.shrink(spend);
                    data.curr += spend;
                }

            }
        }

    }

    @Override
    public int getCurrentDoneTooltip(PlayerEntity player, TaskData data) {
        Item item = Registry.ITEM.get(new ResourceLocation(data.getTaskEntry().data));
        int inInv = player.inventory.countItem(item);
        int current = MathHelper.clamp(inInv + data.curr, 0, data.req);
        return current;
    }

    @Override
    public boolean isTaskDoneTooltip(TaskData data, PlayerEntity player) {
        return getCurrentDoneTooltip(player, data) >= data.req;
    }

    @Override
    public IFormattableTextComponent getTranslatable(PlayerEntity player, TaskData data) {

        Item item = Registry.ITEM.get(new ResourceLocation(data.getTaskEntry().data));

        return new TranslationTextComponent(DivineMissions.MODID + ".task." + this.id).append(" ")
            .append(item.getDescription());

    }

}

