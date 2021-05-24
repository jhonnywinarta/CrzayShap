package tw.edu.pu.o1083023.crazyshape

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game.*



class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val intent = getIntent()
        val shapex: Int = intent.getIntExtra("match", 1)

        when (shapex) {
            1 -> textMsg.text = "Please draw a circle"
            2 -> textMsg.text = "Please draw a square"
            3 -> textMsg.text = "Please draw a star"
            4 -> textMsg.text = "Please draw a triangle"
        }

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
    }
}