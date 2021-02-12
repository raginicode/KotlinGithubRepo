package com.test.kotlingithubrepo.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Repo {

    @SerializedName("items")
    @Expose
    var items: List<Item>? = null
}
