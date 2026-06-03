package com.signifier.siedeflora.agriculture.crop;

import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.AABB;

public interface Period {
    Identifier id();

    int stages();

    GrowFunction growth();

    AABB plant();
}
