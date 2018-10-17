package th.co.cdgs.workshop1.remote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import th.co.cdgs.workshop1.R
import th.co.cdgs.workshop1.local.LocalAdapter
import th.co.cdgs.workshop1.local.data.Person
import th.co.cdgs.workshop1.remote.data.PersonApi
import java.util.concurrent.TimeUnit

class RemoteActivity : AppCompatActivity() {

    companion object {
        val TAG = RemoteActivity::class.java.simpleName
    }

    //after create button in layout activity_remote
    lateinit var btnAddPerson: Button

    //define after create input from success
    lateinit var remoteAdapter: RemoteAdapter

    //define after create adapter success
    lateinit var recyclerView: RecyclerView

    //data list from remote
    lateinit var dataList: LinkedHashMap<String, Person>


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
        remoteAdapter = RemoteAdapter()
        remoteAdapter.setDataList(mutableMapOf())

        //define after create adapter success
        recyclerView = findViewById(R.id.rv_person_detail)
        //define layout in recycle view
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        retrofitBuilder().getPersonAll().enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e(TAG, t.message)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                //custom type from firebase
                val type = object : TypeToken<LinkedHashMap<String, Person>>() {}.type
                dataList = Gson().fromJson(response.body()?.string(), type)
                dataList.run {
                    remoteAdapter.setDataList(this.toMutableMap())
                    remoteAdapter.notifyDataSetChanged()
                    recyclerView.adapter = remoteAdapter
                }
            }
        })
    }

    //create interface api first
    private fun retrofitBuilder(): PersonApi {
        return Retrofit.Builder()
            .baseUrl("https://traning-3c90a.firebaseio.com/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(PersonApi::class.java)
    }
}
