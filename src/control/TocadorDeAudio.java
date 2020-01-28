package control;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import javazoom.jl.player.Player;

/**
 *
 * @author Will
 */
public class TocadorDeAudio {

    public static String SOM_SUCESSO = "/audios/sucess.mp3";
    public static String SOM_FALHA = "/audios/fail.mp3";

    private File musica;
    private Player player;

    public void tocar(File musica) {
        this.musica = musica;
    }

    public void run() {
        try {
            FileInputStream fis = new FileInputStream(musica);
            BufferedInputStream bis = new BufferedInputStream(fis);
            this.player = new Player(bis);
            System.out.println("Tocando Musica!");

            this.player.play();
            System.out.println("Terminado Musica!");

        } catch (Exception e) {
            System.out.println("Problema ao tocar Musica" + musica);
            e.printStackTrace();
        }
    }

    public void tocarAudio(String url) {
        String path = System.getProperty("user.dir")+url;
        File arquivo = new File(path);
        TocadorDeAudio musica = new TocadorDeAudio();
        musica.tocar(arquivo);
        musica.run();
    }

}
