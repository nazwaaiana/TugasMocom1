package com.example.tugasuts

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

data class Barang(
    val nama: String,
    val jumlah: Int
)

class DashboardViewModel : ViewModel() {
    var username = mutableStateOf("")
        private set

    private val _barangList = mutableStateListOf<Barang>()
    val barangList: List<Barang> = _barangList

    fun setUsername(name: String) {
        username.value = name
    }

    fun tambahBarang(namaBarang: String, jumlah: Int) {
        _barangList.add(Barang(namaBarang, jumlah))
    }
}