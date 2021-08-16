package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.repository.TypeModelRepository

class TypeListUseCase(private val repository: TypeModelRepository) {
    operator fun invoke() = repository.loadTypeList()
}