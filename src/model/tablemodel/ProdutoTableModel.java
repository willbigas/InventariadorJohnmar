package model.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Produto;

/**
 *
 * @author william.mauro
 */
public class ProdutoTableModel extends AbstractTableModel {

    private static final int SKU = 0;
    private static final int NOME = 1;
    private static final int EAN13 = 2;
    private static final int DUN14 = 3;
    private static final int QTD_DUN14 = 4;
    private static final int LOCALIZACAO = 5;
    private static final int QTD_ESTOQUE = 6;
    private static final int QTD_CONFERIDA = 7;

    private List<Produto> linhas;
    private String[] COLUNAS = {"SKU", "NOME", "EAN13", "DUN14", "QTD_CX", "LOCALIZACAO" , "ESTOQUE_ATUAL" , "CONFERIDO"};

    public ProdutoTableModel() {
        linhas = new ArrayList<>();
    }

    public ProdutoTableModel(List<Produto> listProdutos) {
        linhas = new ArrayList<>(listProdutos);
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return COLUNAS.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUNAS[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case SKU:
                return String.class;
            case NOME:
                return String.class;
            case EAN13:
                return String.class;
            case DUN14:
                return String.class;
            case QTD_DUN14:
                return Integer.class;
            case LOCALIZACAO:
                return String.class;
            case QTD_ESTOQUE:
                return Integer.class;
            case QTD_CONFERIDA:
                return Integer.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Produto produto = linhas.get(linha);
        switch (coluna) {
            case SKU:
                return produto.getSku();
            case NOME:
                return produto.getNome();
            case EAN13:
                return produto.getEan13();
            case DUN14:
                return produto.getDun14();
            case QTD_DUN14:
                return produto.getQtdDun14();
            case LOCALIZACAO:
                return produto.getLocalizacao();
            case QTD_ESTOQUE:
                return produto.getQtdEstoque();
            case QTD_CONFERIDA:
                return produto.getQtdConferida();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        Produto produto = linhas.get(linha);
        switch (coluna) {
            case SKU:
                produto.setSku((String) valor);
                break;
            case NOME:
                produto.setNome((String) valor);
                break;
            case EAN13:
                produto.setEan13((String) valor);
                break;
            case DUN14:
                produto.setDun14((String) valor);
                break;
            case QTD_DUN14:
                produto.setQtdDun14((Integer) valor);
                break;
            case LOCALIZACAO:
                produto.setLocalizacao((String) valor);
                break;
            case QTD_ESTOQUE:
                produto.setQtdEstoque((Integer) valor);
                break;
            case QTD_CONFERIDA:
                produto.setQtdConferida((Integer) valor);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    public Produto pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void adicionar(Produto produto) {
        linhas.add(produto);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    public void adicionar(List<Produto> produtos) {
        int indice = getRowCount();
        linhas.addAll(produtos);
        fireTableRowsInserted(indice, indice + produtos.size());
        fireTableDataChanged();
    }

    public void remover(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha); // atualiza delete
    }

    public void remover(int linhaInicio, int linhaFim) {
        for (int i = linhaInicio; i <= linhaFim; i++) {
            linhas.remove(i);
            fireTableRowsDeleted(linhaInicio, linhaFim); // atualiza delete
        }

    }

    public void atualizar(int indiceLinha, Produto produto) {
        linhas.set(indiceLinha, produto);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
