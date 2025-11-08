public class TestPartita {
    public static void main(String[] args) {
        TavoloDaGioco tavoloDaGioco = new TavoloDaGioco(Costanti.MONETE, Costanti.POZIONI, Costanti.GEMME,Costanti.ROCCE,Costanti.ALBERI, Costanti.BOTOLE);
        LogicaDiGioco logicaDiGioco = new LogicaDiGioco(tavoloDaGioco);
        Partita partita = new Partita(logicaDiGioco);
        partita.gioca();
        RappresentazioneTestuale.chiusuraScanner();



        /**
        tavoloDaGioco.inizializza();
        System.out.println(tavoloDaGioco);
        while (true) {
            logicaDiGioco.turno(tavoloDaGioco.giocatori[0]);
            logicaDiGioco.turno(tavoloDaGioco.giocatori[2]);
        }

        logicaDiGioco.muoviGiocatore(tavoloDaGioco.giocatori[0], Direzione.SUD);
        System.out.println(tavoloDaGioco);
        logicaDiGioco.muoviGiocatore(tavoloDaGioco.giocatori[0], Direzione.OVEST);
        System.out.println(tavoloDaGioco);
        logicaDiGioco.muoviGiocatore(tavoloDaGioco.giocatori[0], Direzione.EST);
        System.out.println(tavoloDaGioco);
        logicaDiGioco.muoviGiocatore(tavoloDaGioco.giocatori[0], Direzione.EST);
        System.out.println(tavoloDaGioco);
        logicaDiGioco.muoviGiocatore(tavoloDaGioco.giocatori[0], Direzione.EST);
        System.out.println(tavoloDaGioco);
        logicaDiGioco.muoviGiocatore(tavoloDaGioco.giocatori[0], Direzione.EST);
        System.out.println(tavoloDaGioco);
        logicaDiGioco.muoviGiocatore(tavoloDaGioco.giocatori[0], Direzione.EST);
        System.out.println(tavoloDaGioco);
        logicaDiGioco.muoviGiocatore(tavoloDaGioco.giocatori[0], Direzione.SUD);
        System.out.println(tavoloDaGioco);
        logicaDiGioco.muoviGiocatore(tavoloDaGioco.giocatori[0], Direzione.OVEST);
        System.out.println(tavoloDaGioco);
        logicaDiGioco.muoviGiocatore(tavoloDaGioco.giocatori[0], Direzione.NORD);
        System.out.println(tavoloDaGioco);
         */
    }
}