package com.signifier.siedeflora.agriculture.crop;

import com.signifier.siedeflora.agriculture.soil.SoilState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CropState {
    private Holder<Crop> crop;
    private Holder<Period> period;
    private GrowData growData;
    private int stage;
    private int progressCountdown;

    public void tick(Level level, BlockPos pos, BlockState state, SoilState soilState) {
        if (this.progressCountdown > 0) {
            this.progressCountdown--;
        }
        else if (this.progressCountdown == 0) {
            this.stage++;
            if (this.stage >= this.period.value().stages()) {
                this.stage = 0;
                this.pushPeriod(soilState);
            }
            this.calcNextProgress(soilState);
        }
    }

    private void pushPeriod(SoilState soilState) {
        this.period = this.period.value().growth().apply(this.growData);
    }

    private void calcNextProgress(SoilState soilState) {





        this.progressCountdown = -1;
    }
}
