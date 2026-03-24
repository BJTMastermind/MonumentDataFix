<p align="center"><img src="./fabric/src/main/resources/icon.png" width=96></p>

<h1><p align="center">Easy Data Fix</p></h1>

<p align="center">A library that enables modders to register custom data fixers.</p>

<div align="center">

~~Download on Modrinth~~ (Coming Soon) |
[Download from Releases](https://github.com/BJTMastermind/easy-data-fix/releases)

</div>

## About The Project

**Easy Data Fix** is a small library mod that aids in creation of data fixers for mods, allowing custom mod game data to be converted between different versions of the game safly. The mod wraps the builtin DataFixerUpper classes which are as Mojang says "a set of utilities designed for incremental building, merging, and optimization of data transformations"

## Use

1. Download `easy_data_fix-<loader>-x.x.x+mc1.21.6` from on of the places at the top of this README.
2. Copy the downloaded jar file to your `mods` folder.

## Using The Library In Development

W.I.P

## Getting Started With Development

To get a local copy up and running, follow these simple steps.

### Prerequisites

Ensure you have the following installed on your machine:

* **Java Development Kit (JDK)**: Version 21 or higher.
  * [Download JDK](https://adoptium.net/)
* **Gradle**: Version 9.2 or higher.
  * [Install Gradle](https://gradle.org/install/)
* **Minecraft**: Version 1.21.6

### Build

1. **Clone the repository**
```sh
git clone https://github.com/BJTMastermind/easy-data-fix.git
```

2. Navigate to the project directory
```sh
cd easy-data-fix
```

3. Build the project with Gradle
```sh
./gradlew clean build
```

You can find the built mod at `easy-data-fix/<loader>/build/libs/easy_data_fix-<loader>-x.x.x+mc1.21.6.jar`.

<!-- ## Do I need this mod?

**For general players**: If you don't need to upgrade a modded save, you don't need Monument Data Fix. If you have a modded save to upgrade, and the mod developer states that Monument Data Fix is needed as a dependency, you should install this mod.

**For mod developers**: If you would like to enable your mod saves to be upgraded, make a data fixer mod using Monument Data Fix as a dependency and point out "Monument Data Fix is needed when upgrading saves" in the mod description.

Currently, there isn't a program document for the mod. In order to add your custom data fixers, call `top.qwerty770.monument.datafix.api.DataFixerRegistry$addDataFix(String name, CustomDataFixer dataFixer)` or `top.qwerty770.monument.datafix.api.DataFixerRegistry$addDataFix(String name, Function<DataFixerBuilder, DataFix> dataFixer)` before `net.minecraft.util.datafix.DataFixers$addFixers` is called. If you have questions on the registry, refer to the 1.21 version of Monument Data Fix as an example, because it provides data fixers for two mods by default. You may submit issues on Github. -->