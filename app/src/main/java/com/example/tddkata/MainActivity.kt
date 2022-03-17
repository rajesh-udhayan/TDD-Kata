package com.example.tddkata

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tddkata.ui.theme.TDDKataTheme
import com.example.tddkata.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TDDKataTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Welcome", style = Typography.h3)
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            TextInputs()
                            SubmitButton()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TextInputs() {

    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(value = username,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = { Text(text = "Email address") },
        onValueChange = {
            username = it
        }
    )

    OutlinedTextField(
        value = password,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        label = { Text(text = "Password") },
        visualTransformation = PasswordVisualTransformation(),
        onValueChange = {
            password = it
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )

}

@Composable
fun SubmitButton() {
    val context = LocalContext.current
    Button(onClick = {
        Toast.makeText(context, "Welcome", Toast.LENGTH_LONG).show()
    }) {
        Text(text = "Login", color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TDDKataTheme {
        Column {
            TextInputs()
            SubmitButton()
        }
    }
}