package com.example.rwbydnd.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Suppress("CheckReturnValue")
public val money_bag: ImageVector
    get() {
        if (_money_bag != null) {
            return _money_bag!!
        }
        _money_bag =
            ImageVector.Builder(
                name = "money_bag",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(8.4f, 21f)
                        quadTo(6.13f, 21f, 4.56f, 19.44f)
                        reflectiveQuadTo(3f, 15.6f)
                        quadTo(3f, 14.65f, 3.33f, 13.75f)
                        reflectiveQuadTo(4.25f, 12.13f)
                        lineTo(7.8f, 7.85f)
                        lineTo(5.38f, 3f)
                        horizontalLineTo(18.63f)
                        lineTo(16.2f, 7.85f)
                        lineToRelative(3.55f, 4.28f)
                        quadToRelative(0.6f, 0.72f, 0.93f, 1.63f)
                        reflectiveQuadTo(21f, 15.6f)
                        quadToRelative(0f, 2.28f, -1.57f, 3.84f)
                        reflectiveQuadTo(15.6f, 21f)
                        horizontalLineTo(8.4f)
                        close()
                        moveTo(12f, 16f)
                        quadToRelative(-0.82f, 0f, -1.41f, -0.59f)
                        reflectiveQuadTo(10f, 14f)
                        reflectiveQuadToRelative(0.59f, -1.41f)
                        reflectiveQuadTo(12f, 12f)
                        reflectiveQuadToRelative(1.41f, 0.59f)
                        quadTo(14f, 13.18f, 14f, 14f)
                        reflectiveQuadToRelative(-0.59f, 1.41f)
                        reflectiveQuadTo(12f, 16f)
                        close()
                        moveTo(9.63f, 7f)
                        horizontalLineToRelative(4.75f)
                        lineToRelative(1f, -2f)
                        horizontalLineTo(8.63f)
                        lineToRelative(1f, 2f)
                        close()
                        moveTo(8.4f, 19f)
                        horizontalLineToRelative(7.2f)
                        quadToRelative(1.43f, 0f, 2.41f, -0.99f)
                        reflectiveQuadTo(19f, 15.6f)
                        quadTo(19f, 15f, 18.79f, 14.44f)
                        reflectiveQuadTo(18.2f, 13.43f)
                        lineTo(14.53f, 9f)
                        horizontalLineTo(9.5f)
                        lineTo(5.8f, 13.4f)
                        quadTo(5.43f, 13.85f, 5.21f, 14.43f)
                        reflectiveQuadTo(5f, 15.6f)
                        quadToRelative(0f, 1.42f, 0.99f, 2.41f)
                        reflectiveQuadTo(8.4f, 19f)
                        close()
                    }
                }
                .build()
        return _money_bag!!
    }

private var _money_bag: ImageVector? = null
