package com.example.hw2.ui.th2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp




@Composable
fun Th2Screen(
    onGoAge: () -> Unit,
    onGoEmail: () -> Unit,
) {
    var input by rememberSaveable { mutableStateOf("") }
    var numbers by rememberSaveable { mutableStateOf(listOf<Int>()) }
    var error by rememberSaveable { mutableStateOf<String?>(null) }


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
                verticalAlignment = Alignment.Top      // or CenterVertically
            ) {
                Button(onClick = { onGoAge() }) { Text("Age") }
                Text(
                    text = "Thuc Hanh 2",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(onClick = {onGoEmail()}) { Text("Email") }
            }


            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    placeholder = { Text("Nhap vao so luong") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )

                )
                Spacer(Modifier.width(8.dp))
                Button(onClick = {
                    val n = input.trim().toIntOrNull()
                    if (n != null && n > 0) {
                        numbers = (1..n).toList()
                        error = null
                    } else {
                        numbers = emptyList()
                        error = "Du lieu khong hop le"
                    }
                }) {
                    Text("Tao")
                }
            }

            if (error != null) {
                Text(
                    text = error!!,
                    color = Color(0xFFD32F2F),
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                Spacer(Modifier.height(8.dp))
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(numbers) { item ->
                    Surface(
                        color = Color(0xFFD9534F),
                        shape = RoundedCornerShape(28.dp),
                        tonalElevation = 0.dp,
                        shadowElevation = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()   // <— sửa ở đây
                            .height(48.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = item.toString(), color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
