package us.bojie.tradebo.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import us.bojie.tradebo.database.entity.Instrument;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface InstrumentDao {

    @Insert(onConflict = REPLACE)
    void save(Instrument instrument);

    @Query("SELECT * FROM instrument where id = :id")
    LiveData<Instrument> load(String id);
}