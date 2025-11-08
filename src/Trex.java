/**
 * L'abilità del T-Rex gli permette di non cedere nessuna moneta quando perde una sfida.
 * Il T-Tex inoltre può abbattere le rocce
 */
public class Trex extends Giocatore{


    public Trex(String nome, char simbolo) {
        super(nome, simbolo, Costanti.TREX_FORZA, Costanti.TREX_VELOCITA, Costanti.TREX_MAGIA, true);
    }

    //nel momento di pagare può decidere se pagare oppure no
    @Override
    public void attivaAbilita() {
        if(getMagia() > 0) {
            usaMagia(); //va ad usare la magia e a ridurre il numero di volte che può usarla
            System.out.println("il giocatore: " + getNome() + " ha attivato l'abilità del Trex, braccino corto! Non paga");
            System.out.println("Magia rimanente: " + getMagia());
        } else {
            System.out.println("il giocatore: " + getNome() + "non ha più magia per usare l'abilità");
        }
    }
}
