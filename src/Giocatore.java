/**
 * Le statistiche di un giocatore sono Forza, Velocità e Magia e sono dei valori interi,
 * ogni personaggio inoltre ha una sua abilità unica.
 * La forza viene sommata al lancio del dado del giocatore durante una sfida.
 * La velocità viene sommata al lancio del dado del giocatore per stabilire il numero di caselle in cui ci si può muovere.
 * La magia indica il numero di volte in cui è possibile utilizzare la propria abilità.
 */
public abstract class Giocatore implements OggettoDiGioco {
    private final String nome;
    private final char simbolo;
    private int patrimonio, numeroGemme, numeroPozioni;
    private int mosseRimanenti = 0;
    private boolean eliminato;
    private boolean dentroBotola;

    private final Posizione posizioneIniziale;
    private Posizione posizione;
    private static int numeroGiocatori = 0;

    private final int forza;
    private int velocita;
    private int magia; //numero di volte che può usare un'abilità
    boolean scavalcaRocce;



    public Giocatore(String nome, char simbolo, int forza, int velocita, int magia, boolean scavalcaRocce) {
        this.nome = nome == null || nome.isBlank() ? "Giocatore " + numeroGiocatori + 1 : nome;
        this.simbolo = simbolo;
        this.patrimonio = 0;
        this.numeroGemme = 0;
        this.numeroPozioni = 0;
        this.forza = forza;
        this.velocita = velocita;
        this.magia = magia;
        this.scavalcaRocce = scavalcaRocce;
        numeroGiocatori++;
        switch(numeroGiocatori) {
            case 1:
                posizioneIniziale = Costanti.SPAWN_GIOCATORE_1;
                break;
            case 2:
                posizioneIniziale = Costanti.SPAWN_GIOCATORE_2;
                break;
            case 3:
                posizioneIniziale = Costanti.SPAWN_GIOCATORE_3;
                break;
            case 4:
                posizioneIniziale = Costanti.SPAWN_GIOCATORE_4;
                break;
            default:
                posizioneIniziale = Costanti.SPAWN_GIOCATORE_1;
                break;
        }
        posizione = posizioneIniziale;
    }


    //metodo che permette di attivare l'abilità del personaggio
    public abstract void attivaAbilita();

    public int getForza() {
        return forza;
    }

    public int getVelocita() {
        return velocita;
    }

    public int getMagia() {
        return magia;
    }

    public int usaMagia() {
        magia--;
        return magia;
    }

    //metodo che mi controlla se è un T-rex
    private boolean controllaSeTrex(Giocatore giocatore) {
        if (giocatore instanceof Trex && giocatore.getMagia() > 0) {
            boolean usaAbilita = RappresentazioneTestuale.menuSfida("Vuoi attivare la tua abilità? (0. No; 1. Si')");
            if (usaAbilita) {
                giocatore.attivaAbilita(); //Chiama il metodo specifico di Trex
                return true;
            }
        }
        return false; //l'abilità non viene usata
    }

    public void setVelocita(int velocita) {
        this.velocita = velocita;
    }

    public int getNumeroPozioni() {
        return numeroPozioni;
    }


    @Override
    public Posizione getPosizione() {
        return posizione;
    }

    @Override
    public char getSimbolo() {
        return simbolo;
    }

