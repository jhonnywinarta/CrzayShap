package tw.edu.pu.o1083023.crazyshape

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class HandPaint(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var path : Path = Path()

    init {
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 40f
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.GRAY)
        canvas.drawPath(path, paint)
    }
}
