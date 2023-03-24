package co.seedglobal.design.component.icons.thin

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

public val Icons.OutlinedThin.Delete: ImageVector
    get() {
        if (_delete != null) {
            return _delete!!
        }
        _delete = Builder(name = "Delete", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
            viewportWidth = 48.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(24.0f, 43.2001f)
                curveTo(34.6f, 43.2001f, 43.2001f, 34.6f, 43.2001f, 24.0f)
                curveTo(43.2001f, 13.4f, 34.6f, 4.8f, 24.0f, 4.8f)
                curveTo(13.4f, 4.8f, 4.8f, 13.4f, 4.8f, 24.0f)
                curveTo(4.8f, 34.6f, 13.4f, 43.2001f, 24.0f, 43.2001f)
                close()
                moveTo(24.0f, 7.2f)
                curveTo(33.3f, 7.2f, 40.8f, 14.7f, 40.8f, 24.0f)
                curveTo(40.8f, 33.3f, 33.3f, 40.8f, 24.0f, 40.8f)
                curveTo(14.7f, 40.8f, 7.2f, 33.3f, 7.2f, 24.0f)
                curveTo(7.2f, 14.7f, 14.7f, 7.2f, 24.0f, 7.2f)
                close()
            }
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(17.7001f, 30.3f)
                curveTo(18.0001f, 30.6f, 18.3001f, 30.7f, 18.7001f, 30.7f)
                curveTo(19.1001f, 30.7f, 19.4001f, 30.6f, 19.7001f, 30.3f)
                lineTo(24.0001f, 26.0f)
                lineTo(28.3001f, 30.3f)
                curveTo(28.6001f, 30.6f, 28.9001f, 30.7f, 29.3001f, 30.7f)
                curveTo(29.7001f, 30.7f, 30.0001f, 30.6f, 30.3001f, 30.3f)
                curveTo(30.8001f, 29.8f, 30.8001f, 28.9f, 30.3001f, 28.4f)
                lineTo(26.0001f, 24.1f)
                lineTo(30.3001f, 19.8f)
                curveTo(30.8001f, 19.3f, 30.8001f, 18.4f, 30.3001f, 17.9f)
                curveTo(29.8001f, 17.4f, 28.9001f, 17.4f, 28.4001f, 17.9f)
                lineTo(24.1001f, 22.2f)
                lineTo(19.8001f, 17.9f)
                curveTo(19.3001f, 17.4f, 18.4001f, 17.4f, 17.9001f, 17.9f)
                curveTo(17.4001f, 18.4f, 17.4001f, 19.3f, 17.9001f, 19.8f)
                lineTo(22.2001f, 24.1f)
                lineTo(17.9001f, 28.4f)
                curveTo(17.2001f, 28.9f, 17.2001f, 29.7f, 17.7001f, 30.3f)
                close()
            }
        }
            .build()
        return _delete!!
    }

private var _delete: ImageVector? = null
