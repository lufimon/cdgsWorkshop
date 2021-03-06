package th.co.cdgs.workshop1.local.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

//1
@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "first_name") var firstName: String? = null,
    @ColumnInfo(name = "last_name") var lastName: String? = null,
    @ColumnInfo(name = "birthDay") var birthDay: String? = null,
    @ColumnInfo(name = "gender") var gender: String? = null
): /*add when update section*/Serializable