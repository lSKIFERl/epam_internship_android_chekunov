package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.entity.Sort
import com.skifer.epam_internship_android_checkunov.domain.repository.SortingRepository
import javax.inject.Inject

class SetSortUseCase @Inject constructor(private val repository: SortingRepository) {
    operator fun invoke(sort: Sort) = repository.setSort(sort)
}