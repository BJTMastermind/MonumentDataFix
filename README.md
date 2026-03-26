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

To setup the data fixers for your mod you will need at least 3 things, a schema class, the renameing fix class, and registering the data fix.

### Setting up the schema

**ModSchemaV0.java**
```java
public class ModSchemaV0 extends NamespacedSchema {
    public ModSchemaV0(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    // Optional: register all entity fixes here.
    @Override
    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema schema) {
        Map<String, Supplier<TypeTemplate>> map = super.registerEntities(schema);
        this.registerSimple(map, "example_mod:mod_entity");
        return map;
    }

    // Optional: register all block entity fixes here.
    @Override
    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema schema) {
        Map<String, Supplier<TypeTemplate>> map = super.registerBlockEntities(schema);
        this.registerSimple(map, "example_mod:mod_block_entity");
        return map;
    }
}
```

### Setup id renaming

**ModRename<Block/Item>Fix.java**
```java
public class ModRename[Block/Item]Fix extends [Block/Item]RenameFix {
    public ModRename[Block/Item]Fix(Schema outputSchema, String name) {
        super(outputSchema, name);
    }

    @Override
    protected String [renameBlock/fixItem](String name) {
        if (name.startsWith("example_mod:")) {
            return switch (name) {
                case "example_mod:mod_[block/item]" -> "example_mod:mod_[block/item]_new_id";
                // Works with renamed id and namespaces
                case "example_mod:mod_[block/item]" -> "new_example_mod:mod_[block/item]_new_id";
                // Also works with just renamed namespace
                default -> name.replace("example_mod:", "new_example_mod:");
            }
        }
        return name;
    }
}
```

### Register the data fix

**ExampleMod.java**
```java
public class ExampleFix implements ModInitializer {
    @Override
    public void onInitialize() {
        DataFixerRegistry.addDataFix("Description of data fixer", builder -> {
            // oldDataValue = The Minecraft data value used in the version your upgrading from.
            Schema schema = builder.addSchema(oldDataValue, ModSchemaV0::new);
            builder.addFixer(new ModBlockRenameFix(schema, "example_mod:mod_block"));
            builder.addFixer(new ModItemRenameFix(schema, "example_mod:mod_item"));
        });
    }
}
```

You have now completed setting up your own data fixer for your mod.

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

Currently, there isn't a program document for the mod. In order to add your custom data fixers, call `me.bjtmastermind.easy_data_fix.api.DataFixerRegistry$addDataFix(String name, CustomDataFixer dataFixer)` or `me.bjtmastermind.easy_data_fix.api.DataFixerRegistry$addDataFix(String name, Function<DataFixerBuilder, DataFix> dataFixer)` before `net.minecraft.util.datafix.DataFixers$addFixers` is called. If you have questions on the registry, refer to the 1.21 version of Monument Data Fix as an example, because it provides data fixers for two mods by default. You may submit issues on Github. -->