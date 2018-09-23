package com.example.leo.gitapp.Model

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

class Repo() : Parcelable {

    var id: Int = 0
    var name: String = ""
    var htmlUrl: String = ""
    var openIssuesCount: Int = 0
    var watchers: Int = 0
    var forksCount: Int = 0
    var language: String = ""

    constructor(data: JSONObject) : this() {
        id = data.getInt("id")
        name = data.getString("name")
        htmlUrl = data.getString("html_url")
        openIssuesCount = data.getInt("open_issues_count")
        watchers = data.getInt("watchers")
        forksCount = data.getInt("forks_count")
        language = data.getString("language")
    }

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
        htmlUrl = parcel.readString()
        openIssuesCount = parcel.readInt()
        watchers = parcel.readInt()
        forksCount = parcel.readInt()
        language = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(htmlUrl)
        parcel.writeInt(openIssuesCount)
        parcel.writeInt(watchers)
        parcel.writeInt(forksCount)
        parcel.writeString(language)
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