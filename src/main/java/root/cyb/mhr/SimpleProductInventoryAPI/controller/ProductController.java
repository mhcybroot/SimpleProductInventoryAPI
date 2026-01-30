package root.cyb.mhr.SimpleProductInventoryAPI.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import root.cyb.mhr.SimpleProductInventoryAPI.model.Product;
import root.cyb.mhr.SimpleProductInventoryAPI.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        log.debug("Received request to create product: {}", product);
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        log.debug("Received request to fetch all products");
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        log.debug("Received request to fetch product by ID: {}", id);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        log.debug("Received request to update product with ID: {}", id);
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("Received request to delete product with ID: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
