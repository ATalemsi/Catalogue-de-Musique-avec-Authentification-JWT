pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Pull the latest code from GitHub (or another Git repository)
                git branch: 'main', url: 'https://github.com/your-repo/music-catalog-api.git'
            }
        }

        stage('Build') {
            steps {
                // Build the project using Maven
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Build the Docker image
                sh 'docker build -t music-catalog-api:latest .'
            }
        }

        stage('Push Docker Image') {
            steps {
                // Push the Docker image to DockerHub or another registry
                withCredentials([string(credentialsId: 'Abdo@2023', variable: 'DOCKER_PASSWORD')]) {
                    sh 'echo $DOCKER_PASSWORD | docker login -u abdellahtalemsi --password-stdin'
                }
                sh 'docker tag music-catalog-api:latest abdellahtalemsi/music-catalog-api:latest'
                sh 'docker push abdellahtalemsi/music-catalog-api:latest'
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                // Deploy the application using Docker Compose
                sh 'docker-compose down'
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        always {
            echo 'Cleaning up Docker resources...'
            sh 'docker system prune -f'
        }
    }
}
