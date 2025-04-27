package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa zarządzająca listą uczniów - dodawanie, usuwanie, zapis i odczyt z pliku.
 */
public class StudentManager {
    private List<Student> students = new ArrayList<>();

    /**
     * Dodaje nowego ucznia do listy.
     * @param student uczeń do dodania
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Usuwa ucznia z listy.
     * @param student uczeń do usunięcia
     */
    public void removeStudent(Student student) {
        students.remove(student);
    }

    /**
     * Zwraca listę wszystkich uczniów.
     * @return lista uczniów
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Zapisuje uczniów i ich oceny do pliku tekstowego.
     * @param filename nazwa pliku
     * @throws IOException jeśli wystąpi błąd podczas zapisu
     */
    public void saveToFile(String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Student s : students) {
                pw.println(s.getFullName());
                for (Grade g : s.getGrades()) {
                    pw.println(g.getSubject() + "," + g.getValue());
                }
                pw.println("---"); // separator uczniów
            }
        }
    }

    /**
     * Wczytuje uczniów i ich oceny z pliku tekstowego.
     * @param filename nazwa pliku
     * @throws IOException jeśli wystąpi błąd podczas odczytu
     */
    public void loadFromFile(String filename) throws IOException {
        students.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            Student current = null;
            while ((line = br.readLine()) != null) {
                if (line.equals("---")) {
                    current = null;
                } else if (current == null) {
                    String[] parts = line.split(" ");
                    current = new Student(parts[0], parts[1]);
                    students.add(current);
                } else {
                    String[] parts = line.split(",");
                    current.addGrade(new Grade(parts[0], Integer.parseInt(parts[1])));
                }
            }
        }
    }
}
