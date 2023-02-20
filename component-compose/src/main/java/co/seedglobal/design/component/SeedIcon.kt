package co.seedglobal.design.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toolingGraphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import co.seedglobal.design.core.LocalSeedContentColor

@Composable
fun SeedIcon(
    icon: SeedIconResource,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalSeedContentColor.current,
) {
    SeedIcon(
        painter = icon.painter,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun SeedIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalSeedContentColor.current,
) {
    SeedIcon(
        painter = rememberVectorPainter(imageVector),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun SeedIcon(
    bitmap: ImageBitmap,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalSeedContentColor.current,
) {
    val painter = remember(bitmap) { BitmapPainter(bitmap) }
    SeedIcon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun SeedIcon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalSeedContentColor.current,
) {
    val colorFilter = if (tint == Color.Unspecified) null else ColorFilter.tint(tint)
    val semantics =
        contentDescription?.let {
            Modifier.semantics {
                this.contentDescription = it
                this.role = Role.Image
            }
        } ?: Modifier
    Box(
        modifier
            .toolingGraphicsLayer()
            .defaultSizeFor(painter)
            .paint(painter, colorFilter = colorFilter, contentScale = ContentScale.Fit)
            .then(semantics)
    )
}

fun Int.toIconResource(): SeedIconResource = SeedIconResource(idRes = this)
fun ImageBitmap.toIconResource(): SeedIconResource = SeedIconResource(bitmap = this)
fun ImageVector.toIconResource(): SeedIconResource = SeedIconResource(imageVector = this)
data class SeedIconResource(
    @DrawableRes
    val idRes: Int? = null,
    val bitmap: ImageBitmap? = null,
    val imageVector: ImageVector? = null,
) {
    init {
        if (idRes == null && bitmap == null && imageVector == null)
            throw IllegalArgumentException("one of idRes,bitmap,imageVector must not null")
    }

    val painter: Painter
        @Composable
        get() {
            if (idRes != null) {
                return painterResource(id = idRes)
            }
            if (bitmap != null) {
                return remember(bitmap) { BitmapPainter(bitmap) }
            }
            if (imageVector != null) {
                return imageVector.toPainter()
            }
            throw IllegalArgumentException("one of idRes,bitmap,imageVector must not null")
        }
}

@Composable
fun ImageVector.toPainter() = rememberVectorPainter(this)

private fun Modifier.defaultSizeFor(painter: Painter) =
    this.then(
        if (painter.intrinsicSize == Size.Unspecified || painter.intrinsicSize.isInfinite()) {
            DefaultIconSizeModifier
        } else {
            Modifier
        }
    )

private fun Size.isInfinite() = width.isInfinite() && height.isInfinite()

private val DefaultIconSizeModifier = Modifier.size(24.dp)
