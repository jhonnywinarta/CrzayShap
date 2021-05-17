package tw.edu.pu.o1083023.crazyshape

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game.*



class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
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