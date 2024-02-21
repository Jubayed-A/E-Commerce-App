pluginManagement {
    repositories {
        google()
        jcenter()
        mavenCentral()
        gradlePluginPortal()
        maven ( url = "https://jitpack.io" )
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven ( url = "https://jitpack.io" )
    }
}

rootProject.name = "E-com Starter Devsstream"
include(":app")
 