package com.people_interactive.test.models

data class LocationModel(
    val city : String,
    val state : String,
    val country : String,
    val postcode : String,
    val street : StreetModel,
    val coordinates : CoordinateModel,
    val timezone : TimezoneModel
)