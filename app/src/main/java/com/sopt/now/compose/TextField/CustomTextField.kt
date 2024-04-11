package com.sopt.now.compose.TextField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomTextField(
    value: String,
    onInputChange: (String) -> Unit,
    label: String,
    isPwSecret: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onInputChange,
        label = { Text(text = label)},
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (!isPwSecret) PasswordVisualTransformation() else VisualTransformation.None

    )
}