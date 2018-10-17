package th.co.cdgs.workshop1

import android.os.AsyncTask

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
    }
}