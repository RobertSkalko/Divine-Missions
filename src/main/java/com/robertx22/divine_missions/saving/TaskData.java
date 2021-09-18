package com.robertx22.divine_missions.saving;

import com.robertx22.divine_missions.database.MissionsDB;
import com.robertx22.divine_missions.database.db_types.TaskEntry;
import com.robertx22.divine_missions.database.db_types.TaskType;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TextFormatting;

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

    public IFormattableTextComponent getTranslated(PlayerEntity player) {
        return getTaskType().getTranslatable(player, this)
            .append(" (" + getTaskType().getCurrentDoneTooltip(player, this) + "/" + req + ")")
            .withStyle(TextFormatting.RED);
    }

    public void increaseProgress() {
        curr++;
    }

    public boolean isDone(PlayerEntity player) {
        return getTaskType().isTaskDone(this, player);
    }

    public boolean isDoneTooltip(PlayerEntity player) {
        return getTaskType().isTaskDoneTooltip(this, player);
    }

    public TaskEntry getTaskEntry() {
        return MissionsDB.TaskEntries()
            .get(id);
    }

    public TaskType getTaskType() {
        return getTaskEntry().getType();
    }

}
