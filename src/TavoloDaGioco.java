
public class TavoloDaGioco {
    private final int righe;
    private final int colonne;
    private final Giocatore[] giocatori = new Giocatore[Costanti.NUMERO_GIOCATORI];
    private final OggettoDiGioco[] oggettiDiGioco;
    int numMonete, numPozioni, numGemme, numRocce, numAlberi, numBotole;
    private int numeroOggettiInGioco;
    //private LogicaDiGioco logicaDiGioco;

    public TavoloDaGioco(int numMonete, int numPozioni, int numGemme, int numRocce, int numAlberi, int numBotole) {
        this.righe = Costanti.RIGHE_GRIGLIA_GIOCO;
        this.colonne = Costanti.COLONNE_GRIGLIA_GIOCO;

        this.numMonete = numMonete >= 0 ? numMonete : 0;
        this.numPozioni = numPozioni >= 0 ? numPozioni : 0;
        this.numGemme = numGemme >= 0 ? numGemme : 0;
        this.numRocce = numRocce >= 0 ? numRocce : 0;
        this.numAlberi = numAlberi >= 0 ? numAlberi : 0;
        this.numBotole = numBotole >= 0 ? numBotole : 0;
        int numOggetti = this.numMonete + this.numPozioni + this.numGemme + this.numRocce + this.numAlberi + numBotole;
        oggettiDiGioco = new OggettoDiGioco[numOggetti + giocatori.length];
        numeroOggettiInGioco = 0;

    }

    public Giocatore[] getGiocatori() {
        return giocatori;
    }

    public int getNumeroGiocatori() {
        return giocatori.length;
    }

    public int getNumeroGiocatoriInGioco() {
        int numeroGiocatoriInGioco = 0;
        for (Giocatore giocatore : giocatori) {
            if (!giocatore.isEliminato()) {
                numeroGiocatoriInGioco++;
            }
        }
        return numeroGiocatoriInGioco;
    }

    public Giocatore[] getGiocatoriInGioco() {
        Giocatore[] giocatoriInGioco = new Giocatore[getNumeroGiocatoriInGioco()];
        int i = 0;
        for (Giocatore giocatore : giocatori) {
            if (!giocatore.isEliminato()) {
                giocatoriInGioco[i++] = giocatore;
            }
        }
        return giocatoriInGioco;
    }

    public int getNumMonete() {
        return numMonete;
    }

    public int getRighe() {
        return righe;
    }

    public int getColonne() {
        return colonne;
    }

    /*
    public LogicaDiGioco getLogicaDiGioco() {
        return logicaDiGioco;
    }
    */

    public void inizializza() {
        creaGiocatori();
        creaOggettiDiGioco();
    }

    private void creaGiocatori() {
        int contaGiocatori = 0;

        String nome;
        boolean nomeValido;
        char simbolo = ' ';
        boolean simboloValido;

        while (contaGiocatori < giocatori.length) {
            nomeValido = true;
            simboloValido = false;

            nome = RappresentazioneTestuale.letturaControlloStringa
                    ("Inserire il nome del giocatore " + (Giocatore.getNumeroGiocatori() + 1) + ": ");
            for (int i = 0; i < giocatori.length; i++) {
                if (giocatori[i] == null)
                    break;
                else if (giocatori[i].getNome().equals(nome)) {
                    nomeValido = false;
                    System.out.println("Questo nome è già stato scelto da un altro giocatore. Riprova");
                }
            }

            if (nomeValido) {
                while (!simboloValido) {
                    simboloValido = true;

                    simbolo = RappresentazioneTestuale.letturaControlloCarattere
                            ("Inserire il simbolo per " + nome + ": ");
                    for (int i = 0; i < giocatori.length; i++) {
                        if (giocatori[i] == null) {
                            break;
                        } else if (giocatori[i].getSimbolo() == simbolo) {
                            simboloValido = false;
                            System.out.println("Questo simbolo è già stato scelto da un altro giocatore. Riprova");
                        }
                    }
                }
            }

            if (nomeValido && simboloValido) {
                //------------------------------------va a creare i personaggi e a metterli nell'array
                giocatori[contaGiocatori] =  RappresentazioneTestuale.scegliPersonaggio(nome,simbolo);
                aggiungiOggettoDiGioco(giocatori[contaGiocatori]);
                contaGiocatori++;
            }
        }
    }

