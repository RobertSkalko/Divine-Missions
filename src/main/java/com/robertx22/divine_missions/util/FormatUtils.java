package com.robertx22.divine_missions.util;

import net.minecraft.util.text.TextFormatting;

public class FormatUtils {

    public static TextFormatting of(String name) {
        TextFormatting format = TextFormatting.getByName(name);
        return format == null ? TextFormatting.GRAY : format;
    }
}
