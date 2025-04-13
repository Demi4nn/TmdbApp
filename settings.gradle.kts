pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TmdbApp"
include(":app")
include(":core")
include(":core:network")
include(":core:common")
include(":core:common:network")
include(":core:common:model")
include(":feature")
include(":feature:home")
include(":feature:now_playing")
include(":feature:auth")
