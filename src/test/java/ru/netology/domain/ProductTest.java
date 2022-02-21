package ru.netology.domain;

import org.junit.jupiter.api.Test;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductsRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private ProductsRepository repository = new ProductsRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book bookItem = new Book(25, "Idiot", 200, "Dostoevsky");
    private Smartphone smartphoneItem = new Smartphone(5, "iPhone", 150000, "Apple");

    @Test
    public void shouldAdd() {
        repository.add(bookItem);
        repository.add(smartphoneItem);

        Product[] expected = new Product[]{bookItem, smartphoneItem};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindById() {
        repository.add(bookItem);
        repository.add(smartphoneItem);

        Product expected = bookItem;
        Product actual = repository.findById(25);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindById() {
        repository.add(bookItem);
        repository.add(smartphoneItem);

        Product expected = null;
        Product actual = repository.findById(52);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        repository.add(bookItem);
        repository.add(smartphoneItem);
        repository.removeById(25);

        Product[] expected = new Product[]{smartphoneItem};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddToManager() {
        manager.add(bookItem);
        manager.add(smartphoneItem);

        Product[] expected = new Product[]{bookItem, smartphoneItem};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatch() {
        boolean actual = manager.matches(bookItem, "Idiot");
        assertTrue(actual);
    }

    @Test
    public void shouldSearchByQuery() {
        manager.add(bookItem);
        manager.add(smartphoneItem);

        Product[] expected = new Product[]{smartphoneItem};
        Product[] actual = manager.searchBy("iPhone");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnNothingIfDoesNotMatch() {
        manager.add(bookItem);
        manager.add(smartphoneItem);

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Knife");
        assertArrayEquals(expected, actual);
    }

}