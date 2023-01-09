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

public val Icons.OutlinedBold.Delete: ImageVector
    get() {
        if (_delete != null) {
            return _delete!!
        }
        _delete = Builder(name = "Delete", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
            viewportWidth = 48.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(24.0f, 4.3f)
                curveTo(13.1f, 4.3f, 4.3f, 13.1f, 4.3f, 24.0f)
                curveTo(4.3f, 34.9f, 13.1f, 43.7001f, 24.0f, 43.7001f)
                curveTo(34.9f, 43.7001f, 43.7001f, 34.9f, 43.7001f, 24.0f)
                curveTo(43.7001f, 13.1f, 34.9f, 4.3f, 24.0f, 4.3f)
                close()
                moveTo(24.0f, 40.3f)
                curveTo(15.0f, 40.3f, 7.7f, 33.0f, 7.7f, 24.0f)
                curveTo(7.7f, 15.0f, 15.0f, 7.7f, 24.0f, 7.7f)
                curveTo(33.0f, 7.7f, 40.3f, 15.0f, 40.3f, 24.0f)
                curveTo(40.3f, 33.0f, 33.0f, 40.3f, 24.0f, 40.3f)
                close()
            }
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(30.4f, 17.6f)
                curveTo(29.8f, 17.0f, 28.8f, 17.0f, 28.1f, 17.6f)
                lineTo(23.9f, 21.8f)
                lineTo(19.7f, 17.6f)
                curveTo(19.1f, 17.0f, 18.1f, 17.0f, 17.4f, 17.6f)
                curveTo(16.7f, 18.2f, 16.8f, 19.2f, 17.4f, 19.9f)
                lineTo(21.6f, 24.1f)
                lineTo(17.4f, 28.3f)
                curveTo(16.8f, 28.9f, 16.8f, 29.9f, 17.4f, 30.6f)
                curveTo(17.7f, 30.9f, 18.1f, 31.1f, 18.5f, 31.1f)
                curveTo(18.9f, 31.1f, 19.3f, 30.9f, 19.6f, 30.6f)
                lineTo(23.8f, 26.4f)
                lineTo(28.0f, 30.6f)
                curveTo(28.3f, 30.9f, 28.7f, 31.1f, 29.1f, 31.1f)
                curveTo(29.5f, 31.1f, 29.9f, 30.9f, 30.2f, 30.6f)
                curveTo(30.8f, 30.0f, 30.8f, 29.0f, 30.2f, 28.3f)
                lineTo(26.0f, 24.1f)
                lineTo(30.2f, 19.9f)
                curveTo(31.1f, 19.2f, 31.1f, 18.2f, 30.4f, 17.6f)
                close()
            }
        }
            .build()
        return _delete!!
    }

private var _delete: ImageVector? = null
