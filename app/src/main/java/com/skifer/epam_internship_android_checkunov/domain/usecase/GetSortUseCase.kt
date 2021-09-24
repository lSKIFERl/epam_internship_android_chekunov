package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository
import javax.inject.Inject

class GetSortUseCase @Inject constructor(private val repository: MealListRepository) {
    operator fun invoke() = repository.getSort()
}