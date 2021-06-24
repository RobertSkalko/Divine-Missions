package com.robertx22.divine_missions.database.task_types;

import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.database.db_types.TaskType;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.TaskData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

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

        Item item = Registry.ITEM.get(new Identifier(data.getTaskEntry().data));

        for (ItemStack stack : player.inventory.main) {
            if (data.curr < data.req) {
                int tospend = data.req - data.curr;

                if (stack.getItem() == item) {
                    int spend = MathHelper.clamp(stack.getCount(), 0, tospend);
                    stack.decrement(spend);
                    data.curr += spend;
                }

            }
        }

    }

    @Override
    public MutableText getTranslatable(TaskData data) {
        Item item = Registry.ITEM.get(new Identifier(data.getTaskEntry().data));
        return new TranslatableText(DivineMissions.MODID + ".task." + this.id).append(" ")
            .append(item.getName());

    }

}

