package com.signifier.siedeflora.agriculture.crop;

import com.signifier.siedeflora.agriculture.soil.SoilState;

public class CropState {
    private Crop crop;
    private Period period;
    private int stage;
    private int progressCountdown;

    public void tick(SoilState soilState) {
        if (this.progressCountdown > 0) {
            this.progressCountdown--;
        }
        else if (this.progressCountdown == 0) {
            this.stage++;
            if (this.stage >= this.period.stages()) {
                this.stage = 0;
                this.pushPeriod(soilState);
            }
            this.calcNextProgress(soilState);
        }
    }

    private void pushPeriod(SoilState soilState) {



    }

    private void calcNextProgress(SoilState soilState) {
        this.progressCountdown = -1;
    }
}
