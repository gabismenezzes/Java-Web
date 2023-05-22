package br.com.maternidade.dao.impl;

import br.com.maternidade.dao.IGenericDAO;
import br.com.maternidade.model.pessoas.Pessoa;
import br.com.maternidade.util.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PessoaDAO implements IGenericDAO <Pessoa, Integer> {

    @Override
    public void inserir(Pessoa obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {

            String sql = "INSERT INTO maternidade.pessoa " +
                    "(nome, login, senha, email, dataAcesso) " +
                    "VALUES(?, ?, ?, ?);";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getLogin());
            pst.setString(3, obj.getSenha());
            pst.setString(4, obj.getEmail());
            LocalDateTime dataAcesso = obj.getDataAcesso();
            java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dataAcesso);
            pst.setTimestamp(5, timestamp);

            pst.execute();
        } finally {
            c.close();
        }
    }

    @Override
    public void alterar(Pessoa obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "UPDATE maternidade.pessoa\n" +
                    "SET nome=?, login=?, senha=?, email=?, dataAcesso=?\n" +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getLogin());
            pst.setString(3, obj.getSenha());
            pst.setString(4, obj.getEmail());
            LocalDateTime dataAcesso = obj.getDataAcesso();
            java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dataAcesso);
            pst.setTimestamp(5, timestamp);

            pst.execute();
        }finally {
            c.close();
        }
    }

    @Override
    public void apagar(Pessoa obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "DELETE FROM maternidade.pessoa\n" +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, obj.getId() );

            pst.execute();
        }finally {
            c.close();
        }
    }

    @Override
    public Pessoa Buscar(Integer key) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT id, prefixo, numero_assentos, tipo_aviao\n" +
                    "FROM maternidade.pessoa " +
                    "WHERE id=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, key);

            ResultSet resultado = pst.executeQuery();
            // 5 - Preparar Objeto

            Pessoa p = null;
            if (resultado.next()){
                LocalDateTime dataAcesso = resultado.getTimestamp(6).toLocalDateTime();
                p = new Pessoa( resultado.getInt(1),
                        resultado.getString(2),
                        resultado.getString(3),
                        resultado.getString(4),
                        resultado.getString(5),
                        dataAcesso
                        );
            }

            return p;
        }finally {
            c.close();
        }
    }

    @Override
    public ArrayList<Pessoa> BuscarTodos() throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT id, nome, login, senha, email, dataAcesso\n" +
                    "FROM maternidade.pessoa;\n";

            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet resultado = pst.executeQuery();
            // 5 - Preparar Objeto

            return getRegistrodePessoas(resultado);
        }finally {
            c.close();
        }
    }

    private ArrayList<Pessoa> getRegistrodePessoas(ResultSet resultado) throws SQLException{
        ArrayList<Pessoa> lista = new ArrayList<>();

        while (resultado.next()){
            LocalDateTime dataAcesso = resultado.getTimestamp(6).toLocalDateTime();
            Pessoa p = new Pessoa( resultado.getInt(1),
                    resultado.getString(2),
                    resultado.getString(3),
                    resultado.getString(4),
                    resultado.getString(5),
                    dataAcesso
            );
            lista.add(p);
        }

        return lista;
    }

    @Override
    public int quantidade() throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT count(*) \n" +
                    "FROM maternidade.pessoa ;\n";

            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet resultado = pst.executeQuery();


            resultado.next();
            return resultado.getInt(1) ;
        }finally {
            c.close();
        }
    }
}
