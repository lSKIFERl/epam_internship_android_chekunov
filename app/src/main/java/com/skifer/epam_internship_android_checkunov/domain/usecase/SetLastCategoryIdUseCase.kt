package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.repository.CategoryRepository
import javax.inject.Inject

class SetLastCategoryIdUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke(id: Long) = repository.setLastCategoryId(id)
}