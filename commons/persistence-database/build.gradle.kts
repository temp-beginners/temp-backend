dependencies {
    implementation(project(":commons:model"))
    implementation(project(":commons:common-util"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Persistence
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")
}
