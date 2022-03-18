package com.example.tddkata.main

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
import androidx.lifecycle.ViewModelProvider
import com.example.tddkata.ui.theme.TDDKataTheme
import com.example.tddkata.ui.theme.Typography

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TDDKataTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ConstructUI()
                }
            }
        }
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }


    @Composable
    fun ConstructUI() {
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
                TextInputsAndButton()
            }
        }
    }

    @Composable
    fun TextInputsAndButton() {

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

        val context = LocalContext.current
        Button(onClick = {
            val validation: CredentialValidation =
                mainViewModel.validateCredentials(username.text, password.text)
            val msg: String
            when (validation) {
                is CredentialValidation.EmptyUserNameAndPassword -> msg =
                    "Empty Username and Password"
                is CredentialValidation.EmptyUserName -> msg = "Empty Username"
                is CredentialValidation.EmptyPassword -> msg = "Empty Password"
                is CredentialValidation.InvalidUserNameAndPassword -> msg = "Invalid credentials"
                is CredentialValidation.InvalidUserName -> msg = "Invalid Username"
                is CredentialValidation.InvalidPassword -> msg = "Invalid Password"
                is CredentialValidation.ValidCredentials -> msg = "Success!"
            }
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Login", color = Color.White)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        TDDKataTheme {
            Column {
                ConstructUI()
            }
        }
    }
}