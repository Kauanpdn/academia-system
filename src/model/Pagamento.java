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
        String status, LocalDate dataVencimento, LocalDate dataPagamento){
            this.id = id;
            this.matriculaId = matriculaId;
            this.valor = valor;
            this.formaPagamento = formaPagamento;
            this.status = status;
            this.dataVencimento = dataVencimento;
            this.dataPagamento = dataPagamento;
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

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public LocalDate getDataVencimento(){
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento){
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento(){
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento){
        this.dataPagamento = dataPagamento;
    }
}
