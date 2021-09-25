package co.ghostnotes.sample.compose.configchanges.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.ghostnotes.sample.compose.configchanges.ui.login.LoginViewModel
import co.ghostnotes.sample.compose.configchanges.ui.theme.ComposeConfigChangesTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import co.ghostnotes.sample.compose.configchanges.R

class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeConfigChangesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Login(loginViewModel)
                }
            }
        }
    }
}

@Composable
fun Login(loginViewModel: LoginViewModel) {
    Column(modifier = Modifier
        .padding(vertical = 8.dp, horizontal = 16.dp)
        .fillMaxSize()
    ) {
        val loginButtonEnabled: Boolean by loginViewModel.loginButtonEnabled.collectAsState()

        UserIdTextField(loginViewModel = loginViewModel)
        PasswordTextField(loginViewModel = loginViewModel)

        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            onClick = { /* onClick */ },
            enabled = loginButtonEnabled
        ) {
            Text(
                text = stringResource(id = R.string.button_login),
                style = MaterialTheme.typography.button
            )
        }
    }
}

@Composable
fun UserIdTextField(modifier: Modifier = Modifier, loginViewModel: LoginViewModel) {
    //var userId by rememberSaveable { mutableStateOf("") }
    val userId: String by loginViewModel.userId.collectAsState()

    TextField(
        modifier = modifier.fillMaxWidth(),
        value = userId,
        onValueChange = { loginViewModel.setUserId(it) },
        label = { Text(stringResource(id = R.string.hint_enter_user_id)) }
    )
}

@Composable
fun PasswordTextField(modifier: Modifier = Modifier, loginViewModel: LoginViewModel) {
    //var password by rememberSaveable { mutableStateOf("") }
    val password: String by loginViewModel.password.collectAsState()

    TextField(
        modifier = modifier.fillMaxWidth(),
        value = password,
        onValueChange = { loginViewModel.setPassword(it) },
        label = { Text(stringResource(id = R.string.hint_enter_password)) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeConfigChangesTheme {
        //Login()
    }
}