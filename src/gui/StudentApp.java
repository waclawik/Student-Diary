package gui;

import model.Student;
import model.Grade;
import model.StudentManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class StudentApp {
    private static final String[] SUBJECTS = {"Matematyka", "Jezyk polski", "Historia", "Biologia"};
    private StudentManager manager = new StudentManager();
    private JFrame frame = new JFrame("Symulator Uczniów i Ocen");
    private DefaultListModel<Student> listModel = new DefaultListModel<>();
    private JList<Student> studentJList = new JList<>(listModel);

    public StudentApp() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();

        JButton addStudentBtn = new JButton("Dodaj ucznia");
        JButton addGradeBtn = new JButton("Dodaj ocenę");
        JButton saveBtn = new JButton("Zapisz");
        JButton loadBtn = new JButton("Wczytaj");
        JButton removeStudentBtn = new JButton("Usuń ucznia");

        buttonsPanel.add(addStudentBtn);
        buttonsPanel.add(addGradeBtn);
        buttonsPanel.add(saveBtn);
        buttonsPanel.add(loadBtn);
        buttonsPanel.add(removeStudentBtn);

        frame.add(new JScrollPane(studentJList), BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        addStudentBtn.addActionListener(e -> addStudent());
        addGradeBtn.addActionListener(e -> addGrade());
        saveBtn.addActionListener(e -> save());
        loadBtn.addActionListener(e -> load());
        removeStudentBtn.addActionListener(e -> removeStudent());

        frame.setVisible(true);
    }

    private void addStudent() {
        String firstName = JOptionPane.showInputDialog("Imię ucznia:");
        String lastName = JOptionPane.showInputDialog("Nazwisko ucznia:");
        if (firstName != null && lastName != null) {
            Student student = new Student(firstName, lastName);
            manager.addStudent(student);
            listModel.addElement(student);
        }
    }

    private void addGrade() {
        Student selected = studentJList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(frame, "Wybierz ucznia!");
            return;
        }
        JComboBox<String> subjectBox = new JComboBox<>(SUBJECTS);
        JComboBox<Integer> gradeBox = new JComboBox<>(new Integer[]{1,2,3,4,5,6});
        JPanel panel = new JPanel();
        panel.add(new JLabel("Przedmiot:"));
        panel.add(subjectBox);
        panel.add(new JLabel("Ocena:"));
        panel.add(gradeBox);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Dodaj ocenę", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            selected.addGrade(new Grade((String) subjectBox.getSelectedItem(), (Integer) gradeBox.getSelectedItem()));
            studentJList.repaint();
        }
    }

    private void save() {
        try {
            manager.saveToFile("students.txt");
            JOptionPane.showMessageDialog(frame, "Zapisano dane!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Błąd zapisu!");
        }
    }

    private void load() {
        try {
            manager.loadFromFile("students.txt");
            listModel.clear();
            for (Student s : manager.getStudents()) {
                listModel.addElement(s);
            }
            JOptionPane.showMessageDialog(frame, "Wczytano dane!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Błąd wczytywania!");
        }
    }

    private void removeStudent() {
        Student selected = studentJList.getSelectedValue();
        if (selected != null) {
            manager.removeStudent(selected);
            listModel.removeElement(selected);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentApp::new);
    }
}
