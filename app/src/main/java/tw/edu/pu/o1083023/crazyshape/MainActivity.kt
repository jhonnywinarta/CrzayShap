package tw.edu.pu.o1083023.crazyshape

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "Author:黃裕洳", Toast.LENGTH_LONG).show()

        Glide.with(this)
            .load(R.drawable.cover)
            .override(800, 600)
            .into(imgTitle)

        imgNext.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                createShape()
            }
        })

        imgNext.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                startActivity(intent)
                return true
            }
        })
    }

    fun createShape() {
        val start: Int = (1..4).random()
        when (start) {
            1 -> imgNext.setImageResource(R.drawable.circle)
            2 -> imgNext.setImageResource(R.drawable.square)
            3 -> imgNext.setImageResource(R.drawable.star)
            4 -> imgNext.setImageResource(R.drawable.triangle)
        }
        intent = Intent(this@MainActivity, GameActivity::class.java).apply {
            putExtra("match",start)
            if (start == 1) {
                putExtra("shape", "circle")
            } else if (start == 2) {
                putExtra("shape", "square")
            } else if (start == 3) {
                putExtra("shape", "star")
            } else if (start == 4) {
                putExtra("shape", "triangle")
            }
        }
    }
}