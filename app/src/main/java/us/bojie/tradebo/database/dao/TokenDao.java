package us.bojie.tradebo.database.dao;


import java.util.Date;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import us.bojie.tradebo.database.entity.Token;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface TokenDao {

    @Insert(onConflict = REPLACE)
    void save(Token token);

    @Query("SELECT * FROM token")
    LiveData<Token> load();

    @Query("SELECT * FROM token WHERE lastRefresh > :lastRefreshMax LIMIT 1")
    Token hasToken(Date lastRefreshMax);
}