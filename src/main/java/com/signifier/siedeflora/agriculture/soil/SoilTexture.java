package com.signifier.siedeflora.agriculture.soil;

import com.signifier.siedeflora.Siedeflora;
import com.signifier.siedeflora.agriculture.NutritionLevel;
import net.minecraft.resources.Identifier;

public record SoilTexture(Identifier id, NutritionLevel nitro, NutritionLevel phos, NutritionLevel pota)
{
    public static final SoilTexture DEFAULT = new SoilTexture(Siedeflora.id("default"), NutritionLevel.POOR, NutritionLevel.POOR, NutritionLevel.POOR);
}
