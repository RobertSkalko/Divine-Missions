package com.robertx22.divine_missions.database.task_types;

import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.database.db_types.TaskType;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.TaskData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class KillMobTag extends TaskType {

    public KillMobTag() {
        super(TaskTypeIds.KILL_MOB_OF_TAG);
    }

    @Override
    public IFormattableTextComponent getTranslatable(PlayerEntity player, TaskData data) {
        return new TranslationTextComponent(DivineMissions.MODID + ".task." + this.id).append(" ")
            .append(data.getTaskEntry().data);

    }
}
