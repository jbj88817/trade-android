package us.bojie.tradebo.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import us.bojie.tradebo.database.entity.Instrument;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface InstumentDao {

    @Insert(onConflict = REPLACE)
    void save(Instrument instrument);

    @Query("SELECT * FROM instrument")
    LiveData<Instrument> load();
}