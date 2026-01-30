package root.cyb.mhr.SimpleProductInventoryAPI.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import root.cyb.mhr.SimpleProductInventoryAPI.exception.InvalidSkuFormatException;
import root.cyb.mhr.SimpleProductInventoryAPI.exception.ProductNotFoundException;
import root.cyb.mhr.SimpleProductInventoryAPI.exception.SkuAlreadyExistsException;
import root.cyb.mhr.SimpleProductInventoryAPI.model.Product;
import root.cyb.mhr.SimpleProductInventoryAPI.repository.ProductRepository;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private static final Pattern SKU_PATTERN = Pattern.compile("^SKU-[a-zA-Z0-9]{8}$");

    public Product createProduct(Product product) {
        log.debug("Received request to create product: {}", product);

        validateSkuFormat(product.getSku());

        if (productRepository.existsBySku(product.getSku())) {
            throw new SkuAlreadyExistsException("Product with SKU " + product.getSku() + " already exists.");
        }

        Product savedProduct = productRepository.save(product);
        log.info("Product created with ID: {} and SKU: {}", savedProduct.getId(), savedProduct.getSku());
        return savedProduct;
    }

    public List<Product> getAllProducts() {
        log.debug("Fetching all products");
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        log.debug("Fetching product with ID: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Failed to find product with ID: {}", id);
                    return new ProductNotFoundException("Product not found with ID: " + id);
                });
    }

    public Product updateProduct(Long id, Product productDetails) {
        log.debug("Request to update product with ID: {}", id);

        Product existingProduct = getProductById(id);

        // SKU cannot be changed
        if (!existingProduct.getSku().equals(productDetails.getSku())) {
            log.warn("Attempt to change SKU for product ID: {}", id);
            throw new IllegalArgumentException("SKU cannot be changed.");
        }

        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setQuantity(productDetails.getQuantity());
        existingProduct.setStatus(productDetails.getStatus());

        Product updatedProduct = productRepository.save(existingProduct);
        log.info("Product updated with ID: {}", updatedProduct.getId());
        return updatedProduct;
    }

    public void deleteProduct(Long id) {
        log.debug("Request to delete product with ID: {}", id);
        if (!productRepository.existsById(id)) {
            log.warn("Failed to delete product. Product not found with ID: {}", id);
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
        log.info("Product deleted with ID: {}", id);
    }

    private void validateSkuFormat(String sku) {
        if (!SKU_PATTERN.matcher(sku).matches()) {
            throw new InvalidSkuFormatException(
                    "Invalid SKU format. Must start with 'SKU-' followed by 8 alphanumeric characters.");
        }
    }
}
