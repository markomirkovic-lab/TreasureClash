public class Botola extends Ostacolo {
    public Botola(Posizione posizione) {
        super(Costanti.SIMBOLO_BOTOLA, posizione, true);
    }

    @Override
    public boolean interagisci(Giocatore giocatore) {
        giocatore.setDentroBotola(true);
        System.out.println("Sei caduto in una botola.");
        return false;
    }
}
