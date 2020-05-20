Overview
=

[ ![Download](https://api.bintray.com/packages/mayconcardoso/ArchitectureBoilerplateGenerator/com.mctech.architecture.generator/images/download.svg?version=1.1.4) ](https://bintray.com/mayconcardoso/ArchitectureBoilerplateGenerator/com.mctech.architecture.generator/1.1.4/link)

Recently I started off working for Unicred Mobile Banking as a Senior Android Engineer.

The app had a huge monolithic and legacy codebase written in Java. So, my first task at Unicred was to define a strategy of modularization and refactoring on the app.

We had to separate the app, initially,  into three layers which are: the ```domain```, ```data```, and ```presentation```.

Since the code was very tightly coupled, I decided, at first, to place all application entities within the ```domain layer```.

However, we know how many boilerplate we need to write to create a new ```feature-module```, don't we? ```ViewModel```, ```Activity or Fragment```, ```UseCases```, ```Many interfaces```, ```Datasources and Repositories``` and so on. So in order to turn this process easier, I built this "architecture-code-generator" to create all of the templates we need on the project for each new feature.

## Download Generator

```groovy
implementation 'com.mctech.architecture:generator:1.1.4'
```
## Related Library

[Mvvm Architecture Toolkit](https://github.com/MayconCardoso/Mvvm-Architecture-Toolkit) - It is a personal MVVM architecture library that contains the base codebase generated here.

Creating an empty feature
=

To start your generator you will need a [ProjectSettings](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/library/src/main/java/com/mctech/architecture/generator/settings/ProjectSettings.kt) instance and a [FeatureSettings](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/library/src/main/java/com/mctech/architecture/generator/settings/FeatureSettings.kt) instance. Here is an example:

```kotlin
fun main() {
    val projectSettings = ProjectSettings(
        basePackageName = Package("com.mctech.architecture")
    )

    val featureSettings = FeatureSettings(
        createDependencyInjectionModules = false,
        createBothRemoteAndLocalDataSources = true,
        presentationViewModel   = PresentationMode.ActivityAndFragment,
        projectSettings         = projectSettings,
        fileDuplicatedStrategy  = FileDuplicatedStrategy.Replace
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

### Domain Layer
* [FeatureEmpty.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/domain/src/main/java/com/mctech/architecture/domain/feature_empty/entity/FeatureEmpty.kt)
* [FeatureEmptyService.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/domain/src/main/java/com/mctech/architecture/domain/feature_empty/service/FeatureEmptyService.kt)

### Data Layer
* [FeatureEmptyDataSource.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/data/src/main/java/com/mctech/architecture/data/feature_empty/datasource/FeatureEmptyDataSource.kt)
* [LocalFeatureEmptyDataSource.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/data/src/main/java/com/mctech/architecture/data/feature_empty/datasource/LocalFeatureEmptyDataSource.kt)
* [RemoteFeatureEmptyDataSource.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/data/src/main/java/com/mctech/architecture/data/feature_empty/datasource/RemoteFeatureEmptyDataSource.kt) - Check out ```createBothRemoteAndLocalDataSources``` on ```FeatureSettings```
* [FeatureEmptyRepository.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/data/src/main/java/com/mctech/architecture/data/feature_empty/repository/FeatureEmptyRepository.kt)
* [FeatureEmptyAPI.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/data/src/main/java/com/mctech/architecture/data/feature_empty/api/FeatureEmptyAPI.kt) - Check out ```createBothRemoteAndLocalDataSources``` on ```FeatureSettings```

### Presentation Layer (New Module)
* [settings.gradle](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/settings.gradle) - Add new module on the current project file.
* [build.gradle](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-feature-empty/build.gradle)
* [AndroidManifest.xml](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-feature-empty/src/main/AndroidManifest.xml)
* [strings.xml](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-feature-empty/src/main/res/values/strings.xml)
* [activity_feature_empty.xml](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-feature-empty/src/main/res/layout/activity_feature_empty.xml) - Check out ```PresentationMode``` on ```FeatureSettings```
* [fragment_feature_empty.xml](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-feature-empty/src/main/res/layout/fragment_feature_empty.xml) - Check out ```PresentationMode``` on ```FeatureSettings```
* [FeatureEmptyActivity.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-feature-empty/src/main/java/com/mctech/architecture/feature/feature_empty/FeatureEmptyActivity.kt)- Check out ```PresentationMode``` on ```FeatureSettings```
* [FeatureEmptyFragment.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-feature-empty/src/main/java/com/mctech/architecture/feature/feature_empty/FeatureEmptyFragment.kt) - Check out ```PresentationMode``` on ```FeatureSettings```
* [FeatureEmptyViewModel.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-feature-empty/src/main/java/com/mctech/architecture/feature/feature_empty/FeatureEmptyViewModel.kt)

Creating a complete feature with business and presentation logic 
=

To start your generator you will need a [ProjectSettings](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/library/src/main/java/com/mctech/architecture/generator/settings/ProjectSettings.kt) instance and a [FeatureSettings](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/library/src/main/java/com/mctech/architecture/generator/settings/FeatureSettings.kt) instance. Here is an example:

```kotlin
fun main() {

    ...
    
    FeatureGenerator.newFeature(
        settings    = featureSettings,
        featureName = "ComplexFeature"
    ) {

        // Add fields on entity
        addEntityField(Parameter(
            name = "id", type = Type.Long
        ))

        addEntityField(Parameter(
            name = "name", type = Type.String
        ))

        addEntityField(Parameter(
            name = "anotherFeature", type = Type.CustomType(
                packageValue = "com.mctech.architecture.domain.feature_empty.entity",
                typeReturn = "FeatureEmpty"
            )
        ))


        // Create an use case that will call the repository and delegate it to the data sources and so on.
        addUseCase {
            UseCaseBuilder(
                name        = "LoadAllItemsCase",
                returnType  = Type.ListOfGeneratedEntity,
                isDaggerInjectable = false
            )
        }

        addUseCase {
            UseCaseBuilder(
                name        = "LoadItemDetailCase",
                returnType  = Type.ResultOf(Type.GeneratedEntity),
                parameters  = listOf(
                    Parameter(
                        name = "item",
                        type = Type.GeneratedEntity
                    ),
                    Parameter(
                        name = "simpleList",
                        type = Type.CustomType(
                            packageValue = "com.mctech.architecture.domain.feature_empty.entity",
                            typeReturn = "FeatureEmpty"
                        )
                    )
                ),
                isDaggerInjectable = false
            )
        }

        addLiveData {
            LiveDataBuilder(
                name = "items",
                type = Type.ListOfGeneratedEntity
            )
        }

        addLiveData {
            LiveDataBuilder(
                name = "userName",
                type = Type.String
            )
        }

        addComponentState {
            ComponentStateBuilder(
                name = "listEntities",
                type = Type.ListOfGeneratedEntity
            )
        }

        addUserInteraction {
            UserInteractionBuilder(
                name = "LoadList",
                connectedState = findStateByName("listEntities"),
                connectedUseCase = findUseCaseByName("LoadAllItemsCase")
            )
        }

        addUserInteraction {
            UserInteractionBuilder(
                name = "OpenDetails",
                parameters = listOf(
                    Parameter(
                        name = "item",
                        type = Type.GeneratedEntity
                    )
                ),
                connectedUseCase = findUseCaseByName("LoadItemDetailCase")
            )
        }
    }
}
```

## Generated files

### Domain Layer
* [ComplexFeature.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/domain/src/main/java/com/mctech/architecture/domain/complex_feature/entity/ComplexFeature.kt)
* [ComplexFeatureService.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/domain/src/main/java/com/mctech/architecture/domain/complex_feature/service/ComplexFeatureService.kt) - With delegation methods of use cases
* [LoadAllItemsCase.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/domain/src/main/java/com/mctech/architecture/domain/complex_feature/interaction/LoadAllItemsCase.kt)
* [LoadItemDetailCase.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/domain/src/main/java/com/mctech/architecture/domain/complex_feature/interaction/LoadItemDetailCase.kt)

### Data Layer
* [ComplexFeatureDataSource.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/data/src/main/java/com/mctech/architecture/data/complex_feature/datasource/ComplexFeatureDataSource.kt) - With delegation methods of use cases
* [LocalComplexFeatureDataSource.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/data/src/main/java/com/mctech/architecture/data/complex_feature/datasource/LocalComplexFeatureDataSource.kt) - With delegation methods of use cases
* [RemoteComplexFeatureDataSource.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/data/src/main/java/com/mctech/architecture/data/complex_feature/datasource/RemoteComplexFeatureDataSource.kt) - With delegation methods of use cases
* [ComplexFeatureRepository.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/data/src/main/java/com/mctech/architecture/data/complex_feature/repository/ComplexFeatureRepository.kt) - With delegation methods of use cases
* [ComplexFeatureAPI.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/data/src/main/java/com/mctech/architecture/data/complex_feature/api/ComplexFeatureAPI.kt) - With delegation methods of use cases

### Presentation Layer (New Module)
* [settings.gradle](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/settings.gradle) - Add new module on the current project file.
* [build.gradle](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-complex-feature/build.gradle)
* [AndroidManifest.xml](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-complex-feature/src/main/AndroidManifest.xml)
* [strings.xml](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-complex-feature/src/main/res/values/strings.xml)
* [activity_complex_feature.xml](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-complex-feature/src/main/res/layout/activity_complex_feature.xml)
* [fragment_complex_feature.xml](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-complex-feature/src/main/res/layout/fragment_complex_feature.xml)
* [ComplexFeatureActivity.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-complex-feature/src/main/java/com/mctech/architecture/feature/feature_empty/ComplexFeatureActivity.kt) - With delegation methods of LiveData
* [ComplexFeatureFragment.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-complex-feature/src/main/java/com/mctech/architecture/feature/feature_empty/ComplexFeatureFragment.kt)  - With delegation methods of LiveData
* [ComplexFeatureViewModel.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-complex-feature/src/main/java/com/mctech/architecture/feature/feature_empty/ComplexFeatureViewModel.kt) - With delegation methods of LiveData
* [ComplexFeatureUserInteraction.kt](https://github.com/MayconCardoso/ArchitectureBoilerplateGenerator/blob/master/sample/features/feature-complex-feature/src/main/java/com/mctech/architecture/feature/complex_feature/ComplexFeatureUserInteraction.kt) 

Roadmap
=

* Improve code (Yeah, I know the code is not good, but again, this library was a personal generator before it became open source) :P
* Make the generator easier to be used
* Improve the templates logic.
* Create an extension library and organize all existing functions.
* Create all unit tests.
