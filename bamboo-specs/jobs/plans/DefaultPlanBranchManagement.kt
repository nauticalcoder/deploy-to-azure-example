package io.xxx.bamboo.specs.plans

import com.atlassian.bamboo.specs.api.builders.plan.branches.*

class DefaultPlanBranchManagement : PlanBranchManagement() {
  init {
    createForVcsBranch()

    delete(
      BranchCleanup()
        .whenRemovedFromRepositoryAfterDays(7)
        .whenInactiveInRepositoryAfterDays(7))

    branchIntegration(
      BranchIntegration()
        .integrationBranchKey(ContinuousIntegrationPlanSpec.PLAN_KEY))

    notificationForCommitters()
  }
}
