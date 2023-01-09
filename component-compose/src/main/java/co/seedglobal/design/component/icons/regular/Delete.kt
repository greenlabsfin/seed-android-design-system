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

public val Icons.OutlinedRegular.Delete: ImageVector
    get() {
        if (_delete != null) {
            return _delete!!
        }
        _delete = Builder(name = "Delete", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
            viewportWidth = 48.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(24.0f, 4.5f)
                curveTo(13.25f, 4.5f, 4.5f, 13.25f, 4.5f, 24.0f)
                curveTo(4.5f, 34.75f, 13.25f, 43.5f, 24.0f, 43.5f)
                curveTo(34.75f, 43.5f, 43.5f, 34.75f, 43.5f, 24.0f)
                curveTo(43.5f, 13.25f, 34.75f, 4.5f, 24.0f, 4.5f)
                close()
                moveTo(24.0f, 40.5f)
                curveTo(14.9f, 40.5f, 7.5f, 33.1f, 7.5f, 24.0f)
                curveTo(7.5f, 14.9f, 14.9f, 7.5f, 24.0f, 7.5f)
                curveTo(33.1f, 7.5f, 40.5f, 14.9f, 40.5f, 24.0f)
                curveTo(40.5f, 33.1f, 33.1f, 40.5f, 24.0f, 40.5f)
                close()
            }
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(30.36f, 17.64f)
                curveTo(29.77f, 17.05f, 28.82f, 17.05f, 28.24f, 17.64f)
                lineTo(24.0f, 21.88f)
                lineTo(19.76f, 17.64f)
                curveTo(19.17f, 17.05f, 18.22f, 17.05f, 17.64f, 17.64f)
                curveTo(17.05f, 18.23f, 17.05f, 19.18f, 17.64f, 19.76f)
                lineTo(21.88f, 24.0f)
                lineTo(17.64f, 28.24f)
                curveTo(17.05f, 28.83f, 17.05f, 29.78f, 17.64f, 30.36f)
                curveTo(17.93f, 30.65f, 18.32f, 30.8f, 18.7f, 30.8f)
                curveTo(19.08f, 30.8f, 19.47f, 30.65f, 19.76f, 30.36f)
                lineTo(24.0f, 26.12f)
                lineTo(28.24f, 30.36f)
                curveTo(28.53f, 30.65f, 28.92f, 30.8f, 29.3f, 30.8f)
                curveTo(29.68f, 30.8f, 30.07f, 30.65f, 30.36f, 30.36f)
                curveTo(30.95f, 29.77f, 30.95f, 28.82f, 30.36f, 28.24f)
                lineTo(26.12f, 24.0f)
                lineTo(30.36f, 19.76f)
                curveTo(30.95f, 19.17f, 30.95f, 18.22f, 30.36f, 17.64f)
                close()
            }
        }
            .build()
        return _delete!!
    }

private var _delete: ImageVector? = null
