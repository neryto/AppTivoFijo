package com.nery.bustos.apptivofijo.presentation.add

enum class FixedAssetType(val value: String) {
    ELECTRONIC("Electrónico"),
    FURNITURE("Moviliario"),
    DECORATION("Decoración"),
    LUMINARY("Luminaria"),
    HOME_APPLIANCE("Electrodoméstico"),
    BOOKS("Libros");

    override fun toString() : String {
        return value
    }


}

