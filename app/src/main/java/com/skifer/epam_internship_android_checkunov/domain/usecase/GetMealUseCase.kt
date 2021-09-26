package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.repository.MealRepository
import javax.inject.Inject

class GetMealUseCase @Inject constructor(private val repository: MealRepository) {
    operator fun invoke(id: Int) = repository.loadDishDetails(id)
}