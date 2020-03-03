String dockerTag = new Date().format('yyyyMMddHHmmss');
node {
    checkout scm

    stage 'test & package'

    sh('./mvnw clean package')

    stage('docker build')

    sh("docker build . -t 149.28.136.60/daily-eat:${dockerTag}")

    stage('docker run')

    sh("docker rm -f daily-eat")
    sh("docker run -it -d --name daily-eat -p 8090:8080 149.28.136.60/daily-eat:${dockerTag}")
}