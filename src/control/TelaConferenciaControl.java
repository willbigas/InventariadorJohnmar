package control;

import java.util.List;
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
    List<Produto> produtos;

    public TelaConferenciaControl(List<Produto> produtosRecebidos) {
        produtoTableModel = new ProdutoTableModel();
        produtos = produtosRecebidos;

    }
    
    public void chamarTelaConferencia() {
        telaConferencia = new TelaConferencia(this);
        inicializaTableModelDeProdutos();
        telaConferencia.setVisible(true);
    }
    
    public void inicializaTableModelDeProdutos() {
        telaConferencia.getTblProdutos().setModel(produtoTableModel);
        produtoTableModel.adicionar(produtos);
    }

}
