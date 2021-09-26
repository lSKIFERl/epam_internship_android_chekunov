package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.repository.CategoryRepository
import javax.inject.Inject

class SetLastCategoryUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke(category: String) = repository.setLastCategory(category)
}