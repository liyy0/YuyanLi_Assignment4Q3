package com.bignerdranch.android.Assignment4Q3

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class CrimeListViewModel : ViewModel() {
    private val crimeRepository = CrimeRepository.get()

//    private val _crimes: MutableStateFlow<List<Crime>> = MutableStateFlow(emptyList())
//    val crimes: StateFlow<List<Crime>>
//        get() = _crimes.asStateFlow()

    private val _crimes: MutableStateFlow<List_filer> = MutableStateFlow(List_filer())
    val crimes: StateFlow<List_filer>
        get() = _crimes.asStateFlow()
    init {
        viewModelScope.launch {
            crimeRepository.getCrimes().collect { crimes ->
                _crimes.update {
                    it.copy(crimeList = crimes.toMutableList(), crimeList_filter = crimes.toMutableList())

                }
//                _crimes.value = it
            }

        }
    }

    fun filterCrimes(category: String, date: Date) {
        _crimes.update {

            val crimeList_filter = _crimes.value.crimeList.filter { crime->
                Log.d("filterCrimes", "CrimeListViewModel: ${crime.category} ${crime.date} ${category} ${date}${Date(0)}")
                Log.d("filterCrimes", "CrimeListViewModel: ${category.isNotEmpty()} ${crime.category.lowercase() == category.lowercase()} ${date != Date(0)} ${crime.date == date}")
                (category == "" || crime.category.lowercase() == category.lowercase()) && (date == Date(0) || crime.date == date)
            }
//
            it.copy(choose_category = category, choose_date = date, crimeList_filter = crimeList_filter.toMutableList())

        }
    }

    fun clearFilter() {
        _crimes.update {
            it.copy(choose_category = "", choose_date = Date(0), crimeList_filter = it.crimeList.toMutableList())
        }
    }

    suspend fun addCrime(crime: Crime) {
        crimeRepository.addCrime(crime)
    }
}
