package com.example.roomdataase.data
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.roomdataase.model.User

@Entity
data class Dog(
    @PrimaryKey val dogId: Long,
    val dogOwnerId: Long,
    val name: String,
    val cuteness: Int,
    val barkVolume: Int,
    val breed: String
)

data class DogAndOwner(
    @Embedded val owner: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "dogOwnerId"
    )
    val dog: Dog
)
