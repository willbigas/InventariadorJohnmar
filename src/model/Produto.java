package model;

/**
 *
 * @author william.mauro
 */
public class Produto {

    private Integer id;
    private String sku;
    private String nome;
    private String ean13;
    private String dun14;
    private Integer qtdDun14;
    private String localizacao;
    private Integer qtdEstoque;
    private Integer qtdConferida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEan13() {
        return ean13;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }

    public String getDun14() {
        return dun14;
    }

    public void setDun14(String dun14) {
        this.dun14 = dun14;
    }

    public Integer getQtdDun14() {
        return qtdDun14;
    }

    public void setQtdDun14(Integer qtdDun14) {
        this.qtdDun14 = qtdDun14;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Integer getQtdConferida() {
        return qtdConferida;
    }

    public void setQtdConferida(Integer qtdConferida) {
        this.qtdConferida = qtdConferida;
    }
    
    
    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", sku=" + sku + ", nome=" + nome + ", ean13=" + ean13 + ", dun14=" + dun14 + ", qtdDun14=" + qtdDun14 + ", localizacao=" + localizacao + ", qtdEstoque=" + qtdEstoque + '}';
    }
    

    
}
