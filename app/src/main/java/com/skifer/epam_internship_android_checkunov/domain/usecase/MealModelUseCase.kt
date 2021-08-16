package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.repository.MealModelRepository

class MealModelUseCase(private val repository: MealModelRepository) {
    operator fun invoke(id: Int) = repository.loadDishDetails(id)
}