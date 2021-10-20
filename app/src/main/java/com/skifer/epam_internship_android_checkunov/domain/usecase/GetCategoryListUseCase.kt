package com.skifer.epam_internship_android_checkunov.domain.usecase

import com.skifer.epam_internship_android_checkunov.domain.entity.CategoryEntity
import com.skifer.epam_internship_android_checkunov.domain.repository.CategoryRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke(): Single<List<CategoryEntity>> = repository.getCategoryList()
}