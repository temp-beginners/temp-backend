apply(from = "../../docker-build.gradle")

dependencies {
    implementation(project(":application:application-core"))

    implementation(project(":commons:model"))
    implementation(project(":commons:health-check"))
    implementation(project(":commons:logback-appender"))
}
