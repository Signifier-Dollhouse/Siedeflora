# AGENTS.md

## Project Scope
- This is a NeoForge mod project (`net.neoforged.moddev`) targeting Minecraft `26.1.2` + NeoForge `26.1.2.36-beta` (see `gradle.properties`).
- Java toolchain is pinned to Java 25 in `build.gradle`; do not downgrade language features/toolchain.
- Main mod id is `siedeflora`; use `Siedeflora.id("...")` for namespaced identifiers (`src/main/java/com/signifier/siedeflora/Siedeflora.java`).

## Architecture (What Exists Today)
- Mod entrypoint `Siedeflora` registers deferred registries for blocks, block entities, and items.
- Registration is split by domain:
  - Blocks + block entities: `src/main/java/com/signifier/siedeflora/block/BlockRegistry.java`
  - Items: `src/main/java/com/signifier/siedeflora/item/ItemRegistry.java`
  - Custom game registries: `src/main/java/com/signifier/siedeflora/registry/RegRegistry.java`
- Core gameplay model is centered on a soil tile:
  - `SoilBlock` creates `SoilBlockEntity`
  - `SoilBlockEntity` stores `SoilState` + `CropState`
  - Nutrition/crop progression logic lives under `agriculture/soil` and `agriculture/crop`
- Several systems are scaffolded/incomplete (TODOs and stubs are intentional current state): crop growth function internals, renderer submit path, loot entry codec/expand, datagen event body.

## Data/Control Flow Patterns
- Tick/growth path is intended to be server-side block-entity driven (`SoilBlock#getTicker` -> block entity ticking).
- Crop placement/growth interoperability is wired through `AdvancedCropBlock` and `SoilBlock` (`mayPlaceOn` only accepts `SoilBlock`).
- Custom registries for `SoilTexture` and `Period` are created with `RegistryBuilder(...).sync(true)` and registered on `NewRegistryEvent`.

## Build, Run, and Dev Commands
- Use Windows wrapper from project root:
```powershell
.\gradlew.bat build
.\gradlew.bat runClient
.\gradlew.bat runServer
.\gradlew.bat runData
.\gradlew.bat runGameTestServer
```
- `runData` is configured to emit generated assets into `src/generated/resources` and read existing assets from `src/main/resources` (`build.gradle`, `neoForge.runs.data`).
- If dependencies or IDE sync get weird, the README-prescribed recovery flow is:
```powershell
.\gradlew.bat --refresh-dependencies
.\gradlew.bat clean
```

## Resource + Metadata Pipeline
- Mod metadata is template-driven: `src/main/templates/META-INF/neoforge.mods.toml` is expanded by task `generateModMetadata`.
- Replacement values come from Gradle properties (`mod_id`, versions, license, etc.); output is `build/generated/sources/modMetadata` and included as a resource source set.
- Main resources include `src/main/resources` plus `src/generated/resources` (and build excludes `.bbmodel` + datagen cache files).

## Code Conventions Specific to This Repo
- Registry holders are mostly `interface` containers with `public static final` deferred objects.
- Block items are mirrored from blocks via helper method (`ItemRegistry.registerSimpleBlockItem`) to keep ids consistent.
- Package-level nullness defaults are used via `package-info.java` in key packages; preserve these defaults when adding new classes.
- Keep new content aligned with current package boundaries (`block`, `block.entity`, `item`, `agriculture.*`, `registry`, `datagen`).

## Integration Notes for Agents
- There are no project tests checked in (`src/test` is absent); validation is primarily compile/run tasks.
- The `run/` directory is an active dev runtime workspace (configs, logs, saves); treat it as environment output, not source of truth.
- `EnhancedModelProvider` is a custom utility for client item/model datagen and intentionally disables known-registry validation (`getKnownBlocks/getKnownItems` return empty streams).

