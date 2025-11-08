public class LogicaDiGioco {
    private int indiceGiocatoreCorrente = 0;
    private final TavoloDaGioco tavoloDaGioco;

    public LogicaDiGioco(TavoloDaGioco tavoloDaGioco) {
        this.tavoloDaGioco = tavoloDaGioco;
    }


    //metodo che mi controlla se il giocatore è un golem e se lo è si può attivare l'abilità
    private boolean controllaSeGolem(Giocatore giocatore) {
        if(giocatore instanceof Golem) {
            boolean usaAbilita = RappresentazioneTestuale.menuSfida("Vuoi attivare la tua abilità speciale? (0. No; 1. Si')");
            if(usaAbilita) {
                giocatore.attivaAbilita();
                return true;
            }
        }
        return false; //l'abilità non viene utilizzata
    }

    private boolean attivaAbilita(Giocatore giocatore) {
        boolean usaAbilita = RappresentazioneTestuale.menuSfida("Vuoi attivare la tua abilità speciale? (0. No; 1. Si')");
        if(usaAbilita) {
            giocatore.attivaAbilita();
            return true;
        }
        return false; //l'abilità non viene utilizzata
    }

    public TavoloDaGioco getTavoloDaGioco() {
        return tavoloDaGioco;
    }

    public boolean partitaFinita() {
        Giocatore[] giocatori = tavoloDaGioco.getGiocatori();
        int contaGiocatoriEliminati = 0;
        for (int i = 0; i < giocatori.length; i++) {
            if (giocatori[i].getPatrimonio() >= tavoloDaGioco.getNumMonete())
                return true;
            if (giocatori[i].isEliminato())
                contaGiocatoriEliminati++;
        }
        if (contaGiocatoriEliminati == giocatori.length - 1)
            return true;
        return false;
    }

    private void muoviGiocatore(Giocatore giocatore, Direzione direzione) {
        int nuovaRiga = giocatore.getPosizione().getRiga() + direzione.getDeltaRiga();
        int nuovaColonna = giocatore.getPosizione().getColonna() + direzione.getDeltaColonna();

        if (!tavoloDaGioco.posizioneOccupata(nuovaRiga, nuovaColonna)){
            giocatore.setPosizione(nuovaRiga, nuovaColonna);
        } else {
            OggettoDiGioco oggettoIncontrato = tavoloDaGioco.getOggettodiGioco(nuovaRiga, nuovaColonna);
            if (oggettoIncontrato instanceof Giocatore) {
                int gemmeGiocatore = giocatore.getNumeroGemme();
                boolean gemmaUsata = oggettoIncontrato.interagisci(giocatore);
                if (gemmaUsata) {
                    if (giocatore.getNumeroGemme() < gemmeGiocatore)
                        tavoloDaGioco.riposizionaGiocatore(giocatore);
                    else
                        tavoloDaGioco.riposizionaGiocatore((Giocatore) oggettoIncontrato);
                }
            } else if (oggettoIncontrato != null) {
                boolean puoMuoversi = oggettoIncontrato.interagisci(giocatore);
                if (puoMuoversi) { //NON VALE PER L'INTERAZIONE CON UN ALTRO GIOCATORE
                    tavoloDaGioco.rimuoviOggettoDiGioco(nuovaRiga, nuovaColonna);
                    giocatore.setPosizione(nuovaRiga, nuovaColonna);
                }
            }
        }
    }


    public void eseguiTurno() {
        Giocatore giocatoreCorrente = tavoloDaGioco.getGiocatori()[indiceGiocatoreCorrente];
        if (giocatoreCorrente.isEliminato()) {
            indiceGiocatoreCorrente = (indiceGiocatoreCorrente + 1) % tavoloDaGioco.getNumeroGiocatori();
            giocatoreCorrente = tavoloDaGioco.getGiocatori()[indiceGiocatoreCorrente];
        }
        turno(giocatoreCorrente);
        indiceGiocatoreCorrente = (indiceGiocatoreCorrente + 1) % tavoloDaGioco.getNumeroGiocatori();
    }

    public String getNomeGiocatoreCorrente() {
        String nomeGioctoreCorrente = tavoloDaGioco.getGiocatori()[indiceGiocatoreCorrente].getNome();
        return nomeGioctoreCorrente;
    }

    private void turno(Giocatore giocatore) {
        if (giocatore.isEliminato())
            return;
        if (giocatore.isDentroBotola()) {
            giocatore.setDentroBotola(false);
            System.out.println(giocatore.getNome() + " salta il turno.");
            System.out.println(tavoloDaGioco);
            return;
        }
        if (giocatore.getPosizione().equals(new Posizione(-1, -1))) {
            tavoloDaGioco.riposizionaGiocatore(giocatore);
            System.out.println(giocatore.getNome() + " è stato riposizionato sulla griglia di gioco.");
        }

        boolean abilitaAttiva = false;
        if (!(giocatore instanceof Trex))
            abilitaAttiva = attivaAbilita(giocatore);
        if (abilitaAttiva) {
            if (giocatore instanceof Golem)
                giocatore.setVelocita(giocatore.getVelocita() + 3);
            else if (giocatore instanceof Mago) {
                tavoloDaGioco.riposizionaGiocatore(giocatore);
            } else if (giocatore instanceof Bandito) {
                int numeroGiocatori = tavoloDaGioco.getNumeroGiocatoriInGioco();
                System.out.println("Scegli un giocatore a cui rubare una moneta: ");
                System.out.println(this);
                int indiceGiocatoreScelto = RappresentazioneTestuale.leggiInteroInRange(1, numeroGiocatori);
                Giocatore giocatoreScelto = tavoloDaGioco.getGiocatoriInGioco()[indiceGiocatoreScelto - 1];
               ((Bandito) giocatore).deruba(giocatoreScelto);
            }
        }

        giocatore.setMosseRimanenti(Dado.lancia() + giocatore.getVelocita());
        System.out.println("Giocatore " + giocatore.getNome() + " (" + giocatore.getSimbolo() + ") -- Lancia dado: " + giocatore.getMosseRimanenti());
        while (giocatore.getMosseRimanenti() > 0) {
            if (partitaFinita())
                return;
            if (giocatore.isEliminato() || giocatore.isDentroBotola()) {
                System.out.println(tavoloDaGioco);
                return;
            }
            System.out.println(tavoloDaGioco);
            System.out.println("Mosse rimaste: " + giocatore.getMosseRimanenti());
            Direzione direzione = RappresentazioneTestuale.menuMovimento();
            muoviGiocatore(giocatore, direzione);
            giocatore.usaMossa();
        }

        //reset della velocità del Golem
        if(giocatore instanceof Golem) {
            ((Golem) giocatore).resetVelocita();
        }

        System.out.println(tavoloDaGioco);
    }

    public static Posizione correggiPosizione(int riga, int colonna) {
        if (riga < 0)
            riga = Costanti.RIGHE_GRIGLIA_GIOCO - 1;
        if (riga > Costanti.RIGHE_GRIGLIA_GIOCO - 1)
            riga = 0;
        if (colonna < 0)
            colonna = Costanti.COLONNE_GRIGLIA_GIOCO - 1;
        if (colonna > Costanti.COLONNE_GRIGLIA_GIOCO - 1)
            colonna = 0;
        return new Posizione(riga, colonna);
    }

    @Override
    public String toString() {
        StringBuilder testo = new StringBuilder();
        Giocatore[] giocatori = tavoloDaGioco.getGiocatori();
        for (int i = 0; i < giocatori.length; i++) {
            if (giocatori[i].isEliminato())
                continue;
            testo.append(giocatori[i]);
            testo.append("\n");
        }
        return testo.toString();
    }
}
