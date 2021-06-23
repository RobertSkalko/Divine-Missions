package com.robertx22.divine_missions.database.task_types;

import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.database.db_types.TaskType;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.TaskData;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;

public class KillAnyMob extends TaskType {

    public KillAnyMob() {
        this.id = TaskTypeIds.ANY_MOB_KILL;
    }

    @Override
    public MutableText getTranslatable(TaskData data) {
        return new TranslatableText(DivineMissions.MODID + ".task." + this.id);

    }
}
