package com.example.tugasuts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tugasuts.ui.theme.TugasutsTheme
import androidx.compose.ui.graphics.RectangleShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahBarangScreen(
    onBackClick: () -> Unit,
    onSimpan: (String, Int) -> Unit
) {
    var namaBarang by remember { mutableStateOf("") }
    var jumlahBarang by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Masukkan Data Barang",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 28.dp)
            )
            OutlinedTextField(
                value = namaBarang,
                onValueChange = {
                    namaBarang = it
                    showError = false
                },
                label = { Text("Nama Barang") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                isError = showError && namaBarang.isBlank(),
                supportingText = {
                    if (showError && namaBarang.isBlank()) {
                        Text("Nama barang tidak boleh kosong")
                    }
                }
            )
            OutlinedTextField(
                value = jumlahBarang,
                onValueChange = {
                    jumlahBarang = it.filter { char -> char.isDigit() }
                    showError = false
                },
                label = { Text("Jumlah Barang") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 28.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = showError &&
                        (jumlahBarang.isBlank() ||
                                jumlahBarang.toIntOrNull() == null ||
                                jumlahBarang.toInt() <= 0),
                supportingText = {
                    if (showError && jumlahBarang.isBlank()) {
                        Text("Jumlah tidak boleh kosong")
                    } else if (showError && jumlahBarang.toIntOrNull() != null && jumlahBarang.toInt() <= 0) {
                        Text("Jumlah harus lebih dari 0")
                    }
                }
            )

            // Tombol Simpan
            Button(
                onClick = {
                    if (namaBarang.isBlank() || jumlahBarang.isBlank()
                        || jumlahBarang.toIntOrNull() == null || jumlahBarang.toInt() <= 0
                    ) {
                        showError = true
                        return@Button
                    }
                    onSimpan(namaBarang, jumlahBarang.toInt())
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RectangleShape
            ) {
                Text("Simpan Barang")
            }

            OutlinedButton(
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape
            ) {
                Text("Batal")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TambahBarangScreenPreview() {
    TugasutsTheme {
        TambahBarangScreen(
            onBackClick = {},
            onSimpan = { _, _ -> }
        )
    }
}