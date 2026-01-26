package model;

public class Plano {

    private int id;
    private String nome;
    private double valor;
    private int duracaoMeses;
    private String descricao;

    public Plano() {

    }

    public Plano(int id, String nome, double valor, int duracaoMeses, String descricao) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.duracaoMeses = duracaoMeses;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Plano{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", duracaoMeses=" + duracaoMeses +
                '}';
    }
}
