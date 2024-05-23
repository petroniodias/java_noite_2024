import java.util.Date;

public class Servico {
    private int id;
    private Veiculo veiculo;
    private String descricao;
    private double valor;
    private Date data;

    public Servico(int id, Veiculo veiculo, String descricao, double valor, Date data) {
        this.id = id;
        this.veiculo = veiculo;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public Servico(Veiculo veiculo, String descricao, double valor, Date data) {
        this.veiculo = veiculo;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Servico [id=" + id + ", veiculo=" + veiculo + ", descricao=" + descricao + ", valor=" + valor + ", data=" + data + "]";
    }
}
