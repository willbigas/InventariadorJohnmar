package control;

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

    public TelaConferenciaControl(List<Produto> produtosRecebidos) {
        produtoTableModel = new ProdutoTableModel();
        produtosDoExcel = produtosRecebidos;

    }

    public void chamarTelaConferencia() {
        telaConferencia = new TelaConferencia(this);
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
        produto = identificaItem();

        System.out.println(produto);
    }

    public Produto identificaItem() {
        String codigo = telaConferencia.getTfCodigo().getText();
        for (Produto umProduto : produtosDoExcel) {
            if (codigo.equals(umProduto.getEan13())) {
                return umProduto;
            }
            if (codigo.equals(umProduto.getDun14())) {
                return umProduto;
            }
            if (!codigo.equals(umProduto.getEan13()) && !codigo.equals(umProduto.getDun14())) {
                return null;
            }
        }
        return null;

    }

}
