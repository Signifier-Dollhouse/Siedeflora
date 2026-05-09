package com.signifier.siedeflora.agriculture.soil;

import net.minecraft.resources.Identifier;

public class SoilState
{
    private final NutritionValue nitro;
    private final NutritionValue phos;
    private final NutritionValue pota;
    private Identifier soilTexture;

    public SoilState() {
        this(SoilTexture.DEFAULT);
    }

    public SoilState(SoilTexture texture) {
        this.soilTexture = texture.id();
        this.nitro = new NutritionValue(texture.nitro());
        this.phos = new NutritionValue(texture.phos());
        this.pota = new NutritionValue(texture.pota());
    }

    public void setTexture(SoilTexture texture) {
        this.soilTexture = texture.id();
        this.nitro.setLevel(texture.nitro());
        this.phos.setLevel(texture.phos());
        this.pota.setLevel(texture.pota());
    }

    public void tick() {
        this.nitro.tick();
        this.phos.tick();
        this.pota.tick();
    }

    public NutritionValue nitro() {
        return nitro;
    }

    public NutritionValue phos() {
        return phos;
    }

    public NutritionValue pota() {
        return pota;
    }
}
