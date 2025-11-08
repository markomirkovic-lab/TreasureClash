/**
 * L'abilità del golem consiste nell'aumentare la propria velocità di 3 per un turno.
 * Il Golem inoltre può abbattere le rocce.
 */
public class Golem extends Giocatore{

    public Golem(String nome, char simbolo) {
        super(nome, simbolo, Costanti.GOLEM_FORZA, Costanti.GOLEM_VELOCITA, Costanti.GOLEM_MAGIA, true);
    }

    @Override
    public void attivaAbilita() {
        if(getMagia() > 0) {
            usaMagia();
            System.out.println("Il giocatore: " + getNome() + " ha attivato l'abilità del Golem! Velocità aumentata di 3 per il prossimo turno.");
            System.out.println("Magia rimanente: " + getMagia());
        } else {
            System.out.println("Il giocatore: " + getNome() + " non ha più magia per attivare l'abilità.");
        }
    }


    //metodo che mi permette di resettare la velocità
    public void resetVelocita() {
        this.setVelocita(Costanti.GOLEM_VELOCITA);
    }
}
