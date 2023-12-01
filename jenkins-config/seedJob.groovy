def projectGroupOwner = "71766908"
def projectGroupPath = "jenkins-group6642934/main-web-project"
def pipelineLibrary = "https://gitlab.com/jenkins-group6642934/pipeline-scripted-library"
def buildCodeLibrary = "https://gitlab.com/jenkins-group6642934/build-code-library"
def scanCodeLibrary = "https://gitlab.com/jenkins-group6642934/scan-code-library"
def deployAppLibrary = "https://gitlab.com/jenkins-group6642934/deploy-app-library"



multibranchPipelineJob('multibranch-pet-clinic') {
    branchSources {
        branchSource {
            source {
                gitlab {
                    serverName("default")
                    projectOwner(projectGroupOwner)
                    projectPath(projectGroupPath)
                    credentialsId("gitlablogin")
                    traits {
                        WebhookListenerBuildConditionsTrait {
                            alwaysBuildMROpen(true)
                            alwaysBuildMRReOpen(true)
                        }
                        gitLabBranchDiscovery {
                            //Only branches that are not also filed as MRs
                            strategyId(1)
                        }
                    }
                }
            }   discardOldItems {
            numToKeep(5)
            daysToKeep(11)
        }
        }
    }

    factory {
        workflowBranchProjectFactory {
        // Relative location within the checkout of your Pipeline script.
            scriptPath("Jenkinsfile")
        }
    }
    orphanedItemStrategy {
        // Trims dead items by the number of days or the number of items.
     
        defaultOrphanedItemStrategy {
            daysToKeepStr("1")
            numToKeepStr("5")
            pruneDeadBranches(true)
            abortBuilds(false)
        }
        inherit()
    } 

    properties {
        folderLibraries {
            libraries {
                libraryConfiguration {
                    name("pipeline-scripted-library")
                    defaultVersion("main")
                    implicit(false)
                    allowVersionOverride(true)
                    includeInChangesets(true)
                    retriever {
                        modernSCM {
                            scm {
                                git {
                                    remote(pipelineLibrary)
                                    credentialsId('gitlablogin')
                                }
                            }
                        }
                    }
                }
                libraryConfiguration {
                    name("build-code-library")
                    defaultVersion("main")
                    implicit(false)
                    allowVersionOverride(true)
                    includeInChangesets(true)
                    retriever {
                        modernSCM {
                            scm {
                                git {
                                    remote(pipelineLibrary)
                                    credentialsId('gitlablogin')
                                }
                            }
                        }
                    }
                }
                libraryConfiguration {
                    name("scan-code-library")
                    defaultVersion("main")
                    implicit(false)
                    allowVersionOverride(true)
                    includeInChangesets(true)
                    retriever {
                        modernSCM {
                            scm {
                                git {
                                    remote(pipelineLibrary)
                                    credentialsId('gitlablogin')
                                }
                            }
                        }
                    }
                }
                libraryConfiguration {
                    name("deploy-app-library")
                    defaultVersion("main")
                    implicit(false)
                    allowVersionOverride(true)
                    includeInChangesets(true)
                    retriever {
                        modernSCM {
                            scm {
                                git {
                                    remote(pipelineLibrary)
                                    credentialsId('gitlablogin')
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}