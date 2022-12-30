package com.example.application.ui.icons.filled

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

public val SampleIcons.Filled.Cart: ImageVector
    get() {
        if (_cart != null) {
            return _cart!!
        }
        _cart = Builder(name = "Cart-fill", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
            viewportWidth = 48.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(16.5f, 42.0f)
                curveTo(18.1569f, 42.0f, 19.5f, 40.6569f, 19.5f, 39.0f)
                curveTo(19.5f, 37.3431f, 18.1569f, 36.0f, 16.5f, 36.0f)
                curveTo(14.8431f, 36.0f, 13.5f, 37.3431f, 13.5f, 39.0f)
                curveTo(13.5f, 40.6569f, 14.8431f, 42.0f, 16.5f, 42.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(36.5f, 42.0f)
                curveTo(38.1569f, 42.0f, 39.5f, 40.6569f, 39.5f, 39.0f)
                curveTo(39.5f, 37.3431f, 38.1569f, 36.0f, 36.5f, 36.0f)
                curveTo(34.8431f, 36.0f, 33.5f, 37.3431f, 33.5f, 39.0f)
                curveTo(33.5f, 40.6569f, 34.8431f, 42.0f, 36.5f, 42.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(41.825f, 11.3231f)
                curveTo(41.6143f, 11.0654f, 41.3488f, 10.8578f, 41.0478f, 10.7154f)
                curveTo(40.7468f, 10.573f, 40.4179f, 10.4994f, 40.085f, 10.5f)
                horizontalLineTo(12.5522f)
                lineTo(11.9775f, 7.2394f)
                curveTo(11.9162f, 6.8921f, 11.7345f, 6.5774f, 11.4643f, 6.3508f)
                curveTo(11.1941f, 6.1241f, 10.8527f, 5.9999f, 10.5f, 6.0f)
                horizontalLineTo(4.5f)
                curveTo(4.1022f, 6.0f, 3.7206f, 6.158f, 3.4393f, 6.4393f)
                curveTo(3.158f, 6.7206f, 3.0f, 7.1022f, 3.0f, 7.5f)
                curveTo(3.0f, 7.8978f, 3.158f, 8.2794f, 3.4393f, 8.5607f)
                curveTo(3.7206f, 8.842f, 4.1022f, 9.0f, 4.5f, 9.0f)
                horizontalLineTo(9.2419f)
                lineTo(13.5225f, 33.2606f)
                curveTo(13.5838f, 33.6079f, 13.7655f, 33.9226f, 14.0357f, 34.1492f)
                curveTo(14.3059f, 34.3759f, 14.6473f, 34.5001f, 15.0f, 34.5f)
                horizontalLineTo(39.0f)
                curveTo(39.3978f, 34.5f, 39.7794f, 34.342f, 40.0607f, 34.0607f)
                curveTo(40.342f, 33.7794f, 40.5f, 33.3978f, 40.5f, 33.0f)
                curveTo(40.5f, 32.6022f, 40.342f, 32.2206f, 40.0607f, 31.9393f)
                curveTo(39.7794f, 31.658f, 39.3978f, 31.5f, 39.0f, 31.5f)
                horizontalLineTo(16.2581f)
                lineTo(15.7294f, 28.5f)
                horizontalLineTo(37.385f)
                curveTo(37.9052f, 28.4993f, 38.4092f, 28.319f, 38.8117f, 27.9894f)
                curveTo(39.2142f, 27.6599f, 39.4904f, 27.2014f, 39.5938f, 26.6916f)
                lineTo(42.2937f, 13.1916f)
                curveTo(42.3588f, 12.8648f, 42.3505f, 12.5278f, 42.2694f, 12.2046f)
                curveTo(42.1884f, 11.8815f, 42.0366f, 11.5804f, 41.825f, 11.3231f)
                close()
            }
        }
            .build()
        return _cart!!
    }

private var _cart: ImageVector? = null
