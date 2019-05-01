package com.norhan.linkdevelopment.utils.base

import javax.inject.Inject

open class BaseModel @Inject constructor(private val commonRepository: CommonIRepository) : BaseIModel {
    override fun isConnectedToNetwork(): Boolean {
        return commonRepository.getLocalDataSource().isConnectedToNetwork()
    }
}