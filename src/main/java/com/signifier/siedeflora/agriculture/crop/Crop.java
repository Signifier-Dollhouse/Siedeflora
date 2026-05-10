package com.signifier.siedeflora.agriculture.crop;

import com.signifier.siedeflora.Siedeflora;
import net.minecraft.resources.Identifier;

public interface Crop {
    Crop NONE = new Crop() {
        @Override
        public Identifier id() {
            return Siedeflora.id("none");
        }
    };

    Identifier id();
}
