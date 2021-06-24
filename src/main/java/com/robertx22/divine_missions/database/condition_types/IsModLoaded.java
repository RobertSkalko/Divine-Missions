package com.robertx22.divine_missions.database.condition_types;

import com.robertx22.divine_missions.database.ConditionTypeIds;
import com.robertx22.divine_missions.database.db_types.ConditionType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;

public class IsModLoaded extends ConditionType {

    public IsModLoaded() {
        super(ConditionTypeIds.IS_MOD_LOADED);
    }

    @Override
    public boolean isAllowed(PlayerEntity player, ConditionData data) {
        return FabricLoader.getInstance()
            .isModLoaded(data.map.get("modid"));
    }

    public static ConditionData of(String modid) {
        ConditionData data = new ConditionData();
        data.id = ConditionTypeIds.IS_MOD_LOADED;
        data.map.put("modid", modid);
        return data;

    }
}
