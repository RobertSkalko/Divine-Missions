package com.robertx22.divine_missions.database.db_types;

import com.robertx22.divine_missions.db_init.RegistryTypes;
import com.robertx22.divine_missions.saving.TaskData;
import com.robertx22.library_of_exile.registry.ExileRegistry;
import com.robertx22.library_of_exile.registry.ExileRegistryType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;

public abstract class TaskType implements ExileRegistry<TaskType> {
    public static TaskType SERIALIZER = new TaskType("") {
        @Override
        public boolean isTaskDone(TaskData data, PlayerEntity player) {
            return false;
        }

        @Override
        public IFormattableTextComponent getTranslatable(PlayerEntity player, TaskData data) {
            return null;
        }
    };

    public int weight = 1000;
    public String id = "";

    public TaskType(String id) {
        this.id = id;
    }

    public boolean isTaskDone(TaskData data, PlayerEntity player) {
        return data.curr >= data.req;
    }

    public boolean isTaskDoneTooltip(TaskData data, PlayerEntity player) {
        return isTaskDone(data, player);
    }

    public int getCurrentDoneTooltip(PlayerEntity player, TaskData data) {
        return data.curr;
    }

    public void spendItems(PlayerEntity player, TaskData data) {

    }

    public abstract IFormattableTextComponent getTranslatable(PlayerEntity player, TaskData data);

    @Override
    public ExileRegistryType getExileRegistryType() {
        return RegistryTypes.TASK_TYPE;
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
