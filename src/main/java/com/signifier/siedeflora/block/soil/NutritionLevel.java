package com.signifier.siedeflora.block.soil;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

import java.util.Locale;

// 贫乏、低量、适常、丰盈和过饱和
public enum NutritionLevel implements StringRepresentable
{
    POOR,
    LOW,
    MEDIUM,
    RICH,
    SATURATED;

    public static final Codec<NutritionLevel> CODEC = StringRepresentable.fromEnum(NutritionLevel::values);

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static NutritionLevel fromString(String name) {
        for (NutritionLevel level : values()) {
            if (level.getSerializedName().equals(name.toLowerCase(Locale.ROOT))) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown NutritionLevel: " + name);
    }
}
