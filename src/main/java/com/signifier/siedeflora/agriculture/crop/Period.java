package com.signifier.siedeflora.agriculture.crop;

import com.signifier.siedeflora.agriculture.interact.RYBB;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.AABB;

import java.util.List;

public interface Period {
    Identifier id();

    int stages();

    GrowFunction growth();

    RYBB plant();

    List<RYBB> gains();
}
