/**
 * L'abilità del bandito gli permette di rubare una moneta ad un giocatore a scelta che ne possiede almeno una.
 */
public class Bandito extends Giocatore{
    public Bandito(String nome, char simbolo) {
        super(nome, simbolo, Costanti.BANDITO_FORZA, Costanti.BANDITO_VELOCITA, Costanti.BANDITO_MAGIA, false);
    }

    @Override
    public void attivaAbilita() {
        if(getMagia() > 0) {
            usaMagia(); //va ad usare la magia e a ridurre il numero di volte che può usarla
            System.out.println("il giocatore: " + getNome() + " ha attivato l'abilità del Bandito, ruba una moneta!");
            System.out.println("Magia rimanente: " + getMagia());
        } else {
            System.out.println("il giocatore: " + getNome() + "non ha più magia per usare l'abilità");
        }
    }

    public boolean deruba(Giocatore giocatore) {
        if (giocatore.getPatrimonio() > 0) {
            rubaMoneta(giocatore);
            System.out.println("Hai rubato una moneta a " + giocatore.getNome());
            return true;
        }
        System.out.println("Il giocatore scelto non ha nessuna moneta.");
        return false;
    }
}