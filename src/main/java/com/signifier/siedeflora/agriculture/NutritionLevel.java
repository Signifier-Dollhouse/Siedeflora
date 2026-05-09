package com.signifier.siedeflora.agriculture;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

import java.util.Locale;

// 贫乏、低量、适常、丰盈和过饱和
public enum NutritionLevel implements StringRepresentable
{
    POOR(0, 2),
    LOW(3, 5),
    MEDIUM(6, 9),
    RICH(10, 13),
    SATURATED(14, 15);

    public static final Codec<NutritionLevel> CODEC = StringRepresentable.fromEnum(NutritionLevel::values);
    private final int from;
    private final int to;

    NutritionLevel(int from, int to)
    {
        this.from = from;
        this.to = to;
    }

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

    public int minValue() {
        return from;
    }

    public int maxValue() {
        return to;
    }
}
