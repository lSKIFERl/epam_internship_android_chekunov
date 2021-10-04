package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.repository.CategoryRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class SetLastCategoryIdUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke(id: Long): Completable = repository.setLastCategoryId(id)
}