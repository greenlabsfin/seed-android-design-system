package com.example.application.ui.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.application.ui.theme.GFSampleTheme
import com.example.application.util.ThemedPreview
import com.greenlabsfin.design.component.GfChip
import com.greenlabsfin.design.component.GfChipDefaults

@Composable
fun ChipScreen() {
    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://cdn.shopify.com/s/files/1/0488/7855/1202/products/mandarin-afourer-g-wcitmaf_2badef46-73f4-438c-829c-dccde6a40f55_1200x.jpg?v=1637122531g")
                .size(Size.ORIGINAL)
                .build()
        )
        GfChip(
            modifier = Modifier.width(200.dp),
            size = GfChip.Size.Large,
            style = GfChip.Style.Pill,
            colors = GfChipDefaults.Colors.neutralOutline,
            text = "귤귤귤귤귤귤귤귤귤귤귤귤귤귤귤",
            leadingImagePainter = painter
        ) {

        }

        GfChip(
            modifier = Modifier.width(200.dp),
            size = GfChip.Size.Large,
            style = GfChip.Style.Rectangle,
            colors = GfChipDefaults.Colors.primary,
            text = "귤귤귤귤귤귤귤귤귤귤귤귤귤귤귤",
            leadingIcon = Icons.Default.Search,
            trailingIcon = Icons.Default.Delete,
        ) {

        }

        GfChip(
            modifier = Modifier.width(200.dp),
            size = GfChip.Size.Large,
            style = GfChip.Style.Pill,
            colors = GfChipDefaults.Colors.primary,
            text = "귤귤귤귤귤귤귤귤귤귤귤귤귤귤귤",
            leadingImagePainter = painter,
            count = 3,
            trailingIcon = Icons.Default.Delete,
        ) {

        }

        GfChip(
            modifier = Modifier.width(200.dp),
            size = GfChip.Size.Large,
            style = GfChip.Style.Pill,
            colors = GfChipDefaults.Colors.neutralOutline,
            text = "귤귤귤귤귤귤귤귤귤귤귤귤귤귤귤",
            leadingImagePainter = painter,
            count = 3,
            isCircleCount = false,
            trailingIcon = Icons.Default.Clear
        ) {

        }
    }
}


@ThemedPreview
@Composable
fun ChipScreenPreview() {
    GFSampleTheme {
        ChipScreen()
    }
}
