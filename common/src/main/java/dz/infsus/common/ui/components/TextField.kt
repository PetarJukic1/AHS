package dz.infsus.common.ui.components

import dz.infsus.common.ui.theme.ColorPallet
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TextField(
    onTextChanged: (String) -> Unit,
    label: String,
    placeholder: String,
    isPassword: Boolean = false,
    isNumber: Boolean = false,
) {
    var text by remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedLabelColor = ColorPallet.primary900,
            focusedLabelColor = ColorPallet.primary600,
            unfocusedBorderColor = ColorPallet.primary900,
            focusedBorderColor = ColorPallet.primary600,
            cursorColor = ColorPallet.neutral900,
            textColor = ColorPallet.neutral900,
            placeholderColor = ColorPallet.neutral900
        ),
        value = text,
        onValueChange = {
            text = it
            onTextChanged(it.text)
        },
        label = {
            Text(

                text = label
            )
        },
        placeholder = {
            Text(text = placeholder)
        },
        visualTransformation = if(isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = if(isNumber) KeyboardType.Number else KeyboardType.Text
        )
    )
}