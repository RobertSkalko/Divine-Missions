package com.robertx22.divine_missions.database.task_types;

import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.database.db_types.TaskType;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.TaskData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class LootChest extends TaskType {

    public LootChest() {
        super(TaskTypeIds.LOOT_CHEST);
    }

    @Override
    public IFormattableTextComponent getTranslatable(PlayerEntity player, TaskData data) {
        return new TranslationTextComponent(DivineMissions.MODID + ".loot_chest").withStyle(TextFormatting.GOLD);
    }
}
