package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.entity.Sort
import com.skifer.epam_internship_android_checkunov.domain.repository.SortingRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class SetSortUseCase @Inject constructor(private val repository: SortingRepository) {
    operator fun invoke(sort: Sort): Completable = repository.setSort(sort)
}