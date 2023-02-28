package com.codinginflow.rayanhamrah.model.network

import javax.inject.Inject

class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource
) {

    val remote = remoteDataSource

}