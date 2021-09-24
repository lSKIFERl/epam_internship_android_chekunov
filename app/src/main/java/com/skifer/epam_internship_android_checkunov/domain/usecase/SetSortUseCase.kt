package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.data.preferences.Sort
import com.skifer.epam_internship_android_checkunov.domain.repository.MealListRepository
import javax.inject.Inject

class SetSortUseCase @Inject constructor(private val repository: MealListRepository) {
    operator fun invoke(sort: Sort) = repository.setSort(sort)
}