package com.robertx22.divine_missions.database.task_types;

import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.database.db_types.TaskType;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.TaskData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.entity.EntityType;

public class KillSpecificMob extends TaskType {

    public KillSpecificMob() {
        super(TaskTypeIds.SPECIFIC_MOB_KILL);
    }

    @Override
    public IFormattableTextComponent getTranslatable(PlayerEntity player, TaskData data) {
        EntityType type = Registry.ENTITY_TYPE.get(new ResourceLocation(data.getTaskEntry().data));
        return new TranslationTextComponent(DivineMissions.MODID + ".task." + this.id).append(" ")
            .append(type.getDescription());

    }
}
