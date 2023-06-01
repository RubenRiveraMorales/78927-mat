package mx.uv.shoppincard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.uv.shoppincard.entity.ShoppingCart;
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {

}