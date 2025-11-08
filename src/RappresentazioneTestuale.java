import java.util.Scanner;

public class RappresentazioneTestuale {
    private static Scanner scanner = new Scanner(System.in);

    public static int leggiInteroInRange(int min, int max) {
        int input = 0;
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print("Inserire un numero tra " + min + " e " + max + ": ");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input < min || input > max)
                    System.out.println("Errore: numero non nel range.");
                else
                    correctInput = true;
            } else {
                System.out.println("Errore: input non e' un numero");
                svuotareScanner();
            }
        }
        svuotareScanner();
        return input;
    }

    public static String letturaControlloStringa(String msg) {
        String input = "";
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print(msg);
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Errore: stringa non valida!");
            } else {
                correctInput = true;
            }
        }
        return input;
    }

    public static char letturaControlloCarattere(String msg) {
        char input = ' ';
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print(msg);
            input = scanner.next().charAt(0); //legge il primo carattere della stringa
            if (input == ' ' || !carattereValido(input)) {
                System.out.println("Errore: carattere non valido!");
            } else {
                correctInput = true;
            }
        }
        svuotareScanner();
        return input;
    }

    private static boolean carattereValido(char c) {
        if (c == Costanti.SIMBOLO_MONETA_1 || c == Costanti.SIMBOLO_MONETA_2 || c == Costanti.SIMBOLO_POZIONE
                || c == Costanti.SIMBOLO_GEMMA || c == Costanti.SIMBOLO_ROCCIA || c == Costanti.SIMBOLO_ALBERO) {
            return false;
        }
        return true;
    }

    public static int menuGiocatore() {
        System.out.println("""
                0. Esci
                1. Mostra griglia
                2. Muovi giocatore
                3. Mostra inventario""");

        return leggiInteroInRange(0, 3);
    }

    public static Direzione menuMovimento() {
        System.out.println("Direzione (0 -> N, 1 -> S, 2 -> W, 3 -> E)");
        int scelta = leggiInteroInRange(0, 3);
        switch (scelta) {
            case 0 -> {
                return Direzione.NORD;
            }
            case 1 -> {
                return Direzione.SUD;
            }
            case 2 -> {
                 return Direzione.OVEST;
            }
            case 3 -> {
                return Direzione.EST;
            }
            default -> {
                return Direzione.NORD;
            }
        }
    }

    public static boolean menuSfida(String msg) {
        System.out.println(msg);
        if (leggiInteroInRange(0, 1) == 1) {
            return true;
        }else return false;
    }


    //stampa per il menu della scelta del personaggio
    private static void menuPersonaggio() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("1. T-Rex\n");
        sb.append("2. Bandito\n");
        sb.append("3. Golem\n");
        sb.append("4. Mago");
        System.out.println(sb);
    }

    //permette di andare a vedere le statistiche di un personaggi tra quelli disponibili
    public static void vediStatistiche() {
        StringBuilder sb = new StringBuilder();
        String scelta = letturaControlloStringa("Vuoi vedere le statistiche di un personaggio? (S/N)");
        while(scelta.equalsIgnoreCase("s")) {
            System.out.print("=".repeat(3)+"PERSONAGGI DISPONIBILI"+ "=".repeat(3));
            menuPersonaggio();
            System.out.println("=".repeat(28));
            int personaggio = leggiInteroInRange(1, Costanti.NUMERO_GIOCATORI);
            sb.append("STATISTICHE:\n");
            switch (personaggio) {
                case 1 -> {
                    System.out.println("Statistiche T-Rex:\n"+"Forza: " + Costanti.TREX_FORZA + "\n" +
                            "Velocita': " + Costanti.TREX_VELOCITA + "\n"+
                            "Magia: " + Costanti.TREX_MAGIA + "\n" + "=".repeat(28));

                }
                case 2 -> {
                    System.out.println("Statistiche Bandito:\n"+"Forza: " + Costanti.BANDITO_FORZA + "\n" +
                            "Velocita': " + Costanti.BANDITO_VELOCITA + "\n"+
                            "Magia: " + Costanti.BANDITO_MAGIA+ "\n" + "=".repeat(28));


                }
                case 3 -> {
                    System.out.println("Statistiche Golem:\n"+"Forza: " + Costanti.GOLEM_FORZA + "\n" +
                            "Velocita': " + Costanti.GOLEM_VELOCITA + "\n"+
                            "Magia: " + Costanti.GOLEM_MAGIA+ "\n" + "=".repeat(28));
                }
                case 4 -> {
                    System.out.println("Statistiche Mago:\n"+"Forza: " + Costanti.MAGO_FORZA + "\n" +
                            "Velocita': " + Costanti.MAGO_VELOCITA + "\n"+
                            "Magia: " + Costanti.MAGO_MAGIA+ "\n" + "=".repeat(28));

                }default -> {
                    System.out.println("scelta non valida!");
                    menuPersonaggio();
                }

            }
            scelta = letturaControlloStringa("Vuoi vedere le statistiche di un personaggio? (S/N)");
        }
    }

    //metodo per la scelta del personaggio e la relativa creazione
    public static Giocatore scegliPersonaggio(String nome, char simbolo) {
        vediStatistiche();
        Giocatore nuovoPersonaggio = null;
        System.out.print("=".repeat(3)+"FAI LA SCELTA DEL TUO PERSONAGGIO" + "=".repeat(3));
        menuPersonaggio();
        int scelta = leggiInteroInRange(1, Costanti.NUMERO_GIOCATORI);
        switch (scelta) {
            case 1 -> nuovoPersonaggio =  new Trex(nome, simbolo);
            case 2 -> nuovoPersonaggio = new Bandito(nome, simbolo);
            case 3 -> nuovoPersonaggio = new Golem(nome, simbolo);
            case 4 -> nuovoPersonaggio = new Mago(nome, simbolo);
        }
        return nuovoPersonaggio;
    }


    //stampa del titolo del gioco
    public static void stampaTitolo() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n".repeat(2));
        sb.append("████████╗██████╗ ███████╗ █████╗ ███████╗██╗   ██╗██████╗ ███████╗     ██████╗██╗      █████╗ ███████╗██╗  ██╗");
        sb.append("\n");
        sb.append("╚══██╔══╝██╔══██╗██╔════╝██╔══██╗██╔════╝██║   ██║██╔══██╗██╔════╝    ██╔════╝██║     ██╔══██╗██╔════╝██║  ██║");
        sb.append("\n");
        sb.append("   ██║   ██████╔╝█████╗  ███████║███████╗██║   ██║██████╔╝█████╗      ██║     ██║     ███████║███████╗███████║");
        sb.append("\n");
        sb.append("   ██║   ██╔══██╗██╔══╝  ██╔══██║╚════██║██║   ██║██╔══██╗██╔══╝      ██║     ██║     ██╔══██║╚════██║██╔══██║");
        sb.append("\n");
        sb.append("   ██║   ██║  ██║███████╗██║  ██║███████║╚██████╔╝██║  ██║███████╗    ╚██████╗███████╗██║  ██║███████║██║  ██║");
        sb.append("\n");
        sb.append("   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝     ╚═════╝╚══════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝");
        sb.append("\n");


        System.out.println(sb);
    }


    public static void chiusuraScanner() {
        scanner.close();
    }

    public static void svuotareScanner() {
        scanner.nextLine();
    }
}
