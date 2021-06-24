package com.robertx22.divine_missions.database.task_types;

import com.robertx22.divine_missions.database.TaskTypeIds;
import com.robertx22.divine_missions.database.db_types.TaskType;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.TaskData;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public class LootChest extends TaskType {

    public LootChest() {
        super(TaskTypeIds.LOOT_CHEST);
    }

    @Override
    public MutableText getTranslatable(TaskData data) {
        return new TranslatableText(DivineMissions.MODID + ".loot_chest").formatted(Formatting.GOLD);

    }
}
