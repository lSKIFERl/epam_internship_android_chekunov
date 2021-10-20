package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.repository.CategoryRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetLastCategoryIdUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke(): Single<Long> = repository.getLastCategoryId()
}