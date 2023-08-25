# deploy-to-azure-example

This repo containts a sanitized example of how we deploy a .Net app to an Azure App Service via Bamboo.  It is not intended to be a complete working example but rather a reference.  One key part of this deployment to be aware of is we are hosting the static React app under the API on one app service.  You will see in the `BuildReactAppJob.kt` file that we create an artifact of the React build and put it in the `src/<Project Name>.Api/wwwroot/react` folder.  When this artifact is downloaded in the `ProductionEnvironment` deployment, it is placed in the `api/wwwroot/react` folder which is where the API's Single Page Application Controller expects it to be.

Microsoft provides some examples of how to do a similar deployment using a cannned GitHub Actions step named `azure/webapps-deploy@v2`.  This mehtod will likely require an Azure Service Principle to be created for the deployment to run under.  In the method we are currently using which is a bit dated, just a username and password provided by the App Service are needed.

https://learn.microsoft.com/en-us/azure/app-service/deploy-github-actions?tabs=applevel

For the database deployment, we are generating a migration script using the Entity Framework tool and then executing that script against the database.  You will probably want a separate user for this to run under with elevated privileges.  A new method that Microsoft has introduced uses an `efbundle` which is self contained executable that can be run against the database that is responsble for running the needed migrations.  Reach out to me if you want more info on that.  The downside to that is you can't see the script that is being run and it is a black box.
