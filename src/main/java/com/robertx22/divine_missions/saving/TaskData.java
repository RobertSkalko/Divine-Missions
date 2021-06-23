package com.robertx22.divine_missions.saving;

import com.robertx22.divine_missions.database.MissionsDB;
import com.robertx22.divine_missions.database.db_types.TaskEntry;
import com.robertx22.divine_missions.database.db_types.TaskType;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

@Storable
public class TaskData {

    @Store
    public String id = ""; // task entry
    @Store
    public int req = 0;
    @Store
    public int curr = 0;

    public static TaskData createNew(TaskEntry task, int req) {

        TaskData data = new TaskData();
        data.id = task.GUID();
        data.req = req;
        return data;
    }

    public MutableText getTranslated() {
        return getTaskType().getTranslatable(this)
            .append(" (" + curr + "/" + req + ")")
            .formatted(Formatting.RED);
    }

    public void increaseProgress() {
        curr++;
    }

    public boolean isDone(PlayerEntity player) {
        return getTaskType().isTaskDone(this, player);
    }

    public TaskEntry getTaskEntry() {
        return MissionsDB.TaskEntries()
            .get(id);
    }

    public TaskType getTaskType() {
        return getTaskEntry().getType();
    }

}
