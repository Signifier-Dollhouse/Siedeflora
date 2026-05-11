package com.signifier.siedeflora.agriculture.soil;

import com.signifier.siedeflora.agriculture.NutritionLevel;

public class NutritionValue
{
    private byte value = 0;
    private NutritionLevel level = NutritionLevel.POOR;
    private int restoreCountdown = -1;

    public NutritionValue() {}

    public int value() {
        return this.value;
    }

    public void set(int value) {
        this.value = (byte) value;
    }

    public void add(int increment) {
        this.value += (byte) increment;
        if (this.value > level.maxValue()) {
            this.value = (byte) level.maxValue();
        }
         else if (this.value < 0) {
             this.value = 0;
        }
    }

    public NutritionLevel level() {
        return this.level;
    }

    public void setLevel(NutritionLevel level) {
        this.level = level;
        if (this.value < this.level.minValue())
            this.value = (byte) this.level.minValue();
    }

    public void tick() {
        if (this.restoreCountdown > 0) {
            this.restoreCountdown--;
        }
        else if (this.restoreCountdown == 0) {
            this.add(1);
            this.calcNextRestore();
        }
    }

    private void calcNextRestore() {
        // TODO
        this.restoreCountdown = -1;
    }
}
