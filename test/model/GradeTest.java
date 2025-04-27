package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testy jednostkowe klasy Grade.
 */
class GradeTest {

    /**
     * Test tworzenia poprawnej oceny.
     * Sprawdza, czy po utworzeniu oceny jej przedmiot i wartość są poprawne.
     */
    @Test
    void createValidGrade() {
        Grade grade = new Grade("Matematyka", 5);
        assertEquals("Matematyka", grade.getSubject());
        assertEquals(5, grade.getValue());
    }

    /**
     * Test tworzenia niepoprawnej oceny.
     * Sprawdza, czy przy próbie utworzenia oceny spoza zakresu 1-6 rzucany jest wyjątek.
     */
    @Test
    void createInvalidGradeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Grade("Historia", 8));
    }

}
