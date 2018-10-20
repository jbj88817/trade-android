package us.bojie.tradebo.database.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import androidx.room.TypeConverter;
import us.bojie.tradebo.database.entity.RecentOrder;


public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String fromExecutionsBean(List<RecentOrder.ExecutionsBean> executions) {
        if (executions == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<RecentOrder.ExecutionsBean>>() {}.getType();
        return gson.toJson(executions, type);
    }

    @TypeConverter
    public static List<RecentOrder.ExecutionsBean> toExecutionsBean(String executionsBeanString) {
        if (executionsBeanString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<RecentOrder.ExecutionsBean>>() {}.getType();
        return gson.fromJson(executionsBeanString, type);
    }
}
