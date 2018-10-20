package us.bojie.tradebo.database.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import androidx.room.TypeConverter;
import us.bojie.tradebo.database.entity.RecentOrder;

public class ExecutionsBeanConverter {

    @Inject
    private static Gson mGson;

    @TypeConverter
    public static String fromExecutionsBean(List<RecentOrder.ExecutionsBean> executions) {
        if (executions == null) {
            return (null);
        }
        return mGson.toJson(executions);
    }

    @TypeConverter
    public static List<RecentOrder.ExecutionsBean> toExecutionsBean(String executionsBeanString) {
        if (executionsBeanString == null) {
            return (null);
        }
        Type type = new TypeToken<List<RecentOrder.ExecutionsBean>>() {
        }.getType();
        return mGson.fromJson(executionsBeanString, type);
    }
}
