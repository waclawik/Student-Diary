package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

/**
 * Testy jednostkowe klasy StudentManager.
 */
class StudentManagerTest {

    /**
     * Test dodawania i usuwania ucznia z listy.
     * Sprawdza, czy uczeń może być poprawnie dodany i usunięty z zarządzanej listy.
     */
    @Test
    void addAndRemoveStudent() {
        StudentManager manager = new StudentManager();
        Student student = new Student("Kasia", "Nowak");
        manager.addStudent(student);
        assertTrue(manager.getStudents().contains(student));
        manager.removeStudent(student);
        assertFalse(manager.getStudents().contains(student));
    }

    /**
     * Test zapisu i odczytu uczniów do/z pliku.
     * Sprawdza, czy dane ucznia są poprawnie zapisywane i odczytywane z pliku tekstowego.
     */
    @Test
    void saveAndLoadStudents() throws IOException {
        StudentManager manager = new StudentManager();
        Student student = new Student("Piotr", "Wójcik");
        student.addGrade(new Grade("Matematyka", 5));
        manager.addStudent(student);

        manager.saveToFile("test_students.txt");

        StudentManager newManager = new StudentManager();
        newManager.loadFromFile("test_students.txt");

        assertEquals(1, newManager.getStudents().size());
        assertEquals("Piotr Wójcik", newManager.getStudents().get(0).getFullName());
        assertEquals(5, newManager.getStudents().get(0).getGrades().get(0).getValue());

        // Usuwanie testowego pliku
        new File("test_students.txt").delete();
    }
}
