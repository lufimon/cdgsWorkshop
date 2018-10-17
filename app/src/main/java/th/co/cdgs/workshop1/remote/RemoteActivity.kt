package th.co.cdgs.workshop1.remote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.Button
import th.co.cdgs.workshop1.R
import th.co.cdgs.workshop1.local.LocalAdapter

class RemoteActivity : AppCompatActivity() {

    //after create button in layout activity_remote
    lateinit var btnAddPerson: Button

    //define after create input from success
    lateinit var localAdapter: LocalAdapter

    //define after create adapter success
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_remote)
        title = "Remote"
        this.init()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        //after create button in layout activity_remote when complete go to create RemoteAddActivity
        btnAddPerson = findViewById(R.id.btn_add_person)
        btnAddPerson.setOnClickListener {
            startActivity(Intent(this@RemoteActivity, RemoteAddActivity::class.java))
        }

        //define after create input from success
        localAdapter = LocalAdapter()
        localAdapter.setDataList(arrayListOf())

        //define after create adapter success
        recyclerView = findViewById(R.id.rv_person_detail)
        //define layout in recycle view
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        //todo call retrofit when show detail all
//        localAdapter.setDataList(it as List<Person>)
//        localAdapter.notifyDataSetChanged()
//        recyclerView.adapter = localAdapter

    }
}
