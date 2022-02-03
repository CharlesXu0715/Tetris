import java.applet.Applet;
import java.net.URL;
import java.applet.AudioClip;

public class Audio extends Thread{ 
    // Nous implémentons l'interface thread qui permet à notre programme de gérer plusieurs choses parallèlement 
    private URL url;
    private AudioClip son;
  
  // Nous renseignons l'adresse du fichier que nous souhaitons traiter
    public Audio(String adresse){
        url = this.getClass().getClassLoader().getResource(adresse);
        son = Applet.newAudioClip(url);
    }
     // Méthode permettant d'arrêter la piste audio
    public void jouer(){ 
        son.play();
    }
     // Méthode permettant de jouer en boucle la piste audio 
    public void jouerBoucle(){
        son.loop();
    }
     // Méthode permettant d'arrêter la piste audio
    public void arreter(){
        son.stop();
    }
}
