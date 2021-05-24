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

        imgNext.setOnLongClickListener(object: View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
                intent = Intent(this@MainActivity, GameActivity::class.java)
                startActivity(intent)
                return true
            }
        })
    }
}