package com.example.nasaneo.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Neo(
    @SerializedName("id")
    val id: String,

    @SerializedName("neo_reference_id")
    val refId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("nasa_jpl_url")
    val url: String
) : Serializable
