public class Pozione extends Collezionabile {
    public Pozione(Posizione posizione) {
        super(Costanti.SIMBOLO_POZIONE, posizione);
    }

    @Override
    public boolean interagisci(Giocatore giocatore) {
        giocatore.raccogliPozione();
        return true;
    }
}
