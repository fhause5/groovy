def now = new Date()
dumpId = now.getTime()


DB_Names = [
    "DB1",
    "DB2",
]

properties([
  parameters([
    choice(
      choices: DB_Names, 
      description: 'Select DB for dump', 
      name: 'DB_NAME'
    )
    ])
])

dumpFile = 'dump-' + DB_NAME + '-' + dumpId

node("master"){
    deleteDir()
    stage('dump'){
        withCredentials([sshUserPrivateKey(
            credentialsId: '____',
            keyFileVariable: 'KEY'
        )]){
		sh 'ssh -i ${KEY} -L 30017:${ip}27017 ubuntu@{ip} -f sleep 60'
			sh "mongodump --uri 'mongodb://root:pass@localhost:30017/${DB_NAME}?authSource=admin' --gzip --archive=${dumpFile}"
		}
        withCredentials([[
		    $class: 'AmazonWebServicesCredentialsBinding',
		    accessKeyVariable: 'AWS_ACCESS_KEY_ID',
		    credentialsId: '_',
		    secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
	    ]]){
            copy = sh (
                script: "aws s3 cp ${dumpFile} s3://_/${dumpFile}",
                returnStdout: true
            ).trim()

	    }
    }
    deleteDir()
}
