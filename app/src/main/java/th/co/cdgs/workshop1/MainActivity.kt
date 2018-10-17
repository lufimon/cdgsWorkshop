package th.co.cdgs.workshop1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import th.co.cdgs.workshop1.local.LocalActivity
import th.co.cdgs.workshop1.remote.RemoteActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_local).apply {
            setOnClickListener {
                startActivity(Intent(this@MainActivity, LocalActivity::class.java))
            }
        }
        findViewById<Button>(R.id.btn_remote).apply {
            setOnClickListener {
                startActivity(Intent(this@MainActivity, RemoteActivity::class.java))
            }
        }
    }
}
