package tw.edu.pu.o1083023.crazyshape

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import org.tensorflow.lite.support.image.TensorImage
import tw.edu.pu.o1083023.crazyshape.ml.Shapes


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

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var xPos = event.getX()
        var yPos = event.getY()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> path.moveTo(xPos, yPos)
            MotionEvent.ACTION_MOVE -> path.lineTo(xPos, yPos)
            MotionEvent.ACTION_UP -> {
                val b = Bitmap.createBitmap(
                    measuredWidth, measuredHeight,
                    Bitmap.Config.ARGB_8888
                )
                val c = Canvas(b)
                draw(c)
                classifyDrawing(b)
            }
        }
        invalidate()
        return true
    }

    fun classifyDrawing(bitmap : Bitmap) {
        val model = Shapes.newInstance(context)
        val image = TensorImage.fromBitmap(bitmap)
        val outputs = model.process(image)
            .probabilityAsCategoryList.apply {
                sortByDescending { it.score }
            }.take(1)
        var Result:String = ""
        when (outputs[0].label) {
            "circle" -> Result = "Circle"
            "square" -> Result = "Rectangle"
            "star" -> Result = "Star"
            "triangle" -> Result = "Triangle"
        }
        Result += ": " + String.format("%.1f%%", outputs[0].score * 100.0f)

        model.close()
        Toast.makeText(context, Result, Toast.LENGTH_SHORT).show()
    }

}
