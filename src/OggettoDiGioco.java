public interface OggettoDiGioco {
    char getSimbolo();
    Posizione getPosizione();

    //Ritorna true se l'oggetto viene rimosso dal tabellone
    boolean interagisci(Giocatore giocatore);
}
