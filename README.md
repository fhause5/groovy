# Jenkins Libraries
This repository includes helper functions for making life easier with Jenkins and Jenkinsfiles.

## Usage
Place this at the top of your Jenkinsfile:
```
@Library('github.com/fhause5/groovy') _
```
_(Note the trailing underscore!)_

## Function Reference
Available functions:
* [JenkinsApi](#JenkinsApi)
* [Tellegram](#tellegram)
* [Terraform](#terraform)
* [AWS](#aws)

### JenkinsApi

```
def build = jenkinslastBuild("http://192.168.0.108:8080","api_nexus", "admin:admin")
println("The last build was ${build}")
```
### Tellegram

* chat_id = 5587421054
* bot_id = 868953939:AAG6eBwoDQXaXa5fuP_P8V4E_Y-3LdC8Niiw
* message = The build is faill
Usage:
```
send_telegram_message('5587421054', '868953939:AAG6eBwoDQXaXa5fuP_P8V4E_Y-3LdC8Niiw', "The build is fail" )
```
### Terraform
Run Terraform commands in a Jenkinsfile. See https://terraform.io

This command requires the Docker Pipeline plugin and a working Docker server.

Usage:
```
Terraform(command, version)
```

The version defaults to the latest version.

The following environment variables, if set, are passed to Terraform: `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, `AWS_DEFAULT_REGION`, `TF_INPUT`, `TF_LOG`, `TF_LOG_PATH`, `TF_CLI_ARGS`.

Example:
```
Terraform("plan")
Terraform("apply -var-file=environment.tfvars")
Terraform("validate", "0.11.11")
```

### AWS
Run AWS CLI commands in a Jenkinsfile.

This command requires the Docker Pipeline plugin and a working Docker server.

Usage:
```
AWS(command)
```

The following environment variables, if set, are passed to AWS CLI: `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, `AWS_DEFAULT_REGION`.

Example:
```
withEnv(["AWS_ACCESS_KEY_ID=abc", "AWS_SECRET_ACCESS_KEY=def", "AWS_DEFAULT_REGION=eu-west-1"]) {
    AWS("ec2 describe-instances")
}

AWS("s3 ls")
```


