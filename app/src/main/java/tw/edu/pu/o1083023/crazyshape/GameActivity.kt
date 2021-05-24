package tw.edu.pu.o1083023.crazyshape

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import org.tensorflow.lite.support.image.TensorImage
import tw.edu.pu.o1083023.crazyshape.ml.Shapes


class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val intent = getIntent()
        val shapex: Int = intent.getIntExtra("match", 1)
        val shape: String? = intent.getStringExtra("shape")

        when (shapex) {
            1 -> textMsg.text = "Please draw a circle"
            2 -> textMsg.text = "Please draw a square"
            3 -> textMsg.text = "Please draw a star"
            4 -> textMsg.text = "Please draw a triangle"
        }

        btnHome.setEnabled(false)

        btnClear.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                handv.path.reset()
                handv.invalidate()
            }
        })

        btnHome.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                finish()
            }
        })

        fun classifyDrawing(bitmap : Bitmap) {
            val model = Shapes.newInstance(this)
            val image = TensorImage.fromBitmap(bitmap)
            val outputs = model.process(image)
                .probabilityAsCategoryList.apply {
                    sortByDescending { it.score }
                }.take(1)
            model.close()
            if (shape == outputs[0].label) {
                Toast.makeText(this,"You drew the right shape!",Toast.LENGTH_SHORT).show()
                btnHome.setEnabled(true)
            }
            else {
                Toast.makeText(this,"Shapes are incorrect!",Toast.LENGTH_SHORT).show()
            }
        }
        handv.setOnTouchListener(object :View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                var xPos = event.getX()
                var yPos = event.getY()
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> handv.path.moveTo(xPos, yPos)
                    MotionEvent.ACTION_MOVE -> handv.path.lineTo(xPos, yPos)
                    MotionEvent.ACTION_UP -> {
                        val b = Bitmap.createBitmap(
                            handv.measuredWidth, handv.measuredHeight,
                            Bitmap.Config.ARGB_8888
                        )
                        val c = Canvas(b)
                        handv.draw(c)
                        classifyDrawing(b)
                    }
                }
                handv.invalidate()
                return true
            }
        })
    }
}