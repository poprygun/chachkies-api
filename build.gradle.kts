import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.openapi.generator") version "5.2.1"
	kotlin("jvm") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"
}

group = "io.microsamples.accelerator"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	val openApiVersion = "1.5.10"
	implementation("org.springdoc:springdoc-openapi-ui:$openApiVersion")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("javax.validation:validation-api:2.0.1.Final")
	implementation("org.jeasy:easy-random-core:5.0.0")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val generatedSourcesDir = "${project.buildDir}/generated-sources"

sourceSets {
	getByName("main") {
		java {
			srcDir("$generatedSourcesDir/src/main/kotlin")
		}
	}
}


openApiGenerate {
	generatorName.set("kotlin-spring")
	invokerPackage.set("io.microsamples.accelerator.chachkies")
	groupId.set("io.microsamples.accelerator.chachkies")
	apiPackage.set("io.microsamples.accelerator.chachkies.api")
	modelPackage.set("io.microsamples.accelerator.chachkies.model")
	generateApiTests.set(false)
	outputDir.set(generatedSourcesDir)
	inputSpec.set("${projectDir}/config/api.yml")
	ignoreFileOverride.set("${projectDir}/.openapi-generator-ignore")
	configOptions.set(
		mapOf(
			"dateLibrary" to "java8",
			"apiSuffix" to "Chachkie",
			"serviceInterface" to "true",
			"enumPropertyNaming" to "UPPERCASE",
			"modelMutable" to "true",
            "serviceImplementation" to "true"
		)
	)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	dependsOn(tasks.openApiGenerate)
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
	}
}