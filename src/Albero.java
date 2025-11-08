public class Albero extends Ostacolo {
    public Albero(Posizione posizione, boolean letale) {
        super(Costanti.SIMBOLO_ALBERO, posizione, letale);
    }

    @Override
    public boolean interagisci(Giocatore giocatore) {
        if (giocatore.getMosseRimanenti() < 2)
            return false;
        if (isLetale()) {
            giocatore.elimina();
            System.out.println("L'albero ti ha schiacciato.");
            System.out.println(giocatore.getNome() + " è stato eliminato.");
            if (giocatore.getMosseRimanenti() < 2)
                return false;
            return true;
        }
        giocatore.usaMossa();
        return true;
    }
}