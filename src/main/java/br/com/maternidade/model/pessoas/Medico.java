package br.com.maternidade.model.pessoas;

import br.com.maternidade.model.enums.EEspecialidadeMedico;

import java.time.LocalDateTime;

public class Medico extends Pessoa{
    private String crm;
    private EEspecialidadeMedico especialidade;

    public Medico() {
    }

    public Medico(int id, String nome, String login, String senha, String email, LocalDateTime dataAcesso, String crm, EEspecialidadeMedico especialidade) {
        super(id, nome, login, senha, email, dataAcesso);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico(String crm, EEspecialidadeMedico especialidade) {
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public EEspecialidadeMedico getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EEspecialidadeMedico especialidade) {
        this.especialidade = especialidade;
    }
}
