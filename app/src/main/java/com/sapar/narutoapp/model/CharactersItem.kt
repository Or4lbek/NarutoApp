package com.sapar.narutoapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.ArrayList

@Serializable
data class CharactersItem(
    val age: String,
    @SerializedName("best_jutsus")
    val bestJutsus: ArrayList<String>?,
    @SerializedName("birthdate")
    val birthDate: String?,
    val clan: String?,
    val classifaction: ArrayList<String>?,
    val firstName: String?,
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String?,
    val name: String?,
    val occupation: ArrayList<String>?,
    val village: String?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<CharactersItem> {
        override fun createFromParcel(parcel: Parcel): CharactersItem {
            return CharactersItem(parcel)
        }

        override fun newArray(size: Int): Array<CharactersItem?> {
            return arrayOfNulls(size)
        }
    }
}