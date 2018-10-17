package th.co.cdgs.workshop1.local

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.Button
import th.co.cdgs.workshop1.R
import th.co.cdgs.workshop1.Utils.Companion.DoAsyncCallBack
import th.co.cdgs.workshop1.local.data.AppDatabase
import th.co.cdgs.workshop1.local.data.Person

class LocalActivity : AppCompatActivity() {

    //after create button in layout activity_local
    lateinit var btnAddPerson: Button
    lateinit var btnSearchPerson: Button

    //define after create input from success
    lateinit var localAdapter: LocalAdapter

    //define after create adapter success
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_local)
        title = "Local"
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
        //after create button in layout activity_local when complete go to create LocalAddActivity
        btnAddPerson = findViewById(R.id.btn_add_person)
        btnAddPerson.setOnClickListener {
            startActivity(Intent(this@LocalActivity, LocalAddActivity::class.java))
        }

        btnSearchPerson = findViewById(R.id.btn_search_person)
        btnSearchPerson.setOnClickListener {

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
        //when onResume to query person all to show
        DoAsyncCallBack({
            AppDatabase.getAppDatabase(this).personDao().getPersonAll()
        }, {
            localAdapter.setDataList(it as List<Person>)
            localAdapter.notifyDataSetChanged()
            recyclerView.adapter = localAdapter
        }).execute()
    }
}
