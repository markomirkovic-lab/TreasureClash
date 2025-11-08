public class Costanti {
    public static final int RIGHE_GRIGLIA_GIOCO = 13;
    public static final int COLONNE_GRIGLIA_GIOCO = 17;

    public static final int NUMERO_GIOCATORI = 4;
    public static final Posizione SPAWN_GIOCATORE_1 = new Posizione(0, 0);
    public static final Posizione SPAWN_GIOCATORE_2 = new Posizione(0, COLONNE_GRIGLIA_GIOCO - 1);
    public static final Posizione SPAWN_GIOCATORE_3 = new Posizione(RIGHE_GRIGLIA_GIOCO - 1, 0);
    public static final Posizione SPAWN_GIOCATORE_4 = new Posizione(RIGHE_GRIGLIA_GIOCO - 1, COLONNE_GRIGLIA_GIOCO - 1);


    public static final char SIMBOLO_MONETA_1 = '$';
    public static final char SIMBOLO_MONETA_2 = '€';
    public static final char SIMBOLO_POZIONE = '&';
    public static final char SIMBOLO_GEMMA = '%';
    public static final char SIMBOLO_ROCCIA = '@';
    public static final char SIMBOLO_ALBERO = '#';
    public static final char SIMBOLO_BOTOLA = ' ';


    //--------------------------------------------------------Costanti con il numero di oggetti da creare---------------------------------------------
    public static final int MONETE = 10;
    public static final int POZIONI = 3;
    public static final int GEMME = 5;
    public static final int ROCCE = 5;
    public static final int ALBERI = 7;
    public static final int BOTOLE = 3;

    //------------------------------------------------costanti dei valori dei personaggi-------------------------------------------------------------

    //valori per il Trex
    public static final int TREX_FORZA = 4;
    public static final int TREX_VELOCITA = 3;
    public static final int TREX_MAGIA = 3;

    //valori per il bandito
    public static final int BANDITO_FORZA = 3;
    public static final int BANDITO_VELOCITA = 5;
    public static final int BANDITO_MAGIA = 2;

    //valori per il golem
    public static final int GOLEM_FORZA = 6;
    public static final int GOLEM_VELOCITA = 1;
    public static final int GOLEM_MAGIA = 3;

    //valori per il mago
    public static final int MAGO_FORZA = 3;
    public static final int MAGO_VELOCITA = 2;
    public static final int MAGO_MAGIA = 5;
}