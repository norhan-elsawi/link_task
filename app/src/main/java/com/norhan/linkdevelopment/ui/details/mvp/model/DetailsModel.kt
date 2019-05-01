package com.norhan.linkdevelopment.ui.details.mvp.model

import com.norhan.linkdevelopment.di.scope.ActivityScope
import com.norhan.linkdevelopment.ui.details.mvp.contract.DetailsContract
import com.norhan.linkdevelopment.ui.details.mvp.repository.IDetailsRepository
import com.norhan.linkdevelopment.utils.base.BaseModel
import javax.inject.Inject

@ActivityScope
class DetailsModel @Inject
constructor(commonRepository: IDetailsRepository) : BaseModel(commonRepository), DetailsContract.Model