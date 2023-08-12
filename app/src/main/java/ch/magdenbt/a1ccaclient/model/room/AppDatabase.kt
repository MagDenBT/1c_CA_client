package ch.magdenbt.a1ccaclient.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ch.magdenbt.a1ccaclient.model.scenarios.localsource.room.ScenarioDbEntity
import ch.magdenbt.a1ccaclient.model.scenarios.localsource.room.ScenariosDao

@Database(
    version = 1,
    entities = [
        ScenarioDbEntity::class
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getScenariosDao(): ScenariosDao

    companion object{
        const val DATABASE_FILE = "MyDataDB"
    }
}