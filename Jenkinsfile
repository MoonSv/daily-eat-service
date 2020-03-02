String dockerTag = new Date().format('yyyyMMddHHmmss');
node {
    checkout scm

    stage('git pull')

    sh('git pull')

    stage 'test & package'

    sh('./mvnw clean package')

    stage('docker build')

    sh("docker build . -t 207.148.65.251/daily-eat:${dockerTag}")

    stage('docker run')

    sh("docker rm -f daily-eat")
    sh("docker run -it -d --name daily-eat -p 8090:8080 207.148.65.251/daily-eat:${dockerTag}")
}