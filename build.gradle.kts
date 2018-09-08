import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    java
}

val processResources by tasks.existing<ProcessResources>()

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
}

dependencies {
    compileOnly("org.bukkit:bukkit:1.13.1-R0.1-SNAPSHOT")
}

processResources.configure {
    from(sourceSets["main"].resources.srcDirs) {
        filter(mapOf("tokens" to mapOf("version" to version)), ReplaceTokens::class.java)
    }
}

inline fun <reified T : Task> TaskContainer.existing() = existing(T::class)
inline fun <reified T : Task> TaskContainer.register(name: String, configuration: Action<in T>) = register(name, T::class, configuration)
