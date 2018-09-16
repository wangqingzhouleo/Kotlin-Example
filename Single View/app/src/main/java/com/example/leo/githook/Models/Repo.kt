package com.example.leo.githook.Models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

class Repo() : Parcelable {

    private var _name: String = ""
    var name: String
        get() { return name.toUpperCase() }
        set(value) { _name = value }

    var htmlUrl: String = ""

    constructor(data: JSONObject) : this() {
        name = data.getString("name")
        htmlUrl = data.getString("html_url")
    }

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        htmlUrl = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(htmlUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Repo> {
        override fun createFromParcel(parcel: Parcel): Repo {
            return Repo(parcel)
        }

        override fun newArray(size: Int): Array<Repo?> {
            return arrayOfNulls(size)
        }
    }
}