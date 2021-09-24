package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.repository.TypeModelRepository
import javax.inject.Inject

class GetLastCategoryUseCase @Inject constructor(private val repository: TypeModelRepository) {
    operator fun invoke() = repository.getLastType()
}