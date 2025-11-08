public class Partita {
    private LogicaDiGioco logicaDiGioco;

    public Partita(LogicaDiGioco logicaDiGioco) {
        this.logicaDiGioco = logicaDiGioco;
    }

    public void gioca() {
        logicaDiGioco.getTavoloDaGioco().inizializza();
        RappresentazioneTestuale.stampaTitolo();
        int scelta = RappresentazioneTestuale.menuGiocatore();
        while (scelta != 0) {
            switch (scelta) {
                case 1:
                    System.out.println(logicaDiGioco.getTavoloDaGioco());
                    break;
                case 2:
                    logicaDiGioco.eseguiTurno();
                    break;
                case 3:
                    System.out.println(logicaDiGioco);;
                    break;
            }
            if(logicaDiGioco.partitaFinita()) {
                scelta = 0;
            } else {
                scelta = RappresentazioneTestuale.menuGiocatore();
                System.out.println();
            }
        }
        System.out.println(logicaDiGioco.getTavoloDaGioco());
        dichiaraVincitore();
    }

    private void dichiaraVincitore() {
        Giocatore[] giocatori = logicaDiGioco.getTavoloDaGioco().getGiocatori();
        for (int i = 0; i < giocatori.length; i++) {
            if (!giocatori[i].isEliminato()) {
                System.out.println("Vincitore: " + giocatori[i].getNome());
                return;
            }
        }
    }
}