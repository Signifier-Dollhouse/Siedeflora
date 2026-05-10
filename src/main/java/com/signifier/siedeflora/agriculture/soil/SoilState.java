package com.signifier.siedeflora.agriculture.soil;

import net.minecraft.resources.Identifier;

public interface SoilState
{
    NutritionValue nitro();

    NutritionValue phos();

    NutritionValue pota();

    SoilTexture texture();

    default void setTexture(SoilTexture texture) {
        this.nitro().setLevel(texture.nitro());
        this.phos().setLevel(texture.phos());
        this.pota().setLevel(texture.pota());
    }

    default void tick() {
        this.nitro().tick();
        this.phos().tick();
        this.pota().tick();
    }
}
