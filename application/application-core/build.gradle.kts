dependencies {
    implementation(project(":commons:model"))
    implementation(project(":commons:persistence-database"))
    implementation(project(":commons:logback-appender"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}
