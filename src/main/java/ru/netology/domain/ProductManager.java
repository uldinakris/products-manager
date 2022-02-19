package ru.netology.domain;

public class ProductManager {
    private ProductsRepository repository;

    public ProductManager(ProductsRepository repository){
        this.repository = repository;
    }

    public void add(Product product) {
        repository.add(product);
    }

    public Product[] searchBy(String query) {
        Product[] result = new Product[0];
        for (Product item: repository.findAll()) {
            if (matches(item, query)) {
                Product[] tmp = new Product[result.length + 1];

                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product product, String query) {
        String name = product.getName();
        boolean result = name.contains(query);
        return result;
    }
}
