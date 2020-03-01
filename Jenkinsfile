String dockerTag = dateFormat.format(new Date())
node {
    checkout scm
    stage 'test & package'

    sh('./mvnw clean package')

    stage('docker build')

    sh("docker build . -t 207.148.65.251/daily-eat:${dockerTag}")

    sh(" docker run -it -d --name daily-eat  -p 8080:8080 207.148.65.251/daily-eat:${dockerTag}")
}