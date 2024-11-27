package org.sandroni.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.sandroni.entity.OrderEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class OrderRepository implements PanacheRepositoryBase<OrderEntity, Long> {

}
