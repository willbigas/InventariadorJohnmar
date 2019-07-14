package control;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import model.Produto;
import model.tablemodel.ProdutoTableModel;
import uteis.UtilTable;
import view.TelaConferencia;

/**
 *
 * @author william.mauro
 */
public class TelaConferenciaControl {

    TocadorDeAudio tocadorDeAudio;
    TelaConferencia telaConferencia;
    ProdutoTableModel produtoTableModel;
    Produto produto;
    List<Produto> produtosDoExcel;
    List<Produto> produtosParaExportar;

    private final Color COR_VERDE = new Color(0, 204, 0);

    private static final int SKU = 0;
    private static final int NOME = 1;
    private static final int EAN13 = 2;
    private static final int DUN14 = 3;
    private static final int QTD_DUN14 = 4;
    private static final int LOCALIZACAO = 5;
    private static final int QTD_ESTOQUE = 6;
    private static final int QTD_CONFERIDO = 7;

    Integer indiceDoProdutoNaLista = null;

    public TelaConferenciaControl(List<Produto> produtosRecebidos) {
        produtoTableModel = new ProdutoTableModel();
        produtosDoExcel = produtosRecebidos;
        produtosParaExportar = new ArrayList<>();
        tocadorDeAudio = new TocadorDeAudio();
    }

    public void chamarTelaConferencia() {
        telaConferencia = new TelaConferencia(this);
        telaConferencia.setLocationRelativeTo(null);
        inicializaTableModelDeProdutos();
        redimensionarTamanhosDaTabela();
        centralizarConteudosDaTabela();
        UtilTable.mudarCorDeFonteColuna(QTD_CONFERIDO, COR_VERDE, telaConferencia.getTblProdutos());
        telaConferencia.setVisible(true);

    }

    private void redimensionarTamanhosDaTabela() {
        UtilTable.centralizarCabecalho(telaConferencia.getTblProdutos());
        UtilTable.redimensionar(telaConferencia.getTblProdutos(), SKU, 90);
        UtilTable.redimensionar(telaConferencia.getTblProdutos(), NOME, 370);
        UtilTable.redimensionar(telaConferencia.getTblProdutos(), EAN13, 140);
        UtilTable.redimensionar(telaConferencia.getTblProdutos(), DUN14, 150);
        UtilTable.redimensionar(telaConferencia.getTblProdutos(), QTD_DUN14, 80);
        UtilTable.redimensionar(telaConferencia.getTblProdutos(), LOCALIZACAO, 100);
        UtilTable.redimensionar(telaConferencia.getTblProdutos(), QTD_ESTOQUE, 80);
        UtilTable.redimensionar(telaConferencia.getTblProdutos(), QTD_CONFERIDO, 80);
    }

    public void centralizarConteudosDaTabela() {
        UtilTable.centralizarConteudo(telaConferencia.getTblProdutos(), EAN13);
        UtilTable.centralizarConteudo(telaConferencia.getTblProdutos(), DUN14);
        UtilTable.centralizarConteudo(telaConferencia.getTblProdutos(), QTD_DUN14);
        UtilTable.centralizarConteudo(telaConferencia.getTblProdutos(), LOCALIZACAO);
        UtilTable.centralizarConteudo(telaConferencia.getTblProdutos(), QTD_ESTOQUE);
        UtilTable.centralizarConteudo(telaConferencia.getTblProdutos(), QTD_CONFERIDO);

    }

    public void inicializaTableModelDeProdutos() {
        telaConferencia.getTblProdutos().setModel(produtoTableModel);
        produtoTableModel.adicionar(produtosDoExcel);
    }

    public void contarUmNovoItemAction() {
        if (telaConferencia.getTfQuantidade().getText().length() > 6) {
            JOptionPane.showMessageDialog(telaConferencia, "Quantidade superior ao limite de 6 Digitos [000000]");
            return;
        }

        Integer quantidade = null;

        try {

            if (telaConferencia.getTfQuantidade().getText().isEmpty()) {
                quantidade = 1;
            } else {
                quantidade = Integer.valueOf(telaConferencia.getTfQuantidade().getText());
            }

        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(telaConferencia, "O campo quantidade não é um numero valido, Verifique!");
            quantidade = null;
            return;
        }

        produto = processarUmaConferencia(quantidade);

        if (produto == null) {
            JOptionPane.showMessageDialog(telaConferencia, "Código não encontrado!");
            tocadorDeAudio.tocarAudio(TocadorDeAudio.SOM_FALHA);
            return;
        } else {
            limparCampos();
            tocadorDeAudio.tocarAudio(TocadorDeAudio.SOM_SUCESSO);
        }

    }

    public Produto processarUmaConferencia(Integer quantidade) {
        String codigo = telaConferencia.getTfCodigo().getText();
        Produto produtoBuscado = null;

        for (int i = 0; i < produtosDoExcel.size(); i++) {
            Produto umProduto = produtosDoExcel.get(i);

            if (codigo.equalsIgnoreCase(umProduto.getEan13())) {
                produtoBuscado = umProduto;
                System.out.println("Produto encontrado pelo EAN13:" + produtoBuscado);
                indiceDoProdutoNaLista = i;
                if (produtoBuscado.getQtdConferida() == null) {
                    produtoBuscado.setQtdConferida(0);
                }
                produtoBuscado.setQtdConferida(produtoBuscado.getQtdConferida() + (quantidade * 1));
                produtoTableModel.atualizar(i, produtoBuscado);
            }
            if (codigo.equalsIgnoreCase(umProduto.getDun14())) {
                produtoBuscado = umProduto;
                System.out.println("Produto encontrado pelo DUN14:" + produtoBuscado);
                if (produtoBuscado.getQtdConferida() == null) {
                    produtoBuscado.setQtdConferida(0);
                }
                produtoBuscado.setQtdConferida(produtoBuscado.getQtdConferida() + (quantidade * produtoBuscado.getQtdDun14()));
                produtoTableModel.atualizar(i, produtoBuscado);
                produtosParaExportar.add(produtoBuscado);
            }
        }
        return produtoBuscado;

    }

    public void exportarListaParaExcelAction() {
        TelaEscolherArquivoControl.acionaSalvamentoDeArquivo(telaConferencia);
        TelaEscolherArquivoControl.escreverArquivoParaExcel(produtoTableModel.retornaLista());
    }

    public void limparCampos() {
        telaConferencia.getTfCodigo().setText("");
        telaConferencia.getTfQuantidade().setText("");
        telaConferencia.getTfCodigo().requestFocus();

    }
}
