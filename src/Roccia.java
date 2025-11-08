public class Roccia extends Ostacolo {
    public Roccia(Posizione posizione, boolean letale) {
        super(Costanti.SIMBOLO_ROCCIA, posizione, letale);
    }

    @Override
    public boolean interagisci(Giocatore giocatore) {
        if (giocatore instanceof Golem || giocatore instanceof Trex) {
            if (isLetale()) {
                if (giocatore.getMosseRimanenti() < 3) {
                    giocatore.elimina();
                    System.out.println("La roccia è esplosa.");
                    System.out.println(giocatore.getNome() + " è stato eliminato.");
                    return true;
                }
                giocatore.usaMossa();
                giocatore.usaMossa();
                System.out.println("Hai abbattuto una roccia esplosiva.");
                return true;
            } else {
                if (giocatore.getMosseRimanenti() < 2)
                    return false;
                giocatore.usaMossa();
                System.out.println("Hai abbattuto una roccia.");
                return true;
            }
        }
        if (isLetale()) {
            giocatore.elimina();
            System.out.println("La roccia è esplosa.");
            System.out.println(giocatore.getNome() + " è stato eliminato.");
            return true;
        }
        return false;
    }
}
