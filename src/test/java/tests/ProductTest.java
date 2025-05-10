package tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void testProductCreation() {
        Product product = new Product("Phone", 300.0);
        assertEquals("Phone", product.getName());
        assertEquals(300.0, product.getPrice(), 0.001);
    }

    @Test
    void testEmptyProductName() {
        assertThrows(IllegalArgumentException.class, () -> new Product("", 10.0));
    }

    @Test
    void testNegativeProductPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Product("TV", -100.0));
    }

    @Test
    void testToString() {
        Product product = new Product("Book", 15.0);
        assertEquals("Book (15.0)", product.toString());
    }
}