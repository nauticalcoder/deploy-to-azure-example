package io.xxx.bamboo.specs.plans.jobs

import com.atlassian.bamboo.specs.api.builders.docker.DockerConfiguration
import com.atlassian.bamboo.specs.api.builders.plan.Job
import com.atlassian.bamboo.specs.api.builders.plan.artifact.Artifact
import com.atlassian.bamboo.specs.api.builders.requirement.Requirement
import com.atlassian.bamboo.specs.builders.task.*

class PublishAndTestApiJob : Job("Publish and Test API", "PTA") {
  init {
    dockerConfiguration(
      DockerConfiguration()
        .image("mcr.microsoft.com/dotnet/sdk:7.0"))

    tasks(
      VcsCheckoutTask()
        .description("Checkout Default Repository")
        .addCheckoutOfDefaultRepository(),

      ScriptTask()
        .description("Restore Packages")
        .fileFromPath("build/dotnet/restore-packages.sh"),

      ScriptTask()
        .description("Publish")
        .fileFromPath("build/dotnet/publish-api.sh"),

      ScriptTask()
        .description("Test")
        .fileFromPath("build/dotnet/test.sh"))

    finalTasks(
      TestParserTask.createMSTestParserTask()
        .description("Verify Unit Tests")
        .defaultResultDirectory())

    artifacts(
      Artifact()
        .name("API")
        .copyPattern("**/*")
        .location("artifacts/api")
        .shared(true)
        .required(true))

    requirements(Requirement.equals("OS", "Linux"))

    cleanWorkingDirectory(true)
  }
}
