package app.ice.harmoniqa.pagination

sealed class PagingActions<T> {
    data class Insert<T>(
        val item: T,
    ) : PagingActions<T>()

    data class Remove<T>(
        val item: T,
    ) : PagingActions<T>()
}