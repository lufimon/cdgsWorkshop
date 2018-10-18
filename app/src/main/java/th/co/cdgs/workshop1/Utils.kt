package th.co.cdgs.workshop1

import android.app.DatePickerDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.TextView
import java.util.*

class Utils {
    companion object {
        class DoAsyncCallBack(private val handler: () -> Any, private val callback: (Any?) -> Unit) :
            AsyncTask<Unit, Unit, Any>() {
            override fun doInBackground(vararg params: Unit?): Any? {
                return handler()
            }

            override fun onPostExecute(result: Any?) {
                callback(result)
            }
        }

        class DoAsync(private val handler: () -> Unit, private val callback: () -> Unit) :
            AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                handler()
            }

            override fun onPostExecute(result: Unit?) {
                callback()
            }
        }

        fun datePickerDialog(context: Context, txtDate: TextView, date: String?) {
            var years: Int? = null
            var month: Int? = null
            var day: Int? = null

            if (date.isNullOrEmpty()) {
                val c = Calendar.getInstance()
                years = c.get(Calendar.YEAR)
                month = c.get(Calendar.MONTH)
                day = c.get(Calendar.DAY_OF_MONTH)
            } else {
                val c = date!!.split("-")
                years = c[0].toInt()
                month = c[1].toInt() - 1
                day = c[2].toInt()
            }

            val dpd =
                DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, y, m, d ->
                    txtDate.text = y.toString().plus("-").plus(m + 1).plus("-").plus(d.toString())
                }, years, month, day)
            dpd.show()
        }
    }
}