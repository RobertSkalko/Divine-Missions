package com.robertx22.divine_missions.database.db_types;

import com.robertx22.divine_missions.database.condition_types.ConditionData;
import com.robertx22.divine_missions.db_init.RegistryTypes;
import com.robertx22.library_of_exile.registry.ExileRegistry;
import com.robertx22.library_of_exile.registry.ExileRegistryType;
import net.minecraft.entity.player.PlayerEntity;

public abstract class ConditionType implements ExileRegistry<ConditionType> {

    public static ConditionType SERIALIZER = new ConditionType("ds") {

        @Override
        public boolean isAllowed(PlayerEntity player, ConditionData data) {
            return false;
        }
    };

    public int weight = 1000;
    public String id = "";

    public ConditionType(String id) {
        this.id = id;
    }

    public abstract boolean isAllowed(PlayerEntity player, ConditionData data);

    @Override
    public ExileRegistryType getExileRegistryType() {
        return RegistryTypes.CONDITION_TYPE;
    }

    @Override
    public String GUID() {
        return id;
    }

    @Override
    public int Weight() {
        return weight;
    }

}
