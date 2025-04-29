package tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person person;
    private Product product;

    @BeforeEach
    void setUp() {
        person = new Person("John", 100.0);
        product = new Product("Laptop", 50.0);
    }

    @Test
    void testPersonCreation() {
        assertEquals("John", person.getName());
        assertEquals(100.0, person.getMoney(), 0.001);
        assertTrue(person.getProducts().isEmpty());
    }

    @Test
    void testBuyProductSuccess() {
        person.buyProduct(product);
        assertEquals(1, person.getProducts().size());
        assertEquals(50.0, person.getMoney(), 0.001);
    }

    @Test
    void testBuyProductNotEnoughMoney() {
        Product expensiveProduct = new Product("Car", 200.0);
        assertThrows(IllegalArgumentException.class, () -> person.buyProduct(expensiveProduct));
    }

    @Test
    void testHasBoughtAnything() {
        assertFalse(person.hasBoughtAnything());
        person.buyProduct(product);
        assertTrue(person.hasBoughtAnything());
    }

    @Test
    void testToString() {
        person.buyProduct(product);
        String expected = "John купил(а): Laptop";
        assertTrue(person.toString().contains(expected));
    }

    @Test
    @Disabled("Тест временно отключен для демонстрации")
    void testDisabledTest() {
        // Этот тест не будет выполняться
    }
}