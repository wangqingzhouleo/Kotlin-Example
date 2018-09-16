package com.example.leo.githook.Models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

class User(): Parcelable {

    var id = 0
    var name = ""
    var avatarUrl = ""
    var publicRepoCount = 0
    var reposUrl = ""

    constructor(data: JSONObject) : this() {
        id = data.getInt("id")
        name = data.getString("name")
        avatarUrl = data.getString("avatar_url")
        publicRepoCount = data.getInt("public_repos")
        reposUrl = data.getString("repos_url")
    }

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
        avatarUrl = parcel.readString()
        publicRepoCount = parcel.readInt()
        reposUrl = parcel.readString()
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeInt(id)
        p0?.writeString(name)
        p0?.writeString(avatarUrl)
        p0?.writeInt(publicRepoCount)
        p0?.writeString(reposUrl)
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}