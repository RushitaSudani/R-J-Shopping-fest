package org.technous.validation.repository;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.technous.validation.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
