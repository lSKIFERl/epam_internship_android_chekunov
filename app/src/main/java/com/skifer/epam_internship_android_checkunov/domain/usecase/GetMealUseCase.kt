package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.entity.MealEntity
import com.skifer.epam_internship_android_checkunov.domain.repository.MealRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMealUseCase @Inject constructor(private val repository: MealRepository) {
    operator fun invoke(id: Int): Single<MealEntity> = repository.loadMealDetails(id)
}