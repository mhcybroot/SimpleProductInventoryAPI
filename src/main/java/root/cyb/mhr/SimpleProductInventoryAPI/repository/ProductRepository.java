package root.cyb.mhr.SimpleProductInventoryAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import root.cyb.mhr.SimpleProductInventoryAPI.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySku(String sku);
}
