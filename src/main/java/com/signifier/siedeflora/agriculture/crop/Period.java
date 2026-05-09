package com.signifier.siedeflora.agriculture.crop;

import com.signifier.siedeflora.agriculture.crop.stage.Stage;
import net.minecraft.resources.Identifier;

import java.util.List;

public interface Period {
    Identifier id();

    List<Stage> stages();
}
