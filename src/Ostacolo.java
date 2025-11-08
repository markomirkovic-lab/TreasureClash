public abstract class Ostacolo extends OggettoStatico {
    private final boolean letale;

    public Ostacolo(char simbolo, Posizione posizione, boolean letale) {
        super(simbolo, posizione);
        this.letale = letale;
    }

    public boolean isLetale() {
        return letale;
    }
}