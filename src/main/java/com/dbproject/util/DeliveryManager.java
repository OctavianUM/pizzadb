package com.dbproject.util;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.dbproject.dao.CourierDAO;
import com.dbproject.dao.CustomerDAO;
import com.dbproject.domain.Courier;
import com.dbproject.domain.Order;
import com.dbproject.domain.Delivery;

public class DeliveryManager {

    public DeliveryManager(){

    }

    public void Delivery(Order order){

        //find a courier with a available status or waiting
            //if not: send message "restaurant too busy, not taking orders"

        //assign the order to this courier
            //update courier status to waiting
            //set 3 min timer -> 
                // expired: 
                // courier status = DELIVERING
                // courier set timelastdelivery
        int postal = CustomerDAO.getCustomerAdress(order.getCustomerId()).getPostal();
        ArrayList<Courier> availableCouriers = (ArrayList<Courier>) CourierDAO.getAvailableCouriers(postal);

        if (availableCouriers != null) {
            Courier courier = availableCouriers.getFirst();

            Delivery delivery = new Delivery(order.getOrderId(), courier.getCourierID());
            courier.setStatus(CourierStatus.WAITING.toString());

            Executors.newScheduledThreadPool(1).schedule(() -> {
                CourierDAO.updateCourierStatus(CourierStatus.DELIVERING);
            }, 3, TimeUnit.MINUTES);
        }
    }
    
}
