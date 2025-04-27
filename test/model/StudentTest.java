package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testy jednostkowe klasy Student.
 */
class StudentTest {

    /**
     * Test dodawania ocen i obliczania średniej.
     * Sprawdza, czy średnia ocen jest prawidłowo wyliczana po dodaniu kilku ocen.
     */
    @Test
    void addGradeAndCalculateAverage() {
        Student student = new Student("Jan", "Kowalski");
        student.addGrade(new Grade("Matematyka", 5));
        student.addGrade(new Grade("Historia", 3));
        assertEquals(4.0, student.calculateAverage(), 0.01);
    }

    /**
     * Test obliczania średniej bez ocen.
     * Sprawdza, czy średnia wynosi 0.0, gdy uczeń nie ma żadnych ocen.
     */
    @Test
    void calculateAverageNoGrades() {
        Student student = new Student("Anna", "Nowak");
        assertEquals(0.0, student.calculateAverage(), 0.01);
    }

    /**
     * Test usuwania oceny ucznia.
     * Sprawdza, czy ocena jest poprawnie usuwana z listy ocen ucznia.
     */
    @Test
    void removeGradeCorrectly() {
        Student student = new Student("Marek", "Zielinski");
        Grade grade = new Grade("Biologia", 4);
        student.addGrade(grade);
        student.removeGrade(grade);
        assertTrue(student.getGrades().isEmpty());
    }
}
