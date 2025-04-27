package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca ucznia, zawiera dane osobowe i listę ocen.
 */
public class Student {
    private String firstName;
    private String lastName;
    private List<Grade> grades = new ArrayList<>();

    /**
     * Tworzy nowego ucznia.
     * @param firstName imię ucznia
     * @param lastName nazwisko ucznia
     */
    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Dodaje ocenę do ucznia.
     * @param grade ocena do dodania
     */
    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    /**
     * Usuwa ocenę uczniowi.
     * @param grade ocena do usunięcia
     */
    public void removeGrade(Grade grade) {
        grades.remove(grade);
    }

    /**
     * Zwraca listę ocen ucznia.
     * @return lista ocen
     */
    public List<Grade> getGrades() {
        return grades;
    }

    /**
     * Oblicza średnią ocen ucznia.
     * @return średnia ocen (lub 0.0 jeśli brak ocen)
     */
    public double calculateAverage() {
        if (grades.isEmpty()) return 0.0;
        return grades.stream().mapToInt(Grade::getValue).average().orElse(0.0);
    }

    /**
     * Zwraca pełne imię i nazwisko ucznia.
     * @return imię i nazwisko
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Zwraca ucznia jako czytelny tekst (pełne imię + średnia ocen).
     * @return tekstowa reprezentacja ucznia
     */
    @Override
    public String toString() {
        return getFullName() + " (średnia: " + String.format("%.2f", calculateAverage()) + ")";
    }
}
