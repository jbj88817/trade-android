package us.bojie.tradebo.database.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import us.bojie.tradebo.database.entity.OwnedStock;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface OwnedStockDao {

    @Insert(onConflict = REPLACE)
    void save(List<OwnedStock> ownedStocks);

    @Query("SELECT * FROM ownedstock")
    LiveData<List<OwnedStock>> load();
}