    private void creaOggettiDiGioco() {
        boolean cellaValida;
        int rigaCasuale;
        int colonnaCasuale;
        Posizione posizioneCasuale;
        for (int i = 0; i < oggettiDiGioco.length; i++) {
            cellaValida = false;
            while(!cellaValida) {
                cellaValida = true;
                rigaCasuale = (int) (Math.random() * righe);
                colonnaCasuale = (int) (Math.random() * colonne);
                posizioneCasuale = new Posizione(rigaCasuale, colonnaCasuale);
                for (int j = 0; j < giocatori.length; j++) {
                    if (giocatori[j] == null)
                        break;
                    if (posizioneCasuale.equals(giocatori[j].getPosizioneIniziale())) {
                        cellaValida = false;
                        break;
                    }
                }

                for (int j = 0; j < oggettiDiGioco.length; j++) {
                    if (oggettiDiGioco[j] == null)
                        break;
                    if (oggettiDiGioco[j].getPosizione().equals(posizioneCasuale)){
                        cellaValida = false;
                    }
                }

                if (cellaValida) {
                    if (numeroOggettiInGioco < giocatori.length + numMonete / 2) {
                        aggiungiOggettoDiGioco(new Moneta(posizioneCasuale, '$'));
                    } else if (numeroOggettiInGioco < giocatori.length + numMonete) {
                        aggiungiOggettoDiGioco(new Moneta(posizioneCasuale, '€'));
                    } else if (numeroOggettiInGioco < giocatori.length + numMonete + numPozioni) {
                        aggiungiOggettoDiGioco(new Pozione(posizioneCasuale));
                    } else if (numeroOggettiInGioco < giocatori.length + numMonete + numPozioni + numGemme) {
                        aggiungiOggettoDiGioco(new Gemma(posizioneCasuale));
                    } else if (numeroOggettiInGioco < giocatori.length + numMonete + numPozioni + numGemme + numRocce - 2) {
                        aggiungiOggettoDiGioco(new Roccia(posizioneCasuale, false));
                    } else if (numeroOggettiInGioco < giocatori.length + numMonete + numPozioni + numGemme + numRocce) {
                        aggiungiOggettoDiGioco(new Roccia(posizioneCasuale, true));
                    } else if (numeroOggettiInGioco < giocatori.length + numMonete + numPozioni + numGemme + numRocce + numAlberi - 3) {
                        aggiungiOggettoDiGioco(new Albero(posizioneCasuale, false));
                    } else if (numeroOggettiInGioco < giocatori.length + numMonete + numPozioni + numGemme + numRocce + numAlberi) {
                        aggiungiOggettoDiGioco(new Albero(posizioneCasuale, true));
                    } else if (numeroOggettiInGioco < giocatori.length + numMonete + numPozioni + numGemme + numRocce + numAlberi + numBotole) {
                        aggiungiOggettoDiGioco(new Botola(posizioneCasuale));
                    }
                }
            }
        }
    }

    private boolean aggiungiOggettoDiGioco(OggettoDiGioco oggettoDiGioco) {
        if (numeroOggettiInGioco < oggettiDiGioco.length) {
            oggettiDiGioco[numeroOggettiInGioco++] = oggettoDiGioco;
            return true;
        }
        return false;
    }

    public boolean posizioneOccupata(int riga, int colonna) {
        Posizione posizione = LogicaDiGioco.correggiPosizione(riga, colonna);
        for (int i = 0; i < oggettiDiGioco.length; i++) {
            if (oggettiDiGioco[i] == null) continue;
            else if (oggettiDiGioco[i].getPosizione().equals(posizione)) {
                if (oggettiDiGioco[i] instanceof Giocatore && ((Giocatore) oggettiDiGioco[i]).isEliminato()) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    public OggettoDiGioco getOggettodiGioco(int riga, int colonna) {
        Posizione posizione = LogicaDiGioco.correggiPosizione(riga, colonna);
        for (int i = 0; i < oggettiDiGioco.length; i++) {
            if (oggettiDiGioco[i] == null) continue;
            else if (oggettiDiGioco[i].getPosizione().equals(posizione)) {
                if (oggettiDiGioco[i] instanceof Giocatore) {
                    if (((Giocatore) oggettiDiGioco[i]).isEliminato()) //SE L'OGGETTO È UN GIOCATORE ELIMINATO NON LO CONSIDERA
                        continue;
                }
                return oggettiDiGioco[i];
            }
        }
        return null;
    }

    public boolean rimuoviOggettoDiGioco(int riga, int colonna) {
        Posizione posizione = LogicaDiGioco.correggiPosizione(riga, colonna);
        for (int i = 0; i < oggettiDiGioco.length; i++) {
            if (oggettiDiGioco[i] == null) continue;
            else if (oggettiDiGioco[i].getPosizione().equals(posizione)) {
                oggettiDiGioco[i] = null;
                return true;
            }
        }
        return false;
    }

    public void riposizionaGiocatore(Giocatore giocatore) {
        boolean cellaValida = false;
        int rigaCasuale;
        int colonnaCasuale;
        while (!cellaValida) {
            rigaCasuale = (int) (Math.random() * righe);
            colonnaCasuale = (int) (Math.random() * colonne);
            if (!posizioneOccupata(rigaCasuale, colonnaCasuale)) {
                cellaValida = true;
                giocatore.setPosizione(rigaCasuale, colonnaCasuale);
            }
        }
    }

    private void appendRowString(StringBuilder sb, int col1, int col2, int col3, int col4, int col5, int col6, int col7, int col8, int col9, int col10,
                                 String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10) {
        sb.append(String.format("%-" + col1 + "s", val1))
                .append(String.format("%-" + col2 + "s", val2))
                .append(String.format("%-" + col3 + "s", val3))
                .append(String.format("%-" + col4 + "s", val4))
                .append(String.format("%-" + col5 + "s", val5))
                .append(String.format("%-" + col6 + "s", val6))
                .append(String.format("%-" + col7 + "s", val7))
                .append(String.format("%-" + col8 + "s", val8))
                .append(String.format("%-" + col9 + "s", val9))
                .append(String.format("%-" + col10 + "s", val10))
                .append("\n");
    }


    private void appendRow(StringBuilder sb, int col1, int col2, int col3, int col4, int col5, int col6, int col7, int col8, int col9, int col10,
                           String val1, char val2, int val3, int val4, int val5, int val6, String val7, int val8, int val9, int val10) {
        sb.append(String.format("%-" + col1 + "s", val1))
                .append(String.format(" %-" + col2 + "s", val2))
                .append(String.format(" %-" + col3 + "d", val3))
                .append(String.format("%-" + col4 + "d", val4))
                .append(String.format("%-" + col5 + "d", val5))
                .append(String.format("%-" + col6 + "d", val6))
                .append(String.format("%-" + col7 + "s", val7))
                .append(String.format("%-" + col8 + "s", val8))
                .append(String.format("%-" + col9 + "s", val9))
                .append(String.format("%-" + col10 + "s", val10));
        //.append("\n");
    }

    private void appendDivider(StringBuilder sb, int col1, int col2, int col3, int col4, int col5, int col6, int col7, int col8, int col9, int col10) {
        sb.append("-".repeat(col1 + col2 + col3 + col3 + col4 + col5 + col6 + col7 + col8 + col9 + col10)).append("\n");
    }

    @Override
    public String toString() {
        StringBuilder testo = new StringBuilder();

        for (int i = 0; i < colonne * 4 + 1; i++) {
            testo.append("-");
        }
        testo.append("\n");

        for (int i = 0; i < righe; i++) {
            testo.append("| ");
            for (int j = 0; j < colonne; j++) {
                boolean posizioneOccupata = posizioneOccupata(i, j);
                if (!posizioneOccupata)
                    testo.append(" ");
                else {
                    OggettoDiGioco oggettoCorrente = getOggettodiGioco(i, j);
                    if (oggettoCorrente instanceof Giocatore) {
                        if (((Giocatore) oggettoCorrente).isEliminato())
                            testo.append(" ");
                        else
                            testo.append(oggettoCorrente.getSimbolo());
                    } else {
                        if (oggettoCorrente == null)
                            testo.append(" ");
                        else
                            testo.append(oggettoCorrente.getSimbolo());
                    }
                }
                testo.append(" | ");
            }
            testo.append("\n");

            for (int j = 0; j < colonne  * 4 + 1; j++) {
                testo.append("-");
            }
            testo.append("\n");
        }

        //tabella

        //Numero giocatori
        int numeroGiocatori = 0;
        testo.append("Totale iniziale del numero di giocatori: ");
        for (int j = 0; j < giocatori.length; j++) {
            if (giocatori[j] != null) {
                numeroGiocatori++;
            }
        }
        //testo.append(numeroGiocatori + "    Turno attuale del giocatore: " + LogicaDiGioco.getNomeGiocatoreCorrente() + "\n");
        testo.append(getNumeroGiocatoriInGioco() + "    Turno attuale del giocatore: " +  "n.d." + "\n");


        //StringBuilder table = new StringBuilder();
        // Column widths
        int colSeparator = 1;
        int col1Width = 10;//nome
        int col2Width = 8;//simbolo
        int col3Width = 11;//patrimonio
        int col4Width = 16;//mosse rimanenti
        int col5Width = 6;//no. gemme
        int col6Width = 8;//no. pozioni
        int col7Width = 12;//nome personaggio
        int col8Width = 6;//forza
        int col9Width = 6;//magia
        int col10Width = 10;//velocita'



        //Header
        appendRowString(testo, col1Width, col2Width, col3Width, col4Width, col5Width, col6Width, col7Width, col8Width, col9Width, col10Width,
                "nome", "|simbolo|", "patrimonio|", "mosse rimanenti|", "gemme|", "pozioni|", "personaggio|", "forza|", "magia|", "velocita'|");

        //tabella di informazioni
        for (int j = 0; j < giocatori.length; j++) {
            if (giocatori[j] != null && !giocatori[j].isEliminato()) {

                appendRow(testo, col1Width, col2Width, col3Width, col4Width, col5Width, col6Width, col7Width, col8Width, col9Width, col10Width,
                        giocatori[j].getNome(),
                        giocatori[j].getSimbolo(),
                        giocatori[j].getPatrimonio(),
                        giocatori[j].getMosseRimanenti(),
                        giocatori[j].getNumeroGemme(),
                        giocatori[j].getNumeroPozioni(),
                        giocatori[j].getClass().getName(),
                        giocatori[j].getForza(),
                        giocatori[j].getMagia(),
                        giocatori[j].getVelocita()
                );
            }
            testo.append("\n");
        }

        return testo.toString();
    }
}
