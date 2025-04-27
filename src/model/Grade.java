package model;

/**
 * Klasa reprezentująca pojedynczą ocenę ucznia z danego przedmiotu.
 */
public class Grade {
    private String subject;
    private int value;

    /**
     * Tworzy nową ocenę.
     * @param subject przedmiot, z którego wystawiono ocenę
     * @param value wartość oceny (od 1 do 6)
     * @throws IllegalArgumentException jeśli ocena jest spoza zakresu 1-6
     */
    public Grade(String subject, int value) {
        if (value < 1 || value > 6) {
            throw new IllegalArgumentException("Ocena musi być od 1 do 6");
        }
        this.subject = subject;
        this.value = value;
    }

    /**
     * Zwraca nazwę przedmiotu.
     * @return przedmiot
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Zwraca wartość oceny.
     * @return ocena (1-6)
     */
    public int getValue() {
        return value;
    }

    /**
     * Zwraca ocenę jako czytelny tekst.
     * @return przedmiot i ocena w formie tekstowej
     */
    @Override
    public String toString() {
        return subject + ": " + value;
    }
}
