package inventariadorjohnmar;

import control.TocadorDeAudio;

/**
 *
 * @author William
 */
public class InventariadorJohnmar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TocadorDeAudio tocadorDeAudio = new TocadorDeAudio();
        tocadorDeAudio.tocarAudio(TocadorDeAudio.SOM_SUCESSO);

        // STRING COM O CAMINHO DO ARQUIVO MP3 A SER TOCADO
//        try {
//            InterfaceJanela.MudaSwingParaPadraoDoSO();
//        } catch (Exception exception) {
//            JOptionPane.showMessageDialog(null, "Não consegui alterar o componente do swing para seu sistema operacional , chame o programador!");
//        }
//        TelaEscolherArquivoControl controlArquivo = new TelaEscolherArquivoControl();
    }
}
