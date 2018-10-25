package us.bojie.tradebo.database.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;
import us.bojie.tradebo.database.entity.Order;

public class ExecutionsBeanConverter {

    @TypeConverter
    public static String fromExecutionsBean(List<Order.ExecutionsBean> executions) {
        if (executions == null) {
            return (null);
        }
        Gson gson = new Gson();
        return gson.toJson(executions);
    }

    @TypeConverter
    public static List<Order.ExecutionsBean> toExecutionsBean(String executionsBeanString) {
        if (executionsBeanString == null) {
            return (null);
        }
        Type type = new TypeToken<List<Order.ExecutionsBean>>() {
        }.getType();
        Gson gson = new Gson();
        return gson.fromJson(executionsBeanString, type);
    }
}
