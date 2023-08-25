package io.xxx.bamboo.specs.plans

import com.atlassian.bamboo.specs.api.BambooSpec
import com.atlassian.bamboo.specs.api.builders.permission.*
import com.atlassian.bamboo.specs.api.builders.plan.*
import com.atlassian.bamboo.specs.api.builders.project.Project
import com.atlassian.bamboo.specs.builders.trigger.RemoteTrigger
import com.atlassian.bamboo.specs.builders.trigger.RepositoryPollingTrigger
import io.xxx.bamboo.specs.plans.repositories.PrimaryProjectRepository
import io.xxx.bamboo.specs.plans.stages.BuildPublishAndTestStage

@BambooSpec
class ContinuousIntegrationPlanSpec {
  companion object {
    // TODO: Create your project in the Bamboo UI and assign it the
    //       appropriate permissions that will be inherited by it's
    //       plans. Finally, update the PROJECT_KEY constant.
    const val PROJECT_KEY = "TFT"
    const val PLAN_NAME = "CI - Standalone"
    const val PLAN_KEY = "CIS"
  }

  internal fun plan(): Plan {
    return Plan(project(), PLAN_NAME, PLAN_KEY)
      .planRepositories(PrimaryProjectRepository())
      .planBranchManagement(DefaultPlanBranchManagement())
      .stages(BuildPublishAndTestStage())
      .triggers(
        // TODO: Create a webhook in your BitBucket repository in order to trigger builds following the instructions
        //       found here https://confluence.atlassian.com/bamboo/triggering-a-bamboo-build-from-bitbucket-cloud-using-webhooks-873949130.html
        RemoteTrigger()
          .triggerIPAddresses(
            // This list was retrieved from https://support.atlassian.com/organization-administration/docs/ip-addresses-and-domains-for-atlassian-cloud-products/#AtlassiancloudIPrangesanddomains-OutgoingConnections
            "13.52.5.96/28," +
              "13.236.8.224/28," +
              "18.136.214.96/28," +
              "18.184.99.224/28," +
              "18.234.32.224/28," +
              "18.246.31.224/28," +
              "52.215.192.224/28," +
              "104.192.137.240/28," +
              "104.192.138.240/28," +
              "104.192.140.240/28," +
              "104.192.142.240/28," +
              "104.192.143.240/28," +
              "185.166.143.240/28," +
              "185.166.142.240/20," +
              "10.27.0.4"
          )
      )
  }

  private fun project(): Project {
    return Project()
      .key(PROJECT_KEY)
  }

  internal fun permissions(): PlanPermissions {
    return PlanPermissions(PlanIdentifier(PROJECT_KEY, PLAN_KEY))
      .permissions(Permissions())
  }
}
