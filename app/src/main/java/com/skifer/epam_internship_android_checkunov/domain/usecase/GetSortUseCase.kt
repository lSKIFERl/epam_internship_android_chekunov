package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.entity.Sort
import com.skifer.epam_internship_android_checkunov.domain.repository.SortingRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetSortUseCase @Inject constructor(private val repository: SortingRepository) {
    operator fun invoke(): Single<Sort> = repository.getSort()
}