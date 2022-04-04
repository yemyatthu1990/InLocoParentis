package io.github.yemyatthu1990.parent.sort

sealed class SortOrder {
    object ASCENDING: SortOrder()
    object DESCENDING: SortOrder()
}