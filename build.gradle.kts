plugins {
    java
}

group = "dev.snowz.anoplogin"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks {
    processResources {
        filesMatching("**/plugin.yml") {
            expand("project" to project)
        }
    }
}
