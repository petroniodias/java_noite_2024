public class Veiculo {
    private int id;
    private String modelo;
    private String placa;
    private Cliente proprietario;

    public Veiculo(int id, String modelo, String placa, Cliente proprietario) {
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.proprietario = proprietario;
    }

    public Veiculo(String modelo, String placa, Cliente proprietario) {
        this.modelo = modelo;
        this.placa = placa;
        this.proprietario = proprietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Cliente getProprietario() {
        return proprietario;
    }

    public void setProprietario(Cliente proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public String toString() {
        return "Veiculo [id=" + id + ", modelo=" + modelo + ", placa=" + placa + ", proprietario=" + proprietario + "]";
    }
}
