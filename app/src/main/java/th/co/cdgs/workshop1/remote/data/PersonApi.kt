package th.co.cdgs.workshop1.remote.data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import th.co.cdgs.workshop1.local.data.Person

//1
interface PersonApi {
    @GET(".json")
    fun getPersonAll(): Call<MutableMap<String, Person>>

    @POST(".json")
    fun insertPerson(@Body person: Person): Call<Unit>

    @PUT("{key}.json")
    fun updatePerson(@Path("key") key: String, @Body person: Person): Call<Unit>

    @DELETE("{key}.json")
    fun deletePerson(@Path("key") key: String): Call<Unit>
}