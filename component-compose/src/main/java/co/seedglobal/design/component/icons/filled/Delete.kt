package co.seedglobal.design.component.icons.filled

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

public val Icons.Filled.Delete: ImageVector
    get() {
        if (_delete != null) {
            return _delete!!
        }
        _delete = Builder(name = "Delete", defaultWidth = 48.0.dp, defaultHeight = 48.0.dp,
            viewportWidth = 48.0f, viewportHeight = 48.0f).apply {
            path(fill = SolidColor(Color(0xFF1F2024)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero) {
                moveTo(38.28f, 10.7f)
                curveTo(30.68f, 3.1f, 18.3f, 3.1f, 10.7f, 10.7f)
                curveTo(3.1f, 18.3f, 3.1f, 30.68f, 10.7f, 38.28f)
                curveTo(18.3f, 45.88f, 30.68f, 45.88f, 38.28f, 38.28f)
                curveTo(45.88f, 30.68f, 45.88f, 18.3f, 38.28f, 10.7f)
                close()
                moveTo(28.73f, 30.85f)
                lineTo(24.49f, 26.61f)
                lineTo(20.25f, 30.85f)
                curveTo(19.97f, 31.13f, 19.59f, 31.29f, 19.19f, 31.29f)
                curveTo(18.79f, 31.29f, 18.41f, 31.13f, 18.13f, 30.85f)
                curveTo(17.85f, 30.57f, 17.69f, 30.19f, 17.69f, 29.79f)
                curveTo(17.69f, 29.39f, 17.85f, 29.01f, 18.13f, 28.73f)
                lineTo(22.37f, 24.49f)
                lineTo(18.13f, 20.25f)
                curveTo(17.85f, 19.97f, 17.69f, 19.59f, 17.69f, 19.19f)
                curveTo(17.69f, 18.79f, 17.85f, 18.41f, 18.13f, 18.13f)
                curveTo(18.41f, 17.85f, 18.79f, 17.69f, 19.19f, 17.69f)
                curveTo(19.59f, 17.69f, 19.97f, 17.85f, 20.25f, 18.13f)
                lineTo(24.49f, 22.37f)
                lineTo(28.73f, 18.13f)
                curveTo(29.01f, 17.85f, 29.39f, 17.69f, 29.79f, 17.69f)
                curveTo(30.19f, 17.69f, 30.57f, 17.85f, 30.85f, 18.13f)
                curveTo(31.13f, 18.41f, 31.29f, 18.79f, 31.29f, 19.19f)
                curveTo(31.29f, 19.59f, 31.13f, 19.97f, 30.85f, 20.25f)
                lineTo(26.61f, 24.49f)
                lineTo(30.85f, 28.73f)
                curveTo(31.13f, 29.01f, 31.29f, 29.39f, 31.29f, 29.79f)
                curveTo(31.29f, 30.19f, 31.13f, 30.57f, 30.85f, 30.85f)
                curveTo(30.57f, 31.13f, 30.19f, 31.29f, 29.79f, 31.29f)
                curveTo(29.39f, 31.29f, 29.01f, 31.13f, 28.73f, 30.85f)
                close()
            }
        }
            .build()
        return _delete!!
    }

private var _delete: ImageVector? = null
