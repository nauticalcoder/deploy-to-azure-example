"%bamboo_capability_system_builder_command_MSDeploy_v3%" ^
-verb:sync ^
-source:contentPath="%bamboo_build_working_directory%\api" ^
-dest:contentPath="%bamboo_AppServiceName%",^
wmsvc=https://%bamboo_AppServiceName%.scm.azurewebsites.net:443/msdeploy.axd?site=%bamboo_AppServiceName%,^
username=%bamboo_WebDeployUsername%,^
password=%bamboo_WebDeployPassword% ^
-enableRule:AppOffline
