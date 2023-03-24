package com.example.application.ui.icons.regular

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.example.application.ui.icons.SampleIcons

public val SampleIcons.OutlinedRegular.Cart: ImageVector
    get() {
        if (_cart != null) {
            return _cart!!
        }
        _cart = Builder(name = "Cart-regular", defaultWidth = 48.0.dp, defaultHeight =
        48.0.dp, viewportWidth = 48.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(16.5f, 43.2001f)
                curveTo(14.18f, 43.2001f, 12.3f, 41.32f, 12.3f, 39.0f)
                curveTo(12.3f, 36.68f, 14.18f, 34.8f, 16.5f, 34.8f)
                curveTo(18.82f, 34.8f, 20.7001f, 36.68f, 20.7001f, 39.0f)
                curveTo(20.7001f, 41.32f, 18.82f, 43.2001f, 16.5f, 43.2001f)
                close()
                moveTo(16.5f, 37.5f)
                curveTo(15.67f, 37.5f, 15.0f, 38.1701f, 15.0f, 39.0f)
                curveTo(15.0f, 39.83f, 15.67f, 40.5f, 16.5f, 40.5f)
                curveTo(17.33f, 40.5f, 18.0f, 39.83f, 18.0f, 39.0f)
                curveTo(18.0f, 38.1701f, 17.33f, 37.5f, 16.5f, 37.5f)
                close()
            }
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(36.5f, 43.2001f)
                curveTo(34.18f, 43.2001f, 32.3f, 41.32f, 32.3f, 39.0f)
                curveTo(32.3f, 36.68f, 34.18f, 34.8f, 36.5f, 34.8f)
                curveTo(38.82f, 34.8f, 40.7001f, 36.68f, 40.7001f, 39.0f)
                curveTo(40.7001f, 41.32f, 38.82f, 43.2001f, 36.5f, 43.2001f)
                close()
                moveTo(36.5f, 37.5f)
                curveTo(35.6701f, 37.5f, 35.0f, 38.1701f, 35.0f, 39.0f)
                curveTo(35.0f, 39.83f, 35.6701f, 40.5f, 36.5f, 40.5f)
                curveTo(37.33f, 40.5f, 38.0f, 39.83f, 38.0f, 39.0f)
                curveTo(38.0f, 38.1701f, 37.33f, 37.5f, 36.5f, 37.5f)
                close()
            }
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(38.81f, 27.99f)
                curveTo(39.21f, 27.66f, 39.49f, 27.2f, 39.59f, 26.69f)
                lineTo(42.29f, 13.19f)
                curveTo(42.36f, 12.87f, 42.35f, 12.52f, 42.27f, 12.2f)
                curveTo(42.19f, 11.88f, 42.04f, 11.57f, 41.83f, 11.32f)
                curveTo(41.62f, 11.07f, 41.35f, 10.86f, 41.06f, 10.72f)
                curveTo(40.76f, 10.58f, 40.43f, 10.5f, 40.09f, 10.5f)
                horizontalLineTo(12.55f)
                lineTo(11.97f, 7.24f)
                curveTo(11.84f, 6.52f, 11.22f, 6.0f, 10.49f, 6.0f)
                horizontalLineTo(4.5f)
                curveTo(3.67f, 6.0f, 3.0f, 6.67f, 3.0f, 7.5f)
                curveTo(3.0f, 8.33f, 3.67f, 9.0f, 4.5f, 9.0f)
                horizontalLineTo(9.24f)
                lineTo(13.52f, 33.26f)
                curveTo(13.65f, 33.98f, 14.27f, 34.5f, 15.0f, 34.5f)
                horizontalLineTo(39.0f)
                curveTo(39.83f, 34.5f, 40.5f, 33.83f, 40.5f, 33.0f)
                curveTo(40.5f, 32.17f, 39.83f, 31.5f, 39.0f, 31.5f)
                horizontalLineTo(16.26f)
                lineTo(15.73f, 28.5f)
                horizontalLineTo(37.39f)
                curveTo(37.91f, 28.5f, 38.42f, 28.32f, 38.82f, 27.99f)
                horizontalLineTo(38.81f)
                close()
                moveTo(15.2f, 25.5f)
                lineTo(13.08f, 13.5f)
                horizontalLineTo(39.17f)
                lineTo(36.77f, 25.5f)
                horizontalLineTo(15.2f)
                close()
            }
        }
            .build()
        return _cart!!
    }

private var _cart: ImageVector? = null
