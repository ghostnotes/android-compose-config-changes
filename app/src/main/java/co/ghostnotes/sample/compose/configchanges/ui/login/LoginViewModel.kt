package co.ghostnotes.sample.compose.configchanges.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    private val _userId = MutableStateFlow("")
    val userId: StateFlow<String> = _userId

    fun setUserId(userId: String) {
        _userId.value = userId
        updateLoginButtonEnabled()
    }

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    fun setPassword(password: String) {
        _password.value = password
        updateLoginButtonEnabled()
    }

    private val _loginButtonEnabled = MutableStateFlow(false)
    val loginButtonEnabled: StateFlow<Boolean> = _loginButtonEnabled

    private fun updateLoginButtonEnabled() {
        _loginButtonEnabled.value =
            _userId.value.isNotBlank() && _password.value.isNotBlank()
    }
}