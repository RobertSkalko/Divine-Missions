package com.robertx22.divine_missions.database.db_types;

import com.robertx22.divine_missions.db_init.RegistryTypes;
import com.robertx22.divine_missions.saving.TaskData;
import com.robertx22.library_of_exile.registry.ExileRegistry;
import com.robertx22.library_of_exile.registry.ExileRegistryType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;

public abstract class TaskType implements ExileRegistry<TaskType> {
    public static TaskType SERIALIZER = new TaskType("") {
        @Override
        public boolean isTaskDone(TaskData data, PlayerEntity player) {
            return false;
        }

        @Override
        public MutableText getTranslatable(TaskData data) {
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

    public void spendItems(PlayerEntity player, TaskData data) {

    }

    public abstract MutableText getTranslatable(TaskData data);

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
