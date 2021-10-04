package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.entity.MealListItemEntity
import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMealListUseCase @Inject constructor(private val repository: MealListRepository) {
    operator fun invoke(category: String): Single<List<MealListItemEntity>> = repository.loadMealList(category)
}