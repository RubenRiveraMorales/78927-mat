package mx.uv.shoppincard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.uv.shoppincard.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
