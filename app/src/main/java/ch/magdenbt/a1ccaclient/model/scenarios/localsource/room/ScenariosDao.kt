package ch.magdenbt.a1ccaclient.model.scenarios.localsource.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged


@Dao
abstract class ScenariosDao {

    fun getDistinctScenarios(): Flow<List<ScenarioDbEntity>>{
        return getScenarios().distinctUntilChanged()
    }

    @Query("SELECT * FROM ${ScenarioDbEntity.TABLE_SCENARIOS}")
    protected abstract fun getScenarios(): Flow<List<ScenarioDbEntity>>

    @Query("DELETE FROM ${ScenarioDbEntity.TABLE_SCENARIOS}")
   abstract fun deleteAllScenarios()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveScenarios(scenarioDbEntities: List<ScenarioDbEntity>)

}