public class Gemma extends Collezionabile {
    public Gemma(Posizione posizione) {
        super(Costanti.SIMBOLO_GEMMA, posizione);
    }

    @Override
    public boolean interagisci(Giocatore giocatore) {
        giocatore.raccogliGemma();
        return true;
    }
}
