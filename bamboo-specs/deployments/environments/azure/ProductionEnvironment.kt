package io.xxx.bamboo.specs.deployments.environments.azure

import com.atlassian.bamboo.specs.api.builders.Variable
import com.atlassian.bamboo.specs.api.builders.deployment.Environment
import com.atlassian.bamboo.specs.api.builders.requirement.Requirement
import com.atlassian.bamboo.specs.builders.task.*
import com.atlassian.bamboo.specs.model.task.ScriptTaskProperties
import io.xxx.bamboo.specs.deployments.PRODUCTION_ENVIRONMENT_NAME
import java.nio.file.Paths

class ProductionEnvironment : Environment(PRODUCTION_ENVIRONMENT_NAME) {
  companion object {
    const val DATABASE_SERVER = ""
    const val DATABASE_NAME = ""
    const val DATABASE_USER = ""
    const val DATABASE_PASSWORD = ""

    const val APP_SERVICE_NAME = ""
    const val WEB_DEPLOY_USERNAME = ""
    const val WEB_DEPLOY_PASSWORD = ""
  }

  init {
    tasks(
      CleanWorkingDirectoryTask(),

      ArtifactDownloaderTask()
        .description("Download release contents")
        .artifacts(
          DownloadItem()
            .artifact("Database Migrations Script"),
          DownloadItem()
            .artifact("API")
            .path("api"),
          DownloadItem()
            .artifact("React App")
            .path("api/wwwroot/react")),

      CommandTask()
        .description("Deploy Database")
        .executable("SQLCMD")
        .argument("-b -d \${bamboo.DatabaseName} -S \${bamboo.DatabaseServer} -U \${bamboo.DatabaseUser} -P \${bamboo.DatabasePassword} -I -i database-migrations.sql"),

      ScriptTask()
        .description("Deploy Web App")
        .interpreter(ScriptTaskProperties.Interpreter.BINSH_OR_CMDEXE)
        .inlineBodyFromPath(Paths.get("src/main/java/io/xxx/bamboo/specs/deployments/environments/azure/deploy-web-app.cmd")))

    finalTasks(CleanWorkingDirectoryTask())

    variables(
      Variable("DatabaseServer", DATABASE_SERVER),
      Variable("DatabaseName", DATABASE_NAME),
      Variable("DatabaseUser", DATABASE_USER),
      Variable("DatabasePassword", DATABASE_PASSWORD),

      Variable("AppServiceName", APP_SERVICE_NAME),
      Variable("WebDeployUsername", WEB_DEPLOY_USERNAME),
      Variable("WebDeployPassword", WEB_DEPLOY_PASSWORD))

    requirements(
      Requirement("system.builder.command.MSDeploy v3"))
  }
}
