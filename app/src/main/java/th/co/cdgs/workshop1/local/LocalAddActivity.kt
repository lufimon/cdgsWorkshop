package th.co.cdgs.workshop1.local

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import th.co.cdgs.workshop1.R
import th.co.cdgs.workshop1.Utils
import th.co.cdgs.workshop1.local.data.AppDatabase
import th.co.cdgs.workshop1.local.data.Person

class LocalAddActivity : AppCompatActivity() {

    //after create layout activity_local_add
    lateinit var edtFirstName: TextView
    lateinit var edtLastName: TextView
    lateinit var edtAge: TextView
    lateinit var rdMale: RadioButton
    lateinit var rdFemale: RadioButton
    lateinit var btnSaveAdd: Button
    var gender: String? = null

    //after add btn update layout activity_local_add
    var id: Int? = null
    lateinit var btnUpdate: Button

    //after add btn delete layout activity_local_add
    lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_local_add)
        title = "Add Person"
        //after create layout activity_local_add
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

    //after create layout activity_local_add
    private fun init() {
        edtFirstName = findViewById(R.id.edt_first_name)
        edtLastName = findViewById(R.id.edt_last_name)
        edtAge = findViewById(R.id.edt_age)

        //when select rd male
        rdMale = findViewById(R.id.rd_male)
        rdMale.setOnClickListener {
            this@LocalAddActivity.gender = "Male"
        }

        //when select rd female
        rdFemale = findViewById(R.id.rd_femele)
        rdFemale.setOnClickListener {
            this@LocalAddActivity.gender = "Female"
        }

        btnSaveAdd = findViewById(R.id.btn_save_add)
        //when press button save
        btnSaveAdd.setOnClickListener {
            Utils.Companion.DoAsync({
                Person().apply {
                    firstName = edtFirstName.text.toString()
                    lastName = edtLastName.text.toString()
                    age = edtAge.text.toString().toInt()
                    gender = this@LocalAddActivity.gender
                }.run {
                    AppDatabase.getAppDatabase(this@LocalAddActivity).personDao().insertPerson(this)
                }
            }, {
                finish()
            }).execute()
        }

        /**
         * update section
         */
        val intent = intent.getSerializableExtra("DATA") as Person
        this@LocalAddActivity.id = intent.id
        edtFirstName.text = intent.firstName
        edtLastName.text = intent.lastName
        edtAge.text = intent.age.toString()
        if(intent.gender == "Male"){
            rdMale.isChecked = true
        } else {
            rdFemale.isChecked = true
        }

        btnUpdate = findViewById(R.id.btn_update)
        //when press button update
        btnUpdate.setOnClickListener {
            Utils.Companion.DoAsync({
                Person().apply {
                    id = this@LocalAddActivity.id
                    firstName = edtFirstName.text.toString()
                    lastName = edtLastName.text.toString()
                    age = edtAge.text.toString().toInt()
                    gender = this@LocalAddActivity.gender
                }.run {
                    AppDatabase.getAppDatabase(this@LocalAddActivity).personDao().updatePerson(this)
                }
            }, {
                finish()
            }).execute()
        }

        /**
         * delete section
         */
        btnDelete = findViewById(R.id.btn_delete)
        //when press button delete
        btnDelete.setOnClickListener {
            Utils.Companion.DoAsync({
                AppDatabase.getAppDatabase(this@LocalAddActivity).personDao().deleteById(this@LocalAddActivity.id!!)
            }, {
                finish()
            }).execute()
        }
    }
}
