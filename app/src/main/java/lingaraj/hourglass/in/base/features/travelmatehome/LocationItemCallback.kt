package lingaraj.hourglass.`in`.base.features.travelmatehome

import lingaraj.hourglass.`in`.base.database.location.Location

interface LocationItemCallback {
    fun OnItemEmitted(location: Location)
}