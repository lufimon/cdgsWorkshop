package th.co.cdgs.workshop1.local.data

import android.arch.persistence.room.*

//2
@Dao
interface PersonDao {

    @Insert
    fun insertPerson(person: Person)

    @Update
    fun updatePerson(person: Person)

    @Delete
    fun deletePerson(person: Person)

    @Query("SELECT * FROM person")
    fun getPersonAll(): List<Person>

    @Query("SELECT * FROM person WHERE person.id = :id")
    fun getPersonById(id: Int): Person

    //add delete section
    @Query("DELETE FROM person WHERE person.id = :id")
    fun deleteById(id: Int)
}