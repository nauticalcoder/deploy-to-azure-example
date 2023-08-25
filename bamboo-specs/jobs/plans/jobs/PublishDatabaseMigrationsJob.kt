package io.xxx.bamboo.specs.plans.jobs

import com.atlassian.bamboo.specs.api.builders.docker.DockerConfiguration
import com.atlassian.bamboo.specs.api.builders.plan.Job
import com.atlassian.bamboo.specs.api.builders.plan.artifact.Artifact
import com.atlassian.bamboo.specs.api.builders.requirement.Requirement
import com.atlassian.bamboo.specs.builders.task.*

class PublishDatabaseMigrationsJob : Job("Publish Database Migrations", "PDM") {
  init {
    dockerConfiguration(
      DockerConfiguration()
        .image("mcr.microsoft.com/dotnet/sdk:7.0"))

    tasks(
      VcsCheckoutTask()
        .description("Checkout Default Repository")
        .addCheckoutOfDefaultRepository(),

      ScriptTask()
        .description("Install dotnet Tools")
        .fileFromPath("build/dotnet/install-dotnet-tools.sh"),

      ScriptTask()
        .description("Publish Database Migrations")
        .fileFromPath("build/dotnet/publish-database-migrations.sh"))

    artifacts(
      Artifact()
        .name("Database Migrations Script")
        .copyPattern("database-migrations.sql")
        .location("artifacts")
        .shared(true)
        .required(true))

    requirements(Requirement.equals("OS", "Linux"))

    cleanWorkingDirectory(true)
  }
}
