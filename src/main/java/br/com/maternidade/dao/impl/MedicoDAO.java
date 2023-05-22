package br.com.maternidade.dao.impl;

import br.com.maternidade.dao.IGenericDAO;
import br.com.maternidade.model.enums.EEspecialidadeMedico;
import br.com.maternidade.model.pessoas.Medico;
import br.com.maternidade.util.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicoDAO implements IGenericDAO <Medico, Integer> {
    @Override
    public void inserir(Medico obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {

            String sql = "INSERT INTO maternidade.medico " +
                    "(crm, especialidade) " +
                    "VALUES(?, ?);";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, obj.getCrm());
            pst.setString(2, obj.getEspecialidade().toString());

            pst.execute();
        }finally {
            c.close();
        }
    }

    @Override
    public void alterar(Medico obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "UPDATE maternidade.medico\n" +
                    "SET crm=?, especialidade=?\n" +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, obj.getCrm());
            pst.setString(2, obj.getEspecialidade().toString());

            pst.execute();
        }finally {
            c.close();
        }
    }

    @Override
    public void apagar(Medico obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "DELETE FROM maternidade.medico\n" +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, obj.getId() );

            pst.execute();
        }finally {
            c.close();
        }
    }

    @Override
    public Medico Buscar(Integer key) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT id, crm, especialidade\n" +
                    "FROM maternidade.medico " +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, key);

            ResultSet resultado = pst.executeQuery();
            // 5 - Preparar Objeto

            Medico medico = null;
            if (resultado.next()){
                medico = new Medico(
                        resultado.getString(2),
                        EEspecialidadeMedico.valueOf(resultado.getString(4))
                );
            }

            return medico;
        }finally {
            c.close();
        }
    }

    @Override
    public ArrayList<Medico> BuscarTodos() throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT id, crm, especialidade" +
                    "FROM maternidade.medico";

            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet resultado = pst.executeQuery();
            // 5 - Preparar Objeto

            return getRegistroMedicos(resultado);
        }finally {
            c.close();
        }
    }

    private ArrayList<Medico> getRegistroMedicos(ResultSet resultado) throws SQLException, ClassNotFoundException {
        ArrayList<Medico> lista = new ArrayList<>();

        while (resultado.next()){
            Medico medico = new Medico(
                    resultado.getString(1),
                    EEspecialidadeMedico.valueOf(resultado.getString(2))
            );
            lista.add(medico);
        }

        return lista;
    }

    @Override
    public int quantidade() throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT count(*) \n" +
                    "FROM maternidade.medico ;\n";

            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet resultado = pst.executeQuery();


            resultado.next();
            return resultado.getInt(1) ;
        }finally {
            c.close();
        }
    }
}
