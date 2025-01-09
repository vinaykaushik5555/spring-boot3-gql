pipeline {
    agent any
    environment {
        AWS_REGION = 'ap-south-1'
        ECR_REGISTRY = '660334028312.dkr.ecr.ap-south-1.amazonaws.com'
        ECR_REPOSITORY = 'pos'
        IMAGE_TAG = "product-graphql-${BUILD_NUMBER}"
        GIT_REPO_URL = 'https://github.com/vinaykaushik5555/spring-boot3-gql'
        GIT_BRANCH = 'master'
        MAVEN_TOOL = 'Maven3' // Ensure this matches the Maven installation name in Jenkins
        AWS_CREDENTIALS_ID = 'aws-ecr-credentials' // Jenkins credentials ID for AWS
        GIT_CREDENTIALS_ID = 'github-credentials' // Jenkins credentials ID for GitHub
        KUBECONFIG = '/var/lib/jenkins/.kube/config' // Path to kubeconfig file
        KUBE_NAMESPACE = 'pos'
        RELEASE_NAME = 'product'
        SERVICE_NAME = 'product-service' // Update if your service name differs
        SERVICE_PORT = '8080' // Update to your service's port if different
    }
    tools {
        maven "${MAVEN_TOOL}"
    }
    stages {
        stage('Cloning Git Repository') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: "*/${GIT_BRANCH}"]],
                    userRemoteConfigs: [[
                        url: "${GIT_REPO_URL}",
                        credentialsId: "${GIT_CREDENTIALS_ID}"
                    ]]
                ])
            }
        }
        stage('Building Maven Project') {
            steps {
                    sh 'mvn clean package'
            }
        }
        stage('Building Docker Image') {
            steps {
                    script {
                        dockerImage = docker.build("${ECR_REGISTRY}/${ECR_REPOSITORY}:${IMAGE_TAG}")

                }
            }
        }
        stage('Pushing Docker Image to ECR') {
            steps {
                script {
                    docker.withRegistry("https://${ECR_REGISTRY}", "ecr:${AWS_REGION}:${AWS_CREDENTIALS_ID}") {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Approval for Deployment') {
            steps {
                script {
                    // Wait for manual approval before deployment
                    input message: 'Approve deployment to Kubernetes?', ok: 'Deploy'
                }
            }
        }
        stage('Deploying with Helm') {
            steps {
                    script {
                        sh """
                        helm upgrade --install ${RELEASE_NAME} ./product-graphql \
                            --namespace ${KUBE_NAMESPACE} \
                            --set image.repository=${ECR_REGISTRY}/${ECR_REPOSITORY} \
                            --set image.tag=${IMAGE_TAG} \
                            --set image.pullPolicy=Always
                        """
                }
            }
        }
    }
    post {
        success {
            echo 'Pipeline executed successfully.'
        }
        failure {
            echo 'Pipeline execution failed.'
        }
    }
}
