package model;

import java.time.LocalDate;

public class Pagamento {
    
    private int id;
    private int matriculaId;
    private double valor;
    private String formaPagamento;
    private String status;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;

    public Pagamento(){

    }

    public Pagamento(int id, int matriculaId, double valor, String formaPagamento,
        String status, LocalDate dataVencimmento, LocalDate dataPagmento){
            this.id = id;
            this.matriculaId = matriculaId;
            this.valor = valor;
            this.formaPagamento = formaPagamento;
            this.status = status;
            this.dataVencimento = dataVencimmento;
            this.dataPagamento = dataPagmento;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getMatriculaId(){
        return matriculaId;
    }

    public void setMatriculaId(int matriculaId){
        this.matriculaId = matriculaId;
    }

    public double getValor(){
        return valor;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public String getFormaPagamento(){
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento){
        this.formaPagamento = formaPagamento;
    }

}
