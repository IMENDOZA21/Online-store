package academy.digitallab.store.product.service;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    @Override
    public List<Product> ListAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("CREATED");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product product1=getProduct(product.getId());
        if(null == product1){
            return null;
        }
        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setCategory(product.getCategory());
        product1.setPrice(product.getPrice());
        return productRepository.save(product1);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product product1=getProduct(id);
        if(null == product1){
            return null;
        }
        product1.setStatus("DELETED");
        return productRepository.save(product1);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product product1=getProduct(id);
        if(null == product1){
            return null;
        }
        Double stock = product1.getStock() + quantity;
        product1.setStock(stock);
        return productRepository.save(product1);
    }
}
