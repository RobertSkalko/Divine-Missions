package com.robertx22.divine_missions.database.task_types;

import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.database.db_types.TaskType;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.TaskData;
import net.minecraft.entity.EntityType;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class KillSpecificMob extends TaskType {

    public KillSpecificMob() {
        this.id = TaskTypeIds.SPECIFIC_MOB_KILL;
    }

    @Override
    public MutableText getTranslatable(TaskData data) {
        EntityType type = Registry.ENTITY_TYPE.get(new Identifier(data.getTaskEntry().data));
        return new TranslatableText(DivineMissions.MODID + ".task." + this.id).append(" ")
            .append(type.getName());

    }
}