pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

        mavenCentral()
        maven (  "https://maven.aliyun.com/repository/public")
        maven (  "https://maven.aliyun.com/repository/google")
        maven (  "https://repo.huaweicloud.com/repository/maven")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenCentral()
        maven (  "https://maven.aliyun.com/repository/public")
        maven (  "https://maven.aliyun.com/repository/google")
        maven (  "https://repo.huaweicloud.com/repository/maven")
    }
}

rootProject.name = "ActivityLifeCycle"
include(":app")
 