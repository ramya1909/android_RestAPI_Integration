package com.example.secondtryrestapidata

import com.google.gson.annotations.SerializedName

final data class ApiResponse (
    @SerializedName("count"         ) var count         : Int?               = null,
    @SerializedName("totalCount"    ) var totalCount    : Int?               = null,
    @SerializedName("page"          ) var page          : Int?               = null,
    @SerializedName("totalPages"    ) var totalPages    : Int?               = null,
    @SerializedName("lastItemIndex" ) var lastItemIndex : Int?               = null,
    @SerializedName("results"       ) var results       : ArrayList<Products> = arrayListOf()


)

data class Products (

    @SerializedName("_id"          ) var Id           : String?           = null,
    @SerializedName("author"       ) var author       : String?           = null,
    @SerializedName("content"      ) var content      : String?           = null,
    @SerializedName("tags"         ) var tags         : ArrayList<String> = arrayListOf(),
    @SerializedName("authorSlug"   ) var authorSlug   : String?           = null,
    @SerializedName("length"       ) var length       : Int?              = null,
    @SerializedName("dateAdded"    ) var dateAdded    : String?           = null,
    @SerializedName("dateModified" ) var dateModified : String?           = null

)