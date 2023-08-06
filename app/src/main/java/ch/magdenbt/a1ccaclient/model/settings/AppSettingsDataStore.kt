package ch.magdenbt.a1ccaclient.model.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ch.magdenbt.a1ccaclient.model.AccountNotFound
import ch.magdenbt.a1ccaclient.model.accounts.entities.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSettingsDataStore @Inject constructor(private val context: Context) : AppSettings {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SettingKeys.General.key)

    override suspend fun getAccount(): Account {
        return context.dataStore.data.map { preferences ->

            val username = preferences[stringPreferencesKey(SettingKeys.Username.key)]
            val urlC = preferences[stringPreferencesKey(SettingKeys.URL_CA.key)]
            val password = preferences[stringPreferencesKey(SettingKeys.Password.key)]

            if (username == null && password == null && urlC == null) throw AccountNotFound()

            Account(username = username!!, caURL = urlC!!, password = password!!)
        }.first()
    }

    override suspend fun deleteAccount() {
        context.dataStore.edit {
            it.remove(stringPreferencesKey(SettingKeys.Username.key))
            it.remove(stringPreferencesKey(SettingKeys.URL_CA.key))
            it.remove(stringPreferencesKey(SettingKeys.Password.key))
        }
    }

    override suspend fun saveAccount(account: Account) {
        context.dataStore.edit { settings ->
            settings[stringPreferencesKey(SettingKeys.Username.key)] = account.username
            settings[stringPreferencesKey(SettingKeys.URL_CA.key)] = account.caURL
            settings[stringPreferencesKey(SettingKeys.Password.key)] = account.password
        }
    }
}