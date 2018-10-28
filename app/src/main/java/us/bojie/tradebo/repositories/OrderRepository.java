package us.bojie.tradebo.repositories;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import us.bojie.tradebo.api.ApiService;
import us.bojie.tradebo.database.dao.OrderDao;
import us.bojie.tradebo.database.entity.Order;

public class OrderRepository {

    private static final String TAG = OrderRepository.class.getSimpleName();

    private final ApiService webservice;
    private final OrderDao orderDao;
    private final Executor executor;

    @Inject
    public OrderRepository(ApiService webservice, OrderDao orderDao, Executor executor) {
        this.webservice = webservice;
        this.orderDao = orderDao;
        this.executor = executor;
    }

    public LiveData<Order> getOrder(String orderId) {
        return orderDao.load(orderId);
    }

    public void saveOrder(Order order) {
        orderDao.save(order);
    }
}
