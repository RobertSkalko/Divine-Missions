package com.robertx22.divine_missions.util;

import net.minecraft.util.Formatting;

public class FormatUtils {

    public static Formatting of(String name) {
        Formatting format = Formatting.byName(name);
        return format == null ? Formatting.GRAY : format;
    }
}
