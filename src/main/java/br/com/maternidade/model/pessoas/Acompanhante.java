package br.com.maternidade.model.pessoas;

import java.time.LocalDateTime;

public class Acompanhante extends Pessoa{

    private String grauParentesco;
    private Parturiente paciente;

    public Acompanhante() {
    }

    public Acompanhante(int id, String nome, String login, String senha, String email, LocalDateTime dataAcesso, String grauParentesco, Parturiente paciente) {
        super(id, nome, login, senha, email, dataAcesso);
        this.grauParentesco = grauParentesco;
        this.paciente = paciente;
    }

    public Acompanhante(String grauParentesco, Parturiente paciente) {
        this.grauParentesco = grauParentesco;
        this.paciente = paciente;
    }

    public String getGrauParentesco() {

        return grauParentesco;
    }

    public void setGrauParentesco(String grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    public Parturiente getPaciente() {
        return paciente;
    }

    public void setPaciente(Parturiente paciente) {
        this.paciente = paciente;
    }
}
