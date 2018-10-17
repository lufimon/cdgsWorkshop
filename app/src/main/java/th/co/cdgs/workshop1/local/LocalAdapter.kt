package th.co.cdgs.workshop1.local

import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import th.co.cdgs.workshop1.R
import th.co.cdgs.workshop1.local.data.Person

//constrctor have parameter data list from list
class LocalAdapter : /* two add implement abstract recycleview adapter and three implement funtion */
    RecyclerView.Adapter<LocalAdapter.ViewHolder>() {

    private lateinit var dataList: List<Person>

    fun setDataList(dataList: List<Person>) {
        this.dataList = dataList
    }

    //define view item for create view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //define view item and return view is define
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person_detail, parent, false)
        return ViewHolder(view)
    }

    //define size list item
    override fun getItemCount(): Int {
        //return size data list for loop create item
        return dataList.size
    }

    //define value in item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //after init component item
        val data = dataList[position]
        holder.txtFullName.text = data.firstName.plus(" ").plus(data.lastName)
        holder.txtAge.text = data.birthDay.toString()
        holder.txtGender.text = data.gender

        //after create insert person go to entity add Serializable
        holder.conLayoutItem.setOnClickListener {
            val intent = Intent(holder.itemView.context, LocalAddActivity::class.java)
            intent.putExtra("TITLE", "Update Person")
            intent.putExtra("DATA", data)
            holder.itemView.context.startActivity(intent)
        }
    }

    //first create from create view holder
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //after crate view item and define size item
        val txtFullName = view.findViewById<TextView>(R.id.txt_fullname)
        val txtAge = view.findViewById<TextView>(R.id.txt_age)
        val txtGender = view.findViewById<TextView>(R.id.txt_gender)

        //after create insert person
        val conLayoutItem = view.findViewById<ConstraintLayout>(R.id.con_layout_item)
    }
}
