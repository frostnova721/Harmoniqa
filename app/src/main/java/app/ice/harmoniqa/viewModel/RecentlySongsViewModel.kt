package app.ice.harmoniqa.viewModel

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import app.ice.harmoniqa.pagination.RecentPagingSource
import app.ice.harmoniqa.viewModel.base.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class RecentlySongsViewModel(
    application: Application,
) : BaseViewModel(application) {
    override val tag: String = "RecentlySongsViewModel"

    val recentlySongs =
        Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 20,
            ),
        ) {
            RecentPagingSource(mainRepository)
        }.flow.cachedIn(viewModelScope)
}