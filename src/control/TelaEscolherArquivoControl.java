package control;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Produto;
import uteis.Arquivo;
import view.TelaEscolherArquivo;

/**
 *
 * @author William
 */
public class TelaEscolherArquivoControl {

    TelaEscolherArquivo telaEscolherArquivo;
    TelaConferenciaControl telaConferenciaControl;
    List<Produto> produtos = new ArrayList<>();
    Produto produto;

    public static String enderecoOrigem = "";
    public static String enderecoDestino = "";

    private static final int SKU = 0;
    private static final int NOME = 1;
    private static final int EAN13 = 2;
    private static final int DUN14 = 3;
    private static final int QTD_DUN14 = 4;
    private static final int LOCALIZACAO = 5;
    private static final int QTD_ESTOQUE = 6;

    public TelaEscolherArquivoControl() {
        telaEscolherArquivo = new TelaEscolherArquivo(this);
        telaEscolherArquivo.setLocationRelativeTo(null);
        telaEscolherArquivo.setVisible(true);
    }

    public void lerArquivoAction() {
        String campos[] = null;
        List<String> arquivo = new ArrayList<>();

        try {
            arquivo = Arquivo.lerArqArrayList(enderecoOrigem);
        } catch (Exception exception) {
        }
        for (String linha : arquivo) {
            campos = linha.split(";");
            produto = new Produto();

            if (campos.length == 6) {
                for (int i = 0; i < campos.length; i++) {
                    String umCampo = campos[i];
                    produto.setId(1);
                    produto.setSku(campos[SKU]);
                    produto.setNome(campos[NOME]);
                    produto.setEan13(campos[EAN13]);
                    produto.setDun14(campos[DUN14]);
                    produto.setLocalizacao(campos[LOCALIZACAO]);
                    produto.setQtdDun14(Integer.valueOf(campos[QTD_DUN14]));
                    produto.setQtdEstoque(0);
                    System.out.println("Um Produto de uma Linha :" + produto);
                }
                produtos.add(produto);

            } else {

                for (int i = 0; i < campos.length; i++) {
                    String umCampo = campos[i];
                    produto.setId(1);
                    produto.setSku(campos[SKU]);
                    produto.setNome(campos[NOME]);
                    produto.setEan13(campos[EAN13]);
                    produto.setDun14(campos[DUN14]);
                    produto.setLocalizacao(campos[LOCALIZACAO]);
                    produto.setQtdDun14(Integer.valueOf(campos[QTD_DUN14]));
                    String campoQuePodeSerVazio = campos[QTD_ESTOQUE];
                        produto.setQtdEstoque(Integer.valueOf(campos[QTD_ESTOQUE]));
                    System.out.println("Um Produto de uma Linha :" + produto);
                }
                produtos.add(produto);

            }

        }

        for (Produto produto : produtos) {
            System.out.println(produto);

        }
        
        if (produtos != null) {
            JOptionPane.showMessageDialog(telaEscolherArquivo, "Arquivo lido com sucesso");
            
        }
        

//        try {
//            for (String novasLinha : novasLinhas) {
//                Arquivo.escreverArqConcat(enderecoDestino + ".csv", novasLinha);
//            }
//            JOptionPane.showMessageDialog(null, "Conversão executada com sucesso!");
//
//        } catch (Exception exception) {
//            JOptionPane.showMessageDialog(null, "Erro na converão , chame o desenvolvedor!");
//        }
    }

    public void acionaAberturaDeArquivo() {
        /**
         * Janela de Abrir - JFileChooser - Para abrir arquivos em geral.
         */

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Arquivo Text - Documento de texto em .txt", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(telaEscolherArquivo);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String nomeArquivo = chooser.getSelectedFile().getName();
            enderecoOrigem = chooser.getSelectedFile().getPath();
            System.out.println("Endereco de Arquivo Origem : " + enderecoOrigem);
        }
    }

    public void acionaSalvamentoDeArquivo() {
        /**
         * Janela de Salvar - JFileChooser - Para salvar arquivos em geral.
         */

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Arquivo Text - Documento de texto em .txt", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(telaEscolherArquivo);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String nomeArquivo = chooser.getSelectedFile().getName();
            enderecoDestino = chooser.getSelectedFile().getPath();
            System.out.println("EnderecoArquivo de Destino :" + enderecoDestino);
        }

    }
    
    public void chamarTelaConferenciaAction() {
        telaConferenciaControl = new TelaConferenciaControl(produtos);
        telaConferenciaControl.chamarTelaConferencia();
        
    }
}
