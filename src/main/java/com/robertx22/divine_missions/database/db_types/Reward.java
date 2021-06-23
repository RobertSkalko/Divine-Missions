package com.robertx22.divine_missions.database.db_types;

import com.robertx22.divine_missions.database.RegistryTypes;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.RewardData;
import com.robertx22.library_of_exile.registry.ExileRegistryType;
import com.robertx22.library_of_exile.registry.IAutoGson;
import com.robertx22.library_of_exile.registry.JsonExileRegistry;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public class Reward implements JsonExileRegistry<Reward>, IAutoGson<Reward> {
    public static Reward SERIALIZER = new Reward();

    public int weight = 1000;
    public String id = "";

    public enum Type {
        ITEM, LOOT_TABLE; // todo turn into reward type registry. cus i can add aoe exp etc
    }

    public MutableText getTranslated(RewardData data) {

        if (type == Type.ITEM) {
            return new LiteralText(data.count + "x ").append(data.getStack()
                    .getName())
                .formatted(Formatting.AQUA);
        }

        return new TranslatableText(DivineMissions.MODID + ".loot_table");
    }

    public int worth = 1;
    public Type type = Type.ITEM;

    public String item_id = "";
    public int min = 1;
    public int max = 3;

    @Override
    public ExileRegistryType getExileRegistryType() {
        return RegistryTypes.REWARD;
    }

    @Override
    public String GUID() {
        return id;
    }

    @Override
    public int Weight() {
        return weight;
    }

    @Override
    public Class<Reward> getClassForSerialization() {
        return Reward.class;
    }
}

