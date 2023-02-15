package co.seedglobal.design.component.icons.regular

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

public val Icons.OutlinedRegular.Close: ImageVector
    get() {
        if (_close != null) {
            return _close!!
        }
        _close = Builder(name = "Close", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
                viewportWidth = 48.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(21.4462f, 24.0027f)
                lineTo(11.1681f, 34.2908f)
                curveTo(10.4681f, 34.9908f, 10.4632f, 36.1357f, 11.1632f, 36.8457f)
                curveTo(11.8632f, 37.5457f, 13.0081f, 37.5408f, 13.7181f, 36.8408f)
                lineTo(23.9962f, 26.5527f)
                lineTo(34.2881f, 36.8346f)
                curveTo(34.9881f, 37.5446f, 36.1238f, 37.5403f, 36.8238f, 36.8303f)
                curveTo(37.5338f, 36.1303f, 37.538f, 34.9946f, 36.828f, 34.2946f)
                lineTo(26.5462f, 24.0027f)
                lineTo(36.8234f, 13.7155f)
                curveTo(37.5234f, 13.0155f, 37.5282f, 11.8707f, 36.8282f, 11.1607f)
                curveTo(36.4782f, 10.8107f, 36.0135f, 10.6355f, 35.5535f, 10.6355f)
                curveTo(35.0935f, 10.6355f, 34.6355f, 10.8135f, 34.2855f, 11.1635f)
                lineTo(23.9962f, 21.4527f)
                lineTo(13.7036f, 11.1601f)
                curveTo(13.3536f, 10.8101f, 12.8955f, 10.6321f, 12.4355f, 10.6321f)
                curveTo(11.9755f, 10.6321f, 11.5178f, 10.8144f, 11.1678f, 11.1644f)
                curveTo(10.4678f, 11.8644f, 10.4656f, 13.0022f, 11.1656f, 13.7122f)
                lineTo(21.4462f, 24.0027f)
                close()
            }
        }
        .build()
        return _close!!
    }

private var _close: ImageVector? = null
