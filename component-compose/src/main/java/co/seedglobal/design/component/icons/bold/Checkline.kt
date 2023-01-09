package co.seedglobal.design.component.icons.bold

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import co.seedglobal.design.component.icons.Icons

val Icons.OutlinedBold.CheckLine: ImageVector
    get() {
        if (_checkLine != null) {
            return _checkLine!!
        }
        _checkLine = Builder(name = "checkLine", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
                viewportWidth = 48.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(18.0159f, 37.9841f)
                curveTo(17.5159f, 37.9841f, 17.0159f, 37.7841f, 16.6159f, 37.3841f)
                lineTo(7.6159f, 28.3841f)
                curveTo(6.8159f, 27.5841f, 6.8159f, 26.3841f, 7.6159f, 25.5841f)
                curveTo(8.4159f, 24.7841f, 9.6159f, 24.7841f, 10.4159f, 25.5841f)
                lineTo(17.9159f, 33.0841f)
                lineTo(37.5159f, 10.6841f)
                curveTo(38.2159f, 9.8841f, 39.5159f, 9.7841f, 40.3159f, 10.4841f)
                curveTo(41.1159f, 11.1841f, 41.2159f, 12.4841f, 40.5159f, 13.2841f)
                lineTo(19.5159f, 37.2841f)
                curveTo(19.1159f, 37.6841f, 18.6159f, 37.9841f, 18.0159f, 37.9841f)
                close()
            }
        }
        .build()
        return _checkLine!!
    }

private var _checkLine: ImageVector? = null
