package io.xxx.bamboo.specs.plans.jobs

import com.atlassian.bamboo.specs.api.builders.docker.DockerConfiguration
import com.atlassian.bamboo.specs.api.builders.plan.Job
import com.atlassian.bamboo.specs.api.builders.plan.artifact.Artifact
import com.atlassian.bamboo.specs.api.builders.requirement.Requirement
import com.atlassian.bamboo.specs.builders.task.*

class BuildReactAppJob : Job("Build React App", "BRA") {
  init {
    dockerConfiguration(
      DockerConfiguration()
        .image("node:lts"))

    tasks(
      VcsCheckoutTask()
        .description("Checkout Default Repository")
        .addCheckoutOfDefaultRepository(),

      ScriptTask()
        .description("Restore Packages")
        .fileFromPath("build/react/restore-packages.sh"),

      ScriptTask()
        .description("Build")
        .fileFromPath("build/react/build.sh"))

    artifacts(
      Artifact()
        .name("React App")
        .copyPattern("**/*")
        // TODO: Replace with the path to your compiled React application
        .location("src/<Project Name>.Api/wwwroot/react")
        .shared(true)
        .required(true))

    requirements(Requirement.equals("OS", "Linux"))

    cleanWorkingDirectory(true)
  }
}
