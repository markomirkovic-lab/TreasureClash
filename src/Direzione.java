public enum Direzione {
    NORD(-1, 0),
    SUD(1, 0),
    EST(0, 1),
    OVEST(0, -1);

    private final int deltaRiga;
    private final int deltaColonna;

    Direzione(int deltaRiga, int deltaColonna) {
        this.deltaRiga = deltaRiga;
        this.deltaColonna = deltaColonna;
    }

    public int getDeltaRiga() {
        return deltaRiga;
    }

    public int getDeltaColonna() {
        return deltaColonna;
    }
}
