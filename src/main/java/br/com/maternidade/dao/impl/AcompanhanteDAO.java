package br.com.maternidade.dao.impl;

import br.com.maternidade.dao.IGenericDAO;
import br.com.maternidade.model.enums.EEspecialidadeMedico;
import br.com.maternidade.model.pessoas.Acompanhante;
import br.com.maternidade.model.pessoas.Bebe;
import br.com.maternidade.model.pessoas.Medico;
import br.com.maternidade.model.pessoas.Parturiente;
import br.com.maternidade.util.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AcompanhanteDAO implements IGenericDAO<Acompanhante, Integer> {
    @Override
    public void inserir(Acompanhante obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {

            String sql = "INSERT INTO maternidade.acompanhante " +
                    "(crm, especialidade) " +
                    "VALUES(?, ?);";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, obj.getGrauParentesco());
            pst.setString(2, obj.getPaciente().toString());

            pst.execute();
        }finally {
            c.close();
        }
    }

    @Override
    public void alterar(Acompanhante obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "UPDATE maternidade.acompanhante\n" +
                    "SET grauparentesco=?, paciente=?\n" +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, obj.getGrauParentesco());
            pst.setString(2, obj.getPaciente().toString());

            pst.execute();
        }finally {
            c.close();
        }
    }

    @Override
    public void apagar(Acompanhante obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "DELETE FROM maternidade.acompanhante\n" +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, obj.getId() );

            pst.execute();
        }finally {
            c.close();
        }
    }

    @Override
    public Acompanhante Buscar(Integer key) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT id, grauParentesco, paciente\n" +
                    "FROM maternidade.acompanhante " +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, key);

            ResultSet resultado = pst.executeQuery();
            // 5 - Preparar Objeto

            Acompanhante ac = null;
            if (resultado.next()) {
                ac = new Acompanhante(
                        resultado.getString(1),
                        (Parturiente) resultado.getObject(2)
                );

        }

            return ac;
        }finally {
            c.close();
        }
    }

    @Override
    public ArrayList<Acompanhante> BuscarTodos() throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT id, bebe, acompanhante, medico\n" +
                    "FROM maternidade.parturiente;\n";

            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet resultado = pst.executeQuery();

            return getRegistrodeAcompanhante(resultado);
        }finally {
            c.close();
        }
    }

    private ArrayList<Acompanhante> getRegistrodeAcompanhante(ResultSet resultado) throws SQLException {
        ArrayList<Acompanhante> lista = new ArrayList<>();

        while (resultado.next()){

            Acompanhante ac = new Acompanhante(
                    resultado.getString(1),
                    (Parturiente) resultado.getObject(2)
            );
        }

        return lista;
    }

    @Override
    public int quantidade() throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT count(*) \n" +
                    "FROM maternidade.acompanhante ;\n";

            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet resultado = pst.executeQuery();


            resultado.next();
            return resultado.getInt(1) ;
        }finally {
            c.close();
        }
    }
}
