package br.com.maternidade.model.pessoas;

import java.time.LocalDateTime;

public class Parturiente extends Pessoa{
    private Bebe bebe;
    private Acompanhante acompanhante;
    private Medico medico;

    public Parturiente() {
    }

    public Parturiente(int id, String nome, String login, String senha, String email, LocalDateTime dataAcesso, Bebe bebe, Acompanhante acompanhante, Medico medico) {
        super(id, nome, login, senha, email, dataAcesso);
        this.bebe = bebe;
        this.acompanhante = acompanhante;
        this.medico = medico;
    }

    public Parturiente(int id, Bebe bebe, Acompanhante acompanhante, Medico medico) {
        this.bebe = bebe;
        this.acompanhante = acompanhante;
        this.medico = medico;
    }

    public Bebe getBebe() {
        return bebe;
    }

    public void setBebe(Bebe bebe) {
        this.bebe = bebe;
    }

    public Acompanhante getAcompanhante() {
        return acompanhante;
    }

    public void setAcompanhante(Acompanhante acompanhante) {
        this.acompanhante = acompanhante;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
