package eandradep.io.geojsonproject.models.dto

import java.io.Serializable

class PathPassengerData: Serializable{

    constructor()

    constructor(cityName: String, pathPassengerRoute: String, documentIdentification: String){
        this.cityName = cityName
        this.pathPassengerRoute = pathPassengerRoute
        this.documentIdentification = documentIdentification
    }

    var cityName: String = ""
    var pathPassengerRoute: String = ""
    var documentIdentification: String = ""
}