package br.com.maternidade.dao.impl;

import br.com.maternidade.dao.IGenericDAO;
import br.com.maternidade.model.enums.EEspecialidadeMedico;
import br.com.maternidade.model.enums.ETipoParto;
import br.com.maternidade.model.pessoas.Bebe;
import br.com.maternidade.model.pessoas.Medico;
import br.com.maternidade.model.pessoas.Parturiente;
import br.com.maternidade.util.connection.ConnectionFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BebeDAO implements IGenericDAO<Bebe, Integer> {

    @Override
    public void inserir(Bebe obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {

            String sql = "INSERT INTO maternidade.parturiente " +
                    "(mae, nomedopai, horarioNascimento, parto, medicos, ficounauti) " +
                    "VALUES(?, ?, ?,?,?,?);";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, obj.getMae().toString());
            pst.setString(2, obj.getNomedoPai());
            pst.setString(3, obj.getHorarioNascimento().toString());
            pst.setString(4, obj.getParto().toString());
            pst.setString(5, obj.getMedicos().toString());
            pst.setBoolean(6,obj.getFicounaUTI());

            pst.execute();
        } finally {
            c.close();
        }
    }

    @Override
    public void alterar(Bebe obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "UPDATE maternidade.bebe\n" +
                    "SET mae=?, nomedopai=?, horarioNascimento=?, parto=?, medicos=?, ficounauti=?\n" +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, obj.getMae().toString());
            pst.setString(2, obj.getNomedoPai());
            pst.setString(3, obj.getHorarioNascimento().toString());
            pst.setString(4, obj.getParto().toString());
            pst.setString(5, obj.getMedicos().toString());
            pst.setBoolean(6,obj.getFicounaUTI());

            pst.execute();
        }finally {
            c.close();
        }
    }

    @Override
    public void apagar(Bebe obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "DELETE FROM maternidade.bebe\n" +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, obj.getId() );

            pst.execute();
        }finally {
            c.close();
        }
    }

    @Override
    public Bebe Buscar(Integer key) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT id, mae, nomedopai, horarioNascimento, parto, medicos, ficounauti \n" +
                    "FROM maternidade.bebe " +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, key);

            ResultSet resultado = pst.executeQuery();

            Bebe b = null; // Initialize with a default value

            if (resultado.next()) {
                Parturiente mae = new Parturiente();
                String nomedoPai = resultado.getString(1);
                String horarioNascimentoString = resultado.getString(2);
                LocalTime horarioNascimento = LocalTime.parse(horarioNascimentoString);
                ETipoParto parto = ETipoParto.valueOf(resultado.getString(3));
                List<Medico> medicos = new ArrayList<>();
                boolean ficounaUTI = resultado.getBoolean(4);

                b = new Bebe(mae, nomedoPai, horarioNascimento, parto, medicos, ficounaUTI);
            }

            return b;
        } finally {
            c.close();
        }

    }

    @Override
    public ArrayList<Bebe> BuscarTodos() throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT id, mae, nomedopai, horarioNascimento, parto, medicos, ficounauti \n" +
            "FROM maternidade.bebe";

            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet resultado = pst.executeQuery();


            return getRegistroBebes(resultado);
        }finally {
            c.close();
        }
    }

    private ArrayList<Bebe> getRegistroBebes(ResultSet resultado) throws SQLException {
        ArrayList<Bebe> lista = new ArrayList<>();

        while (resultado.next()){
            Parturiente mae = new Parturiente();
            String nomedoPai = resultado.getString(1);
            String horarioNascimentoString = resultado.getString(2);
            LocalTime horarioNascimento = LocalTime.parse(horarioNascimentoString);
            ETipoParto parto = ETipoParto.valueOf(resultado.getString(3));
            List<Medico> medicos = new ArrayList<>();
            boolean ficounaUTI = resultado.getBoolean(4);

            Bebe b = new Bebe(mae, nomedoPai, horarioNascimento, parto, medicos, ficounaUTI);

            lista.add(b);
        }

        return lista;
    }

    @Override
    public int quantidade() throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT count(*) \n" +
                    "FROM maternidade.bebe ;\n";

            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet resultado = pst.executeQuery();


            resultado.next();
            return resultado.getInt(1) ;
        }finally {
            c.close();
        }
    }
}
