package com.bignerdranch.android.Assignment4Q3

import java.util.Date

data class List_filer(
    var choose_category: String = "",
    var choose_date: Date = Date(0),
    var crimeList: MutableList<Crime> = mutableListOf(),
    var crimeList_filter : MutableList<Crime> = mutableListOf()
)
