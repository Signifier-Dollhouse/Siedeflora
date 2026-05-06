package com.signifier.siedeflora.crop;

import com.signifier.siedeflora.crop.stage.Stage;
import net.minecraft.resources.Identifier;

import java.util.List;

public interface Period {
    Identifier id();

    List<Stage> stages();
}
