package com.signifier.siedeflora.agriculture.soil;

import net.minecraft.resources.Identifier;

public class SoilState
{
    public static final int MAX_MOISTURE = 300;

    private Identifier texture = SoilTexture.DEFAULT.id();
    private int moisture = 50;
    private final NutritionValue nitro = new NutritionValue();
    private final NutritionValue phos = new NutritionValue();
    private final NutritionValue pota = new NutritionValue();

    public NutritionValue nitro() {
        return this.nitro;
    }

    public NutritionValue phos() {
        return this.phos;
    }

    public NutritionValue pota() {
        return this.pota;
    }

    public void setTexture(SoilTexture texture) {
        this.texture = texture.id();
        this.nitro().setLevel(texture.nitro());
        this.phos().setLevel(texture.phos());
        this.pota().setLevel(texture.pota());
    }

    public void tick() {
        this.nitro().tick();
        this.phos().tick();
        this.pota().tick();
    }
}
