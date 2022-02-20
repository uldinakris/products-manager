package ru.netology.manager;

import ru.netology.domain.Product;
import ru.netology.repository.ProductsRepository;

public class ProductManager {
    private ProductsRepository repository;

    public ProductManager(ProductsRepository repository) {
        this.repository = repository;
    }

    public void add(Product product) {
        repository.add(product);
    }

    public Product[] searchBy(String query) {
        Product[] result = new Product[0];
        for (Product item : repository.findAll()) {
            if (matches(item, query)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product product, String query) {
        return product.getName().contains(query);
    }
}
