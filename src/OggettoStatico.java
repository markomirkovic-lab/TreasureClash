public abstract class OggettoStatico implements OggettoDiGioco {
    private final char simbolo;
    private final Posizione posizione;

    public OggettoStatico(char simbolo, Posizione posizione) {
        this.simbolo = simbolo;
        this.posizione = posizione;
    }

    @Override
    public char getSimbolo() {
        return simbolo;
    }

    @Override
    public Posizione getPosizione() {
        return posizione;
    }
}
