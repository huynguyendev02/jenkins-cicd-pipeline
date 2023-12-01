# jenkins-cicd-pipeline
Continuous Integration/Continuous Deployment (CI/CD) pipeline using Jenkins.
## Main Pipeline

  ![architecture drawio](https://github.com/huynguyendev02/jenkins-cicd-pipeline/assets/109943707/5b3026ec-546b-4381-9a21-5803be3f6d9b)
  ![multi-branch-pipeline](https://github.com/huynguyendev02/jenkins-cicd-pipeline/assets/109943707/7e133dbf-c2d3-409c-8be7-193459861602)
![all-stages-main](https://github.com/huynguyendev02/jenkins-cicd-pipeline/assets/109943707/84365bfb-d1c7-4a82-bf8a-d72bf6218453)

## Description
- The pipeline is designed to work in different environments - Production, UAT (User Acceptance Testing), and Dev (Development). The full pipeline is used in Production and UAT, while only the first 4 stages are used in the Dev environment.
- The application can be deployed as a service or as a container, providing flexibility in deployment methods.
- The versions include a stable version, a latest version built with the newest code, and versions identified by a git hash and date.
### Convention
Image:
- `stable`: Stable version of well-health app
- `latest`: Build with newest code, may not work correctly
- `$hash ($githash + 'DDMMYYYY')`: Build with version 'X' pushed by `$githash`, may not work correctly

Deploy method
- As service
- As container
