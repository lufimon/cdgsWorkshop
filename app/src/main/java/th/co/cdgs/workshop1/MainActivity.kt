package th.co.cdgs.workshop1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_local).apply {
            setOnClickListener {

            }
        }
        findViewById<Button>(R.id.btn_remote).apply {
            setOnClickListener {

            }
        }
    }
}
