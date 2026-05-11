package com.signifier.siedeflora.agriculture.crop;

import com.signifier.siedeflora.agriculture.NutritionLevel;
import com.signifier.siedeflora.agriculture.NutritionType;

import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public record GrowFunction(EnumMap<NutritionType, Nutritious> predicates,
                           Optional<Special> special,
                           EnumMap<GrowType, Period> results) implements Function<GrowData, Period>
{
    @Override
    public Period apply(GrowData growData) {
        if (special().isPresent() && special().get().predicate().test(growData)) {
            return special().get().result();
        }

        boolean allFine = true;
        for (var entry : predicates().entrySet()) {
            GrowType growType = entry.getValue().apply(growData);
            switch (growType) {
                case WEAK -> {return results().get(GrowType.WEAK);}
                case COMMON -> allFine = false;
            }
        }
        return results().get(allFine ? GrowType.FINE : GrowType.COMMON);
    }

    public record Special(Predicate<GrowData> predicate, Period result) {}

    public record Nutritious(List<NutritionLevel> primary,
                             List<NutritionLevel> secondary) implements Function<GrowData, GrowType>
    {
        // TODO
        @Override
        public GrowType apply(GrowData data) {
            return null;
        }
    }
}