    @Override
    public boolean interagisci(Giocatore avversario) {
        if (avversario.numeroPozioni > 0) {
            boolean avversarioUsaPozione = RappresentazioneTestuale.menuSfida(avversario.nome + " ha " + avversario.numeroPozioni +
                    " pozioni. Vuoi usarne una? \n 1. SI \n 0. NO");
            if (avversarioUsaPozione) {
                avversario.numeroPozioni--;
                avversario.setPosizione(posizione.getRiga(), posizione.getColonna());
                if (avversario.posizione.equals(posizioneIniziale))
                    setPosizione(avversario.posizioneIniziale.getRiga(), avversario.posizioneIniziale.getColonna());
                else
                    setPosizione(posizioneIniziale.getRiga(), posizioneIniziale.getColonna());
                System.out.println("Sfida vinta da " + avversario.getNome() + " (" + avversario.getSimbolo() + ")");

                //METODO CHE CONTROLLA SE È UN TREX
                if (controllaSeTrex(this)) {
                    return false; // Sfidante perde ma non paga
                }
                avversario.rubaMoneta(this);
                if (patrimonio < 0) {
                    this.elimina();
                    System.out.println(nome + " è stato eliminato.");
                }
                return false;
            }
        }
        if (numeroPozioni > 0) {
            boolean sfidanteUsaPozione = RappresentazioneTestuale.menuSfida(nome + " ha " + numeroPozioni +
                    " pozioni. Vuoi usarne una? \n 1. SI \n 0. NO");
            if (sfidanteUsaPozione) {
                numeroPozioni--;
                if (posizione.equals(avversario.posizioneIniziale)) {
                    avversario.setPosizione(posizioneIniziale.getRiga(), posizioneIniziale.getColonna());
                }
                else
                    avversario.setPosizione(avversario.posizioneIniziale.getRiga(), avversario.posizioneIniziale.getColonna());
                System.out.println("Sfida vinta da " + getNome() + " (" + getSimbolo() + ")");

                //METODO CHE CONTROLLA SE È UN TREX
                if (controllaSeTrex(avversario)) {
                    return false; // Sfidante perde ma non paga
                }

                rubaMoneta(avversario);
                if (avversario.patrimonio < 0) {
                    avversario.elimina();
                    System.out.println(avversario.nome + " è stato eliminato.");
                }
                return false;
            }
        }
        if (avversario.numeroGemme > 0) {
            boolean avversarioUsaGemma = RappresentazioneTestuale.menuSfida(avversario.nome + " ha " + avversario.numeroGemme +
                    " gemme. Vuoi usarne una? \n 1. SI \n 0. NO");
            if (avversarioUsaGemma) {
                avversario.numeroGemme--;
                avversario.setPosizione(posizione.getRiga(), posizione.getColonna());
                if (posizioneIniziale.equals(avversario.posizione))
                    setPosizione(avversario.posizioneIniziale.getRiga(), avversario.posizioneIniziale.getColonna());
                else
                    setPosizione(posizioneIniziale.getRiga(), posizioneIniziale.getColonna());
                System.out.println("Sfida vinta da " + avversario.getNome() + " (" + avversario.getSimbolo() + ")");
                //METODO CHE CONTROLLA SE È UN TREX
                if (controllaSeTrex(this)) {
                    return false; // Sfidante perde ma non paga
                }
                if (patrimonio < 0) {
                    this.elimina();
                    System.out.println(nome + " è stato eliminato.");
                }
                return false;
            }
        }
        if (numeroGemme > 0) {
            boolean sfidanteUsaGemme = RappresentazioneTestuale.menuSfida(nome + " ha " + numeroGemme +
                    " gemme. Vuoi usarne una? \n 1. SI \n 0. NO");
            if (sfidanteUsaGemme) {
                numeroGemme--;
                avversario.setPosizione(posizione.getRiga(), posizione.getColonna());
                System.out.println("Sfida vinta da " + getNome() + " (" + getSimbolo() + ")");
                return true;
            }
        }
        int punteggioAvversario = Dado.lancia() + avversario.getForza();
        int punteggioGiocatore = Dado.lancia() + this.getForza();
        while (punteggioGiocatore == punteggioAvversario) {
            System.out.println("Sfida pari. Ritira i dadi.");
            punteggioAvversario = Dado.lancia() + avversario.getForza();
            punteggioGiocatore = Dado.lancia() + this.getForza();
        }
        System.out.println("Sfidante: " + punteggioAvversario);
        System.out.println("Sfidato: " + punteggioGiocatore);
        if (punteggioGiocatore > punteggioAvversario) {
            if (posizione.equals(avversario.posizioneIniziale))
                avversario.setPosizione(posizioneIniziale.getRiga(), posizioneIniziale.getColonna());
            else
                avversario.setPosizione(avversario.posizioneIniziale.getRiga(), avversario.posizioneIniziale.getColonna());
            System.out.println("Sfida vinta da " + getNome() + " (" + getSimbolo() + ")");
            //METODO CHE CONTROLLA SE È UN TREX
            if (controllaSeTrex(avversario)) {
                return false;
            }

            rubaMoneta(avversario);
            if (avversario.patrimonio < 0) {
                avversario.elimina();
                System.out.println(avversario.nome + " è stato eliminato.");
            }
            return false;
        } else {
            avversario.setPosizione(posizione.getRiga(), posizione.getColonna());
            if (posizioneIniziale.equals(avversario.posizione))
                setPosizione(avversario.posizioneIniziale.getRiga(), avversario.posizioneIniziale.getColonna());
            else
                setPosizione(posizioneIniziale.getRiga(), posizioneIniziale.getColonna());
            System.out.println("Sfida vinta da " + avversario.getNome() + " (" + avversario.getSimbolo() + ")");
            //METODO CHE CONTROLLA SE È UN TREX
            if (controllaSeTrex(this)) {
                return false;
            }
            avversario.rubaMoneta(this);

            if (patrimonio < 0) {
                this.elimina();
                System.out.println(nome + " è stato eliminato.");
            }
            return false;
        }
    }

    public static int getNumeroGiocatori() {
        return numeroGiocatori;
    }

    public String getNome() {
        return nome;
    }

    public boolean isEliminato() {
        return eliminato;
    }

    public void elimina() {
        this.eliminato = true;
    }

    public boolean isDentroBotola() {
        return dentroBotola;
    }

    public void setDentroBotola(boolean dentroBotola) {
        this.dentroBotola = dentroBotola;
        if (this.dentroBotola == true)
            this.posizione = new Posizione(-1, -1);
    }

    public Posizione getPosizioneIniziale() {
        return posizioneIniziale;
    }

    public int getPatrimonio() {
        return patrimonio;
    }

    public int getNumeroGemme() {
        return numeroGemme;
    }

    public int getMosseRimanenti() {
        return mosseRimanenti;
    }

    public void setMosseRimanenti(int mosseRimanenti) {
        this.mosseRimanenti = mosseRimanenti;
    }

    public int usaMossa() {
        mosseRimanenti--;
        return mosseRimanenti;
    }

    public void raccogliMoneta(int valore) {
        patrimonio += valore;
    }

    public void rubaMoneta(Giocatore giocatore) {
        if (giocatore.patrimonio > 0)
            this.patrimonio++;
        giocatore.patrimonio--;
    }

    public void raccogliGemma() {
        this.numeroGemme++;
    }

    public void raccogliPozione() {
        this.numeroPozioni++;
    }


    public void setPosizione(int riga, int colonna) {
        this.posizione = LogicaDiGioco.correggiPosizione(riga, colonna);
    }

    public boolean isScavalcaRocce() {
        return scavalcaRocce;
    }

    //stampa delle info del giocatore
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Giocatore ");
        sb.append(nome);
        sb.append("(");
        sb.append(getSimbolo());
        sb.append(") --Patrimonio: ");
        sb.append(patrimonio);
        sb.append(" -- Gemme: ");
        sb.append(numeroGemme);
        sb.append(" -- Posizioni: ");
        sb.append(numeroPozioni);
        sb.append("  ");

        return sb.toString();
    }
}
