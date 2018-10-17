package th.co.cdgs.workshop1.remote

import android.os.Bundle
import android.service.quicksettings.Tile
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import th.co.cdgs.workshop1.R
import th.co.cdgs.workshop1.Utils
import th.co.cdgs.workshop1.Utils.Companion.datePickerDialog
import th.co.cdgs.workshop1.local.data.AppDatabase
import th.co.cdgs.workshop1.local.data.Person

class RemoteAddActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_remote_add)
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
            //todo call retrofit when save
        }

        /**
         * update section
         */
        val data = intent.getSerializableExtra("DATA")
        if (data != null) {
            val person = data as Person
            this@RemoteAddActivity.id = person.id
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

        btnUpdate = findViewById(R.id.btn_update)
        //when press button update
        btnUpdate.setOnClickListener {
            //todo call retrofit when update
        }

        /**
         * delete section
         */
        btnDelete = findViewById(R.id.btn_delete)
        //when press button delete
        btnDelete.setOnClickListener {
            //todo call retrofit when delete
        }
    }
}
