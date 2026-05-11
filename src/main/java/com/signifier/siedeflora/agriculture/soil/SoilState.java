package com.signifier.siedeflora.agriculture.soil;

public class SoilState
{
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
