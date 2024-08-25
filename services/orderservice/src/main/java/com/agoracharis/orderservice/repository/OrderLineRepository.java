package com.agoracharis.orderservice.repository;

import com.agoracharis.orderservice.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
