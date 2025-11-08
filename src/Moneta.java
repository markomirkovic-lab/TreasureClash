public class Moneta extends Collezionabile {
    private final int valore;

    public Moneta(Posizione posizione, char simbolo) {
        super(simbolo, posizione);
        if (simbolo == Costanti.SIMBOLO_MONETA_1)
            valore = 1;
        else if (simbolo == Costanti.SIMBOLO_MONETA_2)
            valore = 2;
        else
            throw new IllegalArgumentException("ERRORE: impossibile istanziare una moneta con un simbolo diverso da " +
                    Costanti.SIMBOLO_MONETA_1 + " o " + Costanti.SIMBOLO_MONETA_2);
    }

    public int getValore() {
        return valore;
    }

    @Override
    public boolean interagisci(Giocatore giocatore) {
        giocatore.raccogliMoneta(valore);
        return true;
    }
}
