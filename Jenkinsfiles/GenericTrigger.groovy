properties([
        pipelineTriggers([
                GenericTrigger(
                        genericVariables: [
                                [ key: 'repo', value: '$.repository.name', ],
                                [ key: 'action', value: '$.action', ],
                                [ key: 'repo', value: '$.repository.name', ],
                                [ key: 'targetBranch', value: '$.pull_request.base.ref',  ],
                                [ key: 'sourceBranch', value: '$.pull_request.head.ref', ],
                                //    [key: 'ref', value: '$.changes[0].refId',],
                                //    [key: 'type', value: '$.changes[0].type',],
                        ],
                        causeString: '[$repo] PR-$number ($sourceBranch -> $targetBranch) has been $action',
                        token: 'abc123',
                        printContributedVariables: true,
                        printPostContent: true,
                        regexpFilterText: '$repo $targetBranch $sourceBranch $action',
                        regexpFilterExpression: 'testing master develop opened'
                )
        ])
])

node {
    stage ('read properties from webhook json') {
        sh "echo repo"

    }
}
