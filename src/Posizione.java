/**
 * Rappresenta una cella sulla griglia di gioco
 */
public class Posizione {
    private int riga;
    private int colonna;

    public Posizione(int riga, int colonna) {
        this.riga = riga;
        this.colonna = colonna;
    }

    public int getRiga() {
        return riga;
    }

    public int getColonna() {
        return colonna;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Posizione posizione = (Posizione) o;
        return riga == posizione.riga && colonna == posizione.colonna;
    }
}
