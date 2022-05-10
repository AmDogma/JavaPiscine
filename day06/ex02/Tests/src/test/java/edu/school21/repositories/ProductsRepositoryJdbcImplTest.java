package edu.school21.repositories;

import edu.school21.models.Product;
import edu.school21.repositiries.ProductsRepository;
import edu.school21.repositiries.ProductsRepositoryJdbcImpl;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImplTest {
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1, "Sweet Bubaleh", 999),
            new Product(2, "Buratino", 111),
            new Product(3, "Yuppie", 333));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1, "Sweet Bubaleh", 999);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(3, "Coca", 888);
    final Product EXPECTED_SAVED_PRODUCT = new Product(4, "Fuel", 1);

    private ProductsRepository productsRepository;
    @BeforeEach
    public void construct() {
        DataSource dataSource = new EmbeddedDatabaseBuilder().
                setType(EmbeddedDatabaseType.HSQL).addScript("schema.sql").addScript("data.sql").build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    public void testFindAll() {
        Assertions.assertEquals(productsRepository.findAll(), EXPECTED_FIND_ALL_PRODUCTS);
    }

    @Test
    public void testFindById() {
        Assertions.assertEquals(productsRepository.findById(1L).get(), EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @Test
    public void testUpdate() {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(productsRepository.findById(3l).get(), EXPECTED_UPDATED_PRODUCT);
    }

    @Test
    public void testSave() {
        productsRepository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(productsRepository.findById(4l).get(), EXPECTED_SAVED_PRODUCT);
    }

    @Test
    public void testDelete() {
        productsRepository.delete(1l);
        Assertions.assertEquals(productsRepository.findById(1L), Optional.empty());
    }

    @AfterEach
    void close() {

    }

}
