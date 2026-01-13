package model;

import java.time.LocalDate;

public class Matricula {
    
    private int id;
    private int alunoId;
    private int planoId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status;
    private double valorContratado;

    public Matricula () {
    
    }

    public Matricula(int id, int alunoId, int planoId, LocalDate dataInicio, LocalDate dataFim, String status, double valorContratado){
        this.id = id;
        this.alunoId = alunoId;
        this.planoId = planoId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.valorContratado = valorContratado;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getAlunoId(){
        return alunoId;
    }

    public void setAlunoId(int alunoId){
        this.alunoId = alunoId;
    }

    public int getPlanoId(){
        return planoId;
    }

    public void setPlanoId(int planoId){
        this.planoId = planoId;
    }

    public LocalDate getDataInicio(){
        return dataInicio;
    }    

    public void setDataInicio(LocalDate dataInicio){
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim(){
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim){
        this.dataFim = dataFim;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public double getValorContratado(){
        return valorContratado;
    }

    public void setValorContratado(double valorContratado){
        this.valorContratado = valorContratado;
    }

    
}
