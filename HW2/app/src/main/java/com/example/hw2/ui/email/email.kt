package com.example.hw2.ui.email

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun EmailScreen(onGoTh2: () -> Unit, onGoAge: () -> Unit) {
    var input by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf<String?>(null) }
    var success by rememberSaveable { mutableStateOf<Boolean?>(null) }

    fun isValidEmail(text: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(text.trim()).matches()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = onGoAge) { Text("Age") }
                Text(
                    text = "Kiểm tra Email",
                    style = MaterialTheme.typography.titleMedium
                )
                Button(onClick = onGoTh2) { Text("Về Home") }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = input,
                    onValueChange = {
                        input = it
                        // Khi sửa nội dung thì reset kết quả cũ
                        error = null
                        success = null
                    },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    placeholder = { Text("email@example.com") },
                    isError = success == false,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    supportingText = {
                        when (success) {
                            true  -> Text("Email hợp lệ", color = Color(0xFF2E7D32))
                            false -> Text("Email không hợp lệ", color = MaterialTheme.colorScheme.error)
                            null  -> {}
                        }
                    }
                )
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = {
                        val valid = isValidEmail(input)
                        success = valid
                        error = if (valid) null else "Email không hợp lệ"
                    },
                    enabled = input.isNotBlank()
                ) { Text("check") }
            }

            // Nếu muốn hiển thị thêm ngoài supportingText:

        }
    }
}