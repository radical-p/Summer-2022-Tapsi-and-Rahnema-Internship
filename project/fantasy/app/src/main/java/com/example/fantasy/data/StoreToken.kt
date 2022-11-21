import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

interface Storage {
    suspend fun insert(key : String , data: String): Unit

    suspend fun get(key: String): String?
}

class PersistentStorage constructor(
    private val dataStore: DataStore<Preferences>,
) : Storage{
    override suspend fun insert(key : String , data: String): Unit {
        val preferenceKey =  stringPreferencesKey(key)
        dataStore.edit {
            it[preferenceKey] = data
        }
    }

    override suspend fun get(key: String): String? {
            val preferenceKey =  stringPreferencesKey(key)
            val preferences = dataStore.data.first()
            return preferences[preferenceKey]
    }

}