package th.co.cdgs.workshop1.remote

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import th.co.cdgs.workshop1.R

class RemoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_remote)
        title = "Remote"
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
