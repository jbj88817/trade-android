package us.bojie.tradebo.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import us.bojie.tradebo.database.converter.DateConverter;
import us.bojie.tradebo.database.dao.InstrumentDao;
import us.bojie.tradebo.database.dao.OrderDao;
import us.bojie.tradebo.database.dao.OwnedStockDao;
import us.bojie.tradebo.database.dao.TokenDao;
import us.bojie.tradebo.database.entity.Instrument;
import us.bojie.tradebo.database.entity.Order;
import us.bojie.tradebo.database.entity.OwnedStock;
import us.bojie.tradebo.database.entity.Token;

@Database(entities = {Token.class, OwnedStock.class, Instrument.class, Order.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class MyDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile MyDatabase INSTANCE;

    // --- DAO ---
    public abstract TokenDao tokenDao();
    public abstract OwnedStockDao ownedStockDao();
    public abstract InstrumentDao instrumentDao();
    public abstract OrderDao orderDao();
}
