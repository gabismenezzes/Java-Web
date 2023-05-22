package br.com.maternidade.model.pessoas;

import br.com.maternidade.model.enums.ETipoParto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Bebe extends Pessoa{

    private Parturiente mae;
    private String nomedoPai;
    private LocalTime horarioNascimento;
    private ETipoParto parto;
    private List<Medico> medicos;
    private Boolean ficounaUTI;

    public Bebe() {
    }

    public Bebe(int id, String nome, String login, String senha, String email, LocalDateTime dataAcesso, Parturiente mae, String nomedoPai, LocalTime horarioNascimento, ETipoParto parto, List<Medico> medicos, Boolean ficounaUTI) {
        super(id, nome, login, senha, email, dataAcesso);
        this.mae = mae;
        this.nomedoPai = nomedoPai;
        this.horarioNascimento = horarioNascimento;
        this.parto = parto;
        this.medicos = medicos;
        this.ficounaUTI = ficounaUTI;
    }

    public Bebe(Parturiente mae, String nomedoPai, LocalTime horarioNascimento, ETipoParto parto, List<Medico> medicos, Boolean ficounaUTI) {
        this.mae = mae;
        this.nomedoPai = nomedoPai;
        this.horarioNascimento = horarioNascimento;
        this.parto = parto;
        this.medicos = medicos;
        this.ficounaUTI = ficounaUTI;
    }

    public Parturiente getMae() {
        return mae;
    }

    public void setMae(Parturiente mae) {
        this.mae = mae;
    }

    public String getNomedoPai() {
        return nomedoPai;
    }

    public void setNomedoPai(String nomedoPai) {
        this.nomedoPai = nomedoPai;
    }

    public LocalTime getHorarioNascimento() {
        return horarioNascimento;
    }

    public void setHorarioNascimento(LocalTime horarioNascimento) {
        this.horarioNascimento = horarioNascimento;
    }

    public ETipoParto getParto() {
        return parto;
    }

    public void setParto(ETipoParto parto) {
        this.parto = parto;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public Boolean getFicounaUTI() {
        return ficounaUTI;
    }

    public void setFicounaUTI(Boolean ficounaUTI) {
        this.ficounaUTI = ficounaUTI;
    }
}
