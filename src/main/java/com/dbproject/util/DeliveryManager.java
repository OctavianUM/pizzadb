package com.dbproject.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.dbproject.dao.CourierDAO;
import com.dbproject.dao.CustomerDAO;
import com.dbproject.dao.DeliveryDAO;
import com.dbproject.domain.Courier;
import com.dbproject.domain.Order;
import com.dbproject.domain.Delivery;

public class DeliveryManager {
    private final DeliveryDAO deliveryDAO;

    public DeliveryManager(){

        this.deliveryDAO = new DeliveryDAO();
    }

    public void delivery(Order order) {
        List<Courier> availableCouriers = CourierDAO.getAvailableCouriers(DeliveryDAO.getPostalCodeByOrderId(order.getOrderId()));
        List<Courier> waitingCouriers = CourierDAO.getWaitingCouriers(DeliveryDAO.getPostalCodeByOrderId(order.getOrderId()));
        if (!availableCouriers.isEmpty()) {
            Courier courier = availableCouriers.get(0);
            deliveryDAO.submitDelivery(new Delivery(order.getOrderId(), courier.getCourierID()));
            CourierDAO.updateCourierStatus(courier, CourierStatus.WAITING);
        }
        else if (!waitingCouriers.isEmpty()) {
            Courier courier = waitingCouriers.get(0);
            deliveryDAO.submitDelivery(new Delivery(order.getOrderId(), courier.getCourierID()));
            CourierDAO.updateCourierStatus(courier,CourierStatus.WAITING);
        }
        else System.out.println("No couriers are available!");


    }
    
}
