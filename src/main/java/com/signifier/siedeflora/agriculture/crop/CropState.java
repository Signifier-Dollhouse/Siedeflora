package com.signifier.siedeflora.agriculture.crop;

public class CropState {
    private Crop crop;
    private Period period;
    private int stage;
    private int progressCountdown;

    public void tick() {
        if (this.progressCountdown > 0) {
            this.progressCountdown--;
        }
        else if (this.progressCountdown == 0) {
            this.stage++;
            if (this.stage >= this.period.stages()) {
                this.stage = 0;
                this.pushPeriod();
            }
            this.calcNextProgress();
        }
    }

    private void pushPeriod() {
    }

    private void calcNextProgress() {
        this.progressCountdown = -1;
    }
}
