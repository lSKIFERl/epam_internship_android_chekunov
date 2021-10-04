package com.skifer.epam_internship_android_checkunov.data.repository

import com.skifer.epam_internship_android_checkunov.data.preferences.SortingSharedPreferencesSources
import com.skifer.epam_internship_android_checkunov.domain.entity.Sort
import com.skifer.epam_internship_android_checkunov.domain.repository.SortingRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SortingRepositoryImpl @Inject constructor(
    private val prefs: SortingSharedPreferencesSources
): SortingRepository {

    override fun getSort(): Single<Sort> = Single.just(prefs.getSort())

    override fun setSort(sort: Sort): Completable = Completable.fromAction {
        prefs.setSort(sort)
    }
}