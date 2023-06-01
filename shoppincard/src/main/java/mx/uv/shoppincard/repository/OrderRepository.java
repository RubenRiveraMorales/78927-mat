package mx.uv.shoppincard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.uv.shoppincard.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
