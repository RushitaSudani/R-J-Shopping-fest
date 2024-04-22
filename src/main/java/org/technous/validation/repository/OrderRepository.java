package org.technous.validation.repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.technous.validation.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select c from Order c where c.user.id=:userId AND (c.OrderStatus = 'PLACED' " +
            "or c.OrderStatus = 'CONFIRMED' OR c.OrderStatus = 'SHIPPED' or c.OrderStatus = 'DELEVERED')")
    public List<Order> getUsersOrders(@Param("userId") Long userId);


    public Order getOrderByTotalPrice(double totalPrice);
}
