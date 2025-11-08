/**
 * L'abilità del mago gli permette di teletrasportarsi in un punto casuale della griglia di gioco.
 */
public class Mago extends Giocatore{
    public Mago(String nome, char simbolo) {
        super(nome, simbolo, Costanti.MAGO_FORZA, Costanti.MAGO_VELOCITA, Costanti.MAGO_MAGIA, false);
    }

    @Override
    public void attivaAbilita() {
        if(getMagia() > 0) {
            usaMagia();
            System.out.println("il giocatore: " + getNome() + " ha attivato l'abilità del Mago, teletrasporto!");
            System.out.println("Magia rimanente: " + getMagia());
        } else {
            System.out.println("il giocatore: " + getNome() + "non ha più magia per usare l'abilità");
        }
    }
}
