package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.repository.SortingRepository
import javax.inject.Inject

class GetSortUseCase @Inject constructor(private val repository: SortingRepository) {
    operator fun invoke() = repository.getSort()
}