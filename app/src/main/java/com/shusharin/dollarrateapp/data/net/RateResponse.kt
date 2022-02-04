package com.shusharin.dollarrateapp.data.net

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "ValCurs")
data class RateResponse @JvmOverloads constructor(

    @field:Attribute(name = "DateRange2")
    var dateRange2: String = "",

    @field:Attribute(name = "DateRange1")
    var dateRange1: String? = "",

    @field:Attribute(name = "name")
    var name: String = "",

    @field:ElementList(name = "Record", inline = true)
    var records: List<Record>? = null,

    @field:Element(name = "ID", required = false)
    var id: String = "",
)

@Root(name = "Record", strict = true)
data class Record constructor(
    @field:Element(name = "Nominal")
    var nominal: Int = 1,
    @field:Element(name = "Value")
    var value: String = " ",
    @field:Attribute(name = "Date")
    var date: String = "",
    @field:Attribute(name = "Id", required = false)
    var id: String = "",
)
