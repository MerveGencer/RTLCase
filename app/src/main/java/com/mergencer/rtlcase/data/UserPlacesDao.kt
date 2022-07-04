package com.mergencer.rtlcase.data

import androidx.room.*
import com.mergencer.rtlcase.data.model.UserPlace

@Dao
interface UserPlacesDao {

    /**
     * Returns all places of the user
     */
    @Query("SELECT * FROM user_places")
    fun getUserPlaces(): List<UserPlace>?

    @Query("SELECT * FROM user_places WHERE id == :placeId")
    fun getUserPlaceById(placeId: String): UserPlace?

    /**
     * Inserts a new place item to the table
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userPlace: UserPlace)


    /**
     * Clears all items in the table
     */
    @Query("DELETE from user_places WHERE id == :placeId")
    fun delete(placeId: String)


}
