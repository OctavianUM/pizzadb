package com.dbproject.util;

/** 
 * Statuses that can be applied to an order 
 * {@link #PENDING}
 * {@link #BEING_PREPARED}
 * {@link #OUT_FOR_DELIVERY}
 * {@link #DELIVERED}
*/
public enum OrderStatus {
    /** order placed but not accepted by the kitchen */
    PENDING, 
    /** order placed and being made */
    BEING_PREPARED,
    /** order is ready and out for delivery */
    OUT_FOR_DELIVERY,
    /** order delivered*/
    DELIVERED
}
