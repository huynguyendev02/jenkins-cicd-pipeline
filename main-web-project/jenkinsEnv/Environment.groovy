//Tools
env.JAVA_HOME="${tool 'JDK17'}"
env.MAVEN_HOME="${tool 'MAVEN3'}"
//Application Setting
env.APP_NAME = 'web-app-spring'
env.APP_TAG = configEnvBasedOnBranch().get(1)
//Deploy Server
env.PROD_IP = '52.90.239.128'
env.UAT_IP = '35.175.254.125'
env.DEV_IP = '35.153.177.44'

env.DEPLOY_CREDENTIALS_ID = 'nginx-server'
env.DEPLOY_IP = configEnvBasedOnBranch().get(2)
env.DEPLOY_USER = 'ubuntu'

//SonarQube
env.SCANNER_HOME ="${tool 'sonarscanner'}"
env.SONAR_SERVER = 'sonarserver'
env.SONAR_PROJECT_NAME = 'sonar-web-app-spring'

//Nexus
env.NEXUS_IP = '172.31.57.240'
env.NEXUS_CREDENTIALS_ID = 'nexuslogin'
env.NEXUS_GROUP_ID = 'ARTIFACTS'
env.NEXUS_DEFAULT_PORT = '8081'
env.RELEASE_REPO = 'web-app-spring-release'
env.UAT_REPO = 'web-app-spring-uat'
env.DEV_REPO = 'web-app-spring-dev'

env.NEXUS_REPO_PUSH = configEnvBasedOnBranch().get(0)

env.NEXUS_ARTIFACT_ID = 'web-app-spring'

env.NEXUS_DOCKER_PROD = '8001'
env.NEXUS_DOCKER_UAT = '8002'
env.NEXUS_DOCKER_DEV = '8003'
env.NEXUS_DOCKER_PORT = configEnvBasedOnBranch().get(3)

//Docker
env.DOCKER_FILE = './docker/Dockerfile'

//Artifact
env.WORKING_DIR_ARTIFACT = '/home/ubuntu/'
env.ARTIFACT_URL = "${env.NEXUS_IP}:${env.NEXUS_DEFAULT_PORT}/repository/${env.NEXUS_REPO_PUSH}/${env.NEXUS_GROUP_ID}/${env.NEXUS_ARTIFACT_ID}/${env.APP_TAG}/${env.APP_NAME}-${env.APP_TAG}.jar"

def configEnvBasedOnBranch() {
    switch(env.BRANCH_NAME) {
        case 'main':
            return [env.RELEASE_REPO, "${BUILD_TIMESTAMP}-${env.GIT_COMMIT}-RELEASE", env.PROD_IP, env.NEXUS_DOCKER_PROD]
        case ~/(uat)(\/)*((\/)*\w+)*/:
            return [env.UAT_REPO, "${BUILD_TIMESTAMP}-UAT-${env.GIT_COMMIT}", env.UAT_IP, env.NEXUS_DOCKER_UAT]
        case ~/(dev)(\/)*((\/)*\w+)*/:
            return [env.DEV_REPO, "${BUILD_TIMESTAMP}-DEV-${env.GIT_COMMIT}", env.DEV_IP, env.NEXUS_DOCKER_DEV]
        default:
            return ['dev', 'dev', 'dev','dev']
        break
    }
}