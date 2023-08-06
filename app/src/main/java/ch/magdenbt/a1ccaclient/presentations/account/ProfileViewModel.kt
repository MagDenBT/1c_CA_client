package ch.magdenbt.a1ccaclient.presentations.account


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.magdenbt.a1ccaclient.model.AccountNotFound
import ch.magdenbt.a1ccaclient.model.accounts.entities.Account
import ch.magdenbt.a1ccaclient.model.settings.AppSettings
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val appSettings: AppSettings) : ViewModel() {

    private val _currentAccount = MutableLiveData<Account>(null)
    val currentAccount: LiveData<Account?> = _currentAccount

    private val _isSigned = MutableLiveData<Boolean>(false)
    val isSigned:LiveData<Boolean> = _isSigned

    private val _signingInProgress = MutableLiveData<Boolean>(false)
    val signingInProgress:LiveData<Boolean> = _signingInProgress

    private val _accountDeletingInProgress = MutableLiveData<Boolean>(false)
    val accountDeletingInProgress:LiveData<Boolean> = _accountDeletingInProgress

    init {
        viewModelScope.launch {
            try {
                _currentAccount.value = appSettings.getAccount()
                _isSigned.value = true
            } catch (e: AccountNotFound) {
                _isSigned.value = false
            }
        }
    }

    fun updateAccount(account: Account) {
        viewModelScope.launch {

            _signingInProgress.value = true

            appSettings.saveAccount(account)
            _currentAccount.value = account

            if (_isSigned.value == false) _isSigned.value = true

            _signingInProgress.value = false

        }
    }

    fun logout() {
        viewModelScope.launch {

            _accountDeletingInProgress.value = true

            appSettings.deleteAccount()
            _currentAccount.value = null

            if (_isSigned.value == true) _isSigned.value = false

            _accountDeletingInProgress.value = false

        }
    }
}