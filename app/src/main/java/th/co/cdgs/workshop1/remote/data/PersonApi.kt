package th.co.cdgs.workshop1.remote.data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import th.co.cdgs.workshop1.local.data.Person

//1
interface PersonApi {
    @GET(".json")
    fun getPersonAll(): Call<ResponseBody>

    @POST(".json")
    fun insertPerson(@Body person: Person): Call<ResponseBody>

    @PUT("{key}.json")
    fun updatePerson(@Path("key") key: String, @Body person: Person): Call<ResponseBody>
}