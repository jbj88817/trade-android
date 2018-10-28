package us.bojie.tradebo.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import us.bojie.tradebo.database.entity.Order;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface OrderDao {

    @Insert(onConflict = REPLACE)
    void save(Order order);

    @Query("SELECT * FROM `order` where id = :id")
    LiveData<Order> load(String id);
}