#!groovy

node{
    git 'https://github.com/BitwiseInc/dominos.git'
}
stage 'CLEAN'
node {
    sh 'chmod +x gradlew'
    sh './gradlew clean --info'
}

stage 'BUILD'
node {
    sh 'chmod +x gradlew'
    sh './gradlew build --info'
}

stage 'UNIT_TEST'
node {
    sh 'chmod +x gradlew'
    sh './gradlew test --info'
}

stage 'CLEAN_EXISTINGPORTS'
node{
    sh 'chmod +x gradlew'
    sh './gradlew clearApplicatioPorts'
}

stage 'CODE_COVERAGE'
node {
    sh 'chmod +x gradlew'
    sh './gradlew jacoco --info'
}

stage 'START_APPLICATION'
node {
    sh 'chmod +x gradlew'
    sh './gradlew startApplication'
}

stage 'FUNCTIONAL_TESTS'
node {
    sh 'chmod +x gradlew'
    sh './gradlew functionalTest'
}

stage 'ARCHIVE_ARTIFACTS'
node{
    step([$class: 'ArtifactArchiver', artifacts: '**/build/libs/*.jar', fingerprint: true])
}

input message: "Does build look good. Deploy to stage?"

stage name: 'Staging', concurrency: 1
node {
    echo 'Staging server looks to be alive '
    echo "Deployed to staging"
}

node {
    stage 'deploy3'
}


