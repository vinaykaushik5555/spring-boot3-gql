pipeline {
    agent any

    environment {
        GIT_REPO_URL = 'https://github.com/vinaykaushik5555/spring-boot3-gql'
        GIT_BRANCH = 'master'
        MAVEN_TOOL = 'Maven3'
        DOCKER_HUB_USERNAME = 'vinaykaushik'
        DOCKER_HUB_REPO = 'product-service'
        IMAGE_TAG = "product-service-${BUILD_NUMBER}"
        GIT_CREDENTIALS_ID = 'github-credentials'
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
        KUBECONFIG = '/etc/kubernetes/config'
        KUBE_NAMESPACE = 'pos'
        RELEASE_NAME = 'product-service-gql'
        SERVICE_NAME = 'product-service-gql'
        SERVICE_PORT = '8080'
    }

    tools {
        maven "${MAVEN_TOOL}"
    }

    stages {
        stage('Clone Git Repository') {
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

        stage('Build and Run Unit Tests') {
            steps {
                    sh 'mvn clean test'

            }
        }


        stage('Login to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh "echo ${DOCKER_PASSWORD} | docker login -u ${DOCKER_USER} --password-stdin"
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                    script {
                        sh "docker build -t ${DOCKER_HUB_USERNAME}/${DOCKER_HUB_REPO}:${IMAGE_TAG} ."
                    }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    sh "docker push ${DOCKER_HUB_USERNAME}/${DOCKER_HUB_REPO}:${IMAGE_TAG}"
                }
            }
        }

        stage('Deploying to AKS with Helm') {
            steps {
                dir('product-service-chart') { // Change directory to new Helm chart location
                    script {
                        sh """
                        helm upgrade --install ${RELEASE_NAME} . \
                            --namespace ${KUBE_NAMESPACE} \
                            --set image.repository=${DOCKER_HUB_USERNAME}/${DOCKER_HUB_REPO} \
                            --set image.tag=${IMAGE_TAG} \
                            --set image.pullPolicy=Always
                        """
                    }
                }
            }
        }
    }

    post {
        success {
            echo '🎉 Pipeline executed successfully!'
        }
        failure {
            echo '❌ Pipeline execution failed.'
        }
    }
}
