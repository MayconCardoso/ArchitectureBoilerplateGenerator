Overview
=

Recently I started off working for Unicred Mobile Banking as a Senior Android Engineer.

The app had a huge monolithic and legacy codebase written in Java. So, my first task at Unicred was to define a strategy of modularization and refactoring on the app.

We had to separate the app, initially,  into three layers which are: the ```domain```, ```data```, and ```presentation```.

Since the code was very tightly coupled, I decided, at first, to place all application entities within the ```domain layer```.

However, we know how many boilerplate we need to write to create a new ```feature-module```, don't we? So in order to turn this process easier, I built this "architecture-code-generator" to create all of the templates we need on the project for each new feature.

## How to use this generator

TODO

## Creating an empty feature

To start your generator you will need a [ProjectSettings](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/library/src/main/java/com/mctech/architecture/generator/settings/ProjectSettings.kt) instance and a [FeatureSettings](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/library/src/main/java/com/mctech/architecture/generator/settings/FeatureSettings.kt) instance. Here is an example:

```kotlin
fun main() {
    val projectSettings = ProjectSettings(
        basePackageName = Package("com.mctech.architecture")
    )

    val featureSettings = FeatureSettings(
        createDependencyInjectionModules = false,
        projectSettings         = projectSettings,
        fileDuplicatedStrategy  = FileDuplicatedStrategy.Replace,
        presentationViewModel   = PresentationMode.ActivityAndFragment,
    )

    // Here is an empty feature generated
    FeatureGenerator(
        settings    = featureSettings,
        featureName = "FeatureEmpty"
    ).newFeature {}
}
```

All you are going to do is run this ```main function``` and the files are going to be generated.

## Generated files
