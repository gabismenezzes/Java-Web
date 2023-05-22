package br.com.maternidade.dao.impl;

import br.com.maternidade.dao.IGenericDAO;
import br.com.maternidade.model.pessoas.*;
import br.com.maternidade.util.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParturienteDAO implements IGenericDAO<Parturiente, Integer> {
    @Override
    public void inserir(Parturiente obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {

            String sql = "INSERT INTO maternidade.parturiente " +
                    "(bebe, parturiente, medico) " +
                    "VALUES(?, ?, ?);";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, obj.getBebe().toString());
            pst.setString(2, obj.getMedico().toString());
            pst.setString(3, obj.getAcompanhante().toString());

            pst.execute();
        } finally {
            c.close();
        }
    }

    @Override
    public void alterar(Parturiente obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "UPDATE maternidade.parturiente\n" +
                    "SET bebe, acompanhante, medico\n" +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, obj.getBebe().toString());
            pst.setString(2, obj.getAcompanhante().toString());
            pst.setString(3, obj.getMedico().toString());

            pst.execute();
        }finally {
            c.close();
        }
    }

    @Override
    public void apagar(Parturiente obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "DELETE FROM maternidade.parturiente\n" +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, obj.getId() );

            pst.execute();
        }finally {
            c.close();
        }
    }

    @Override
    public Parturiente Buscar(Integer key) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT id, prefixo, numero_assentos, tipo_aviao\n" +
                    "FROM maternidade.pessoa " +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, key);

            ResultSet resultado = pst.executeQuery();

            Parturiente p = null;
            if (resultado.next()) {
                int id = resultado.getInt(1);
                Bebe bebe = (Bebe) resultado.getObject(2);
                Acompanhante acompanhante = (Acompanhante) resultado.getObject(3);
                Medico medico = (Medico) resultado.getObject(4);

                p = new Parturiente(id, bebe, acompanhante, medico);


            }
                return p;
        }finally {
            c.close();
        }
    }

    @Override
    public ArrayList<Parturiente> BuscarTodos() throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT id, bebe, acompanhante, medico\n" +
                    "FROM maternidade.parturiente;\n";

            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet resultado = pst.executeQuery();

            return getRegistrodeParturientes(resultado);
        }finally {
            c.close();
        }
    }

    private ArrayList<Parturiente> getRegistrodeParturientes(ResultSet resultado) throws SQLException {
        ArrayList<Parturiente> lista = new ArrayList<>();

        while (resultado.next()){

            int id = resultado.getInt(1);
            Bebe bebe = (Bebe) resultado.getObject(2);
            Acompanhante acompanhante = (Acompanhante) resultado.getObject(3);
            Medico medico = (Medico) resultado.getObject(4);

            Parturiente p = new Parturiente(id, bebe, acompanhante, medico);
        }

        return lista;
    }

    @Override
    public int quantidade() throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT count(*) \n" +
                    "FROM maternidade.parturiente ;\n";

            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet resultado = pst.executeQuery();


            resultado.next();
            return resultado.getInt(1) ;
        }finally {
            c.close();
        }
    }
}
