package co.seedglobal.design.component.util

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlin.math.absoluteValue

private const val itemIndexCapacity = 1000
private const val scrollingThreshold = 30

@Composable
fun ScrollState.CatchScrollUp(onScrollChange: (isScrollUp: Boolean) -> Unit) {
    var isScrollUp by remember { mutableStateOf(false) }
    var lastOffset by remember { mutableStateOf(0) }

    LaunchedEffect(this) {
        snapshotFlow {
            value
        }.map {
            val newOffset = it
            if (newOffset < scrollingThreshold) return@map false

            val offsetDelta = lastOffset.minus(newOffset)

            // threshold 미만은 이전 상태 지속
            if (offsetDelta.absoluteValue < scrollingThreshold) return@map isScrollUp

            val result = offsetDelta < 0
            lastOffset = newOffset
            result
        }
            .distinctUntilChanged()
            .collect {
                isScrollUp = it
                onScrollChange(isScrollUp)
            }
    }
}

@Composable
fun LazyListState.CatchScrollUp(onScrollDirectionChange: (isScrollUp: Boolean) -> Unit) {
    var isScrollUp by remember { mutableStateOf(false) }
    var lastOffset by remember { mutableStateOf(0) }

    LaunchedEffect(this) {
        snapshotFlow {
            firstVisibleItemIndex.plus(1).times(itemIndexCapacity)
                .plus(firstVisibleItemScrollOffset) to firstVisibleItemIndex
        }
            .map {
                val newOffset = it.first
                val itemIndex = it.second

                // 첫 아이템 맨 위에는 항상 보여주자
                if (itemIndex == 0 && newOffset < itemIndexCapacity.plus(scrollingThreshold)) return@map false

                val offsetDelta = lastOffset.minus(newOffset)

                // threshold 미만은 이전 상태 지속
                if (offsetDelta.absoluteValue < scrollingThreshold) return@map isScrollUp

                val result = offsetDelta < 0
                lastOffset = newOffset
                result
            }
            .distinctUntilChanged()
            .collect {
                isScrollUp = it
                onScrollDirectionChange(isScrollUp)
            }
    }
}
