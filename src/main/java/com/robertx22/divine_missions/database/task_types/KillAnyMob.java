package com.robertx22.divine_missions.database.task_types;

import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.database.db_types.TaskType;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.TaskData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class KillAnyMob extends TaskType {

    public KillAnyMob() {
        super(TaskTypeIds.ANY_MOB_KILL);
    }

    @Override
    public IFormattableTextComponent getTranslatable(PlayerEntity player, TaskData data) {
        return new TranslationTextComponent(DivineMissions.MODID + ".task." + this.id);

    }
}
