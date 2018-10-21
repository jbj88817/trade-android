package us.bojie.tradebo.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import us.bojie.tradebo.database.entity.OwnedStock;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface OwnedStockDao {

    @Insert(onConflict = REPLACE)
    void save(OwnedStock ownedStock);

    @Query("SELECT * FROM ownedstock")
    LiveData<OwnedStock> load();
}
