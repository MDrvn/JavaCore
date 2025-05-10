package tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void testMainMethod() {
        // Тестирование основного потока выполнения
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }
}