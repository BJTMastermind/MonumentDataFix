# Monument Data Fix

A library that enables modders to register customized data fixers.

## What is Data Fixer?

As Mojang said in the readme file, DataFixerUpper is "a set of utilities designed for incremental building, merging, and optimization of data transformations". It is created for converting the game data between different versions of the game.

## What does Monument Data Fix do?

Monument Data Fix provides a set of APIs for modders to create customized data fixers, which allows you to upgrade a save in Minecraft 1.21-1.21.5 to Minecraft 1.21.6+.

## Do I need this mod?

**For general players**: If you don't need to upgrade a modded save, you don't need Monument Data Fix. If you have a modded save to upgrade, and the mod developer states that Monument Data Fix is needed as a dependency, you should install this mod.

**For mod developers**: If you would like to enable your mod saves to be upgraded, make a data fixer mod using Monument Data Fix as a dependency and point out "Monument Data Fix is needed when upgrading saves" in the mod description.

Currently, there isn't a program document for the mod. In order to add your custom data fixers, call `top.qwerty770.monument.datafix.api.DataFixerRegistry$addDataFix(String name, CustomDataFixer dataFixer)` or `top.qwerty770.monument.datafix.api.DataFixerRegistry$addDataFix(String name, Function<DataFixerBuilder, DataFix> dataFixer)` before `net.minecraft.util.datafix.DataFixers$addFixers` is called. If you have questions on the registry, refer to the 1.21 version of Monument Data Fix as an example, because it provides data fixers for two mods by default. You may submit issues on Github.
