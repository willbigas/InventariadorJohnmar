package control;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Produto;
import model.tablemodel.ProdutoTableModel;
import view.TelaConferencia;

/**
 *
 * @author william.mauro
 */
public class TelaConferenciaControl {

    TelaConferencia telaConferencia;
    ProdutoTableModel produtoTableModel;
    Produto produto;
    List<Produto> produtosDoExcel;
    List<Produto> produtosParaExportar;

    Integer indiceDoProdutoNaLista = null;

    public TelaConferenciaControl(List<Produto> produtosRecebidos) {
        produtoTableModel = new ProdutoTableModel();
        produtosDoExcel = produtosRecebidos;
        produtosParaExportar = new ArrayList<>();
    }

    public void chamarTelaConferencia() {
        telaConferencia = new TelaConferencia(this);
        telaConferencia.setLocationRelativeTo(null);
        inicializaTableModelDeProdutos();
        telaConferencia.setVisible(true);
    }

    public void inicializaTableModelDeProdutos() {
        telaConferencia.getTblProdutos().setModel(produtoTableModel);
        produtoTableModel.adicionar(produtosDoExcel);
    }

    public void contarUmNovoItemAction() {
        if (telaConferencia.getTfQuantidade().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por Favor, coloque uma quantidade!");
            return;
        }
        Integer quantidade = Integer.valueOf(telaConferencia.getTfQuantidade().getText());
        produto = processaQuantidades(quantidade);

    }

    public Produto processaQuantidades(Integer quantidade) {
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
                continue;
            }
            if (codigo.equalsIgnoreCase(umProduto.getDun14())) {
                produtoBuscado = umProduto;
                System.out.println("Produto encontrado pelo DUN14:" + produtoBuscado);
                if (produtoBuscado.getQtdConferida() == null) {
                    produtoBuscado.setQtdConferida(0);
                }
                produtoBuscado.setQtdConferida(produtoBuscado.getQtdConferida() + (quantidade * produtoBuscado.getQtdDun14()));
                produtoTableModel.atualizar(i, produto);
                produtosParaExportar.add(produto);
            }
        }
        return produtoBuscado;

    }

    public void exportarListaParaExcelAction() {
        TelaEscolherArquivoControl.acionaSalvamentoDeArquivo(telaConferencia);
        TelaEscolherArquivoControl.escreverArquivoParaExcel(produtosParaExportar);
    }

}
