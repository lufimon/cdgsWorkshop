package th.co.cdgs.workshop1.remote

import android.os.Bundle
import android.service.quicksettings.Tile
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import th.co.cdgs.workshop1.R
import th.co.cdgs.workshop1.Utils
import th.co.cdgs.workshop1.Utils.Companion.datePickerDialog
import th.co.cdgs.workshop1.local.data.AppDatabase
import th.co.cdgs.workshop1.local.data.Person
import th.co.cdgs.workshop1.remote.data.PersonApi

class RemoteAddActivity : AppCompatActivity() {

    companion object {
        val TAG = RemoteAddActivity::class.java.simpleName
    }

    //after create layout activity_remote_add
    lateinit var edtFirstName: TextView
    lateinit var edtLastName: TextView
    lateinit var edtAge: TextView
    lateinit var rdMale: RadioButton
    lateinit var rdFemale: RadioButton
    lateinit var btnSaveAdd: Button
    var gender: String? = null

    //after add btn update layout activity_remote_add
    var key: String? = null
    lateinit var btnUpdate: Button

    //after add btn delete layout activity_remote_add
    lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_remote_add)
        //after create layout activity_remote_add
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

    //create interface api first
    private fun retrofitBuilder(): PersonApi {
        return Retrofit.Builder()
            .baseUrl("https://traning-3c90a.firebaseio.com/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(PersonApi::class.java)
    }

    //after create layout activity_remote_add
    private fun init() {
        edtFirstName = findViewById(R.id.edt_first_name)
        edtLastName = findViewById(R.id.edt_last_name)
        edtAge = findViewById(R.id.edt_age)
        edtAge.setOnClickListener {
            datePickerDialog(this@RemoteAddActivity, edtAge, edtAge.text.toString())
        }

        //when select rd male
        rdMale = findViewById(R.id.rd_male)
        rdMale.setOnClickListener {
            this@RemoteAddActivity.gender = "Male"
        }

        //when select rd female
        rdFemale = findViewById(R.id.rd_femele)
        rdFemale.setOnClickListener {
            this@RemoteAddActivity.gender = "Female"
        }

        btnSaveAdd = findViewById(R.id.btn_save_add)
        //when press button save
        btnSaveAdd.setOnClickListener {
            Person().apply {
                firstName = edtFirstName.text.toString()
                lastName = edtLastName.text.toString()
                birthDay = edtAge.text.toString()
                gender = this@RemoteAddActivity.gender
            }.run {
                //todo first create when create retrofit connect
                retrofitBuilder().insertPerson(this).enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.i(TAG, t.message)
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        Log.i(TAG, response.message())
                        finish()
                    }

                })
            }
        }

        /**
         * update section
         */
        val data = intent.getSerializableExtra("DATA")
        if (data != null) {
            val person = data as Person
            edtFirstName.text = person.firstName
            edtLastName.text = person.lastName
            edtAge.text = person.birthDay
            if (person.gender == "Male") {
                rdMale.isChecked = true
                this@RemoteAddActivity.gender = "Male"
            } else {
                rdFemale.isChecked = true
                this@RemoteAddActivity.gender = "Female"
            }
        }

        val title = intent.getStringExtra("TITLE")
        this@RemoteAddActivity.title = title ?: "Add Person"

        //store key from firebase
        this@RemoteAddActivity.key = intent.getStringExtra("KEY") ?: null

        btnUpdate = findViewById(R.id.btn_update)
        //when press button update
        btnUpdate.setOnClickListener {
            Person().apply {
                firstName = edtFirstName.text.toString()
                lastName = edtLastName.text.toString()
                birthDay = edtAge.text.toString()
                gender = this@RemoteAddActivity.gender
            }.run {
                //todo second create when create retrofit connect
                retrofitBuilder().updatePerson(this@RemoteAddActivity.key!!, this)
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Log.i(TAG, t.message)
                        }

                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            Log.i(TAG, response.message())
                            finish()
                        }
                    })
            }
        }

        /**
         * delete section
         */
        btnDelete = findViewById(R.id.btn_delete)
        //when press button delete
        btnDelete.setOnClickListener {
            //todo third create when create retrofit connect
            retrofitBuilder().deletePerson(this@RemoteAddActivity.key!!)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.i(TAG, t.message)
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        Log.i(TAG, response.message())
                        finish()
                    }
                })
        }
    }
}
