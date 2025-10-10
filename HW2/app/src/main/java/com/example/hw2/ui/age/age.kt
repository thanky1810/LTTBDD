package com.example.hw2.ui.age

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AgeScreen(onGoTh2: () -> Unit, onGoEmail: () -> Unit) {

    // ---- state ----
    var name by rememberSaveable { mutableStateOf("") }
    var ageInput by rememberSaveable { mutableStateOf("") }
    var nameError by rememberSaveable { mutableStateOf<String?>(null) }
    var ageError by rememberSaveable { mutableStateOf<String?>(null) }
    var result by rememberSaveable { mutableStateOf<String?>(null) }

    fun classify(age: Int): String = when {
        age > 65 -> "Người già (>65)"
        age >= 6 -> "Người lớn (6–65)"
        age >= 2 -> "Trẻ em (2–<6)"
        else     -> "Em bé (<2)"
    }

    fun check() {
        // reset
        nameError = null
        ageError = null
        result = null

        val trimmedName = name.trim()
        val age = ageInput.trim().toIntOrNull()

        if (trimmedName.isEmpty()) {
            nameError = "Vui lòng nhập họ và tên"
        }
        if (age == null || age < 0) {
            ageError = "Tuổi không hợp lệ"
        }
        if (nameError == null && ageError == null) {
            result = "$trimmedName là ${classify(age!!)}"
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Thanh tiêu đề + 2 nút điều hướng
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = onGoEmail) { Text("Email") }
                Text("Kiem tra tuoi", style = MaterialTheme.typography.titleMedium)
                Button(onClick = onGoTh2) { Text("Về Home") }
            }

            // --- Khung nhập liệu ---
            Surface(
                tonalElevation = 2.dp,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp)) {
                    // Row 1: Họ và tên
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Họ và tên", modifier = Modifier.width(100.dp))
                        OutlinedTextField(
                            value = name,
                            onValueChange = {
                                name = it
                                if (nameError != null) nameError = null
                            },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            isError = nameError != null,
                            placeholder = { Text("Nguyễn Văn A") },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                        )
                    }
                    if (nameError != null) {
                        Text(
                            text = nameError!!,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 100.dp, top = 4.dp)
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    // Row 2: Tuổi
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Tuổi", modifier = Modifier.width(100.dp))
                        OutlinedTextField(
                            value = ageInput,
                            onValueChange = {
                                ageInput = it
                                if (ageError != null) ageError = null
                            },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            isError = ageError != null,
                            placeholder = { Text("ví dụ: 23") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            )
                        )
                    }
                    if (ageError != null) {
                        Text(
                            text = ageError!!,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 100.dp, top = 4.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // Nút kiểm tra
            Button(
                onClick = { check() },
                enabled = name.isNotBlank() && ageInput.isNotBlank()
            ) {
                Text("Kiểm tra")
            }

            // Kết quả
            when {
                result != null -> Text(
                    text = result!!,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 12.dp)
                )
                nameError == null && ageError == null -> Spacer(Modifier.height(12.dp))
            }
        }
    }
}
