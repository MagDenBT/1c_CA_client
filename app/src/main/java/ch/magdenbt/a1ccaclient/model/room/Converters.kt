package ch.magdenbt.a1ccaclient.model.room

import androidx.room.TypeConverter
import ch.magdenbt.a1ccaclient.model.scenarios.entities.ScenarioResult
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun toScenarioResultFromResult(value: Int): ScenarioResult = enumValues<ScenarioResult>()[value]
    @TypeConverter
    fun fromScenarioResultToResult(value: ScenarioResult): Int = value.ordinal

}