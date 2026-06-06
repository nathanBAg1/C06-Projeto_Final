package br.faculdade.dao;
import java.sql.*;

public abstract class ConnectionDao
{
    Connection connection;     //Conexao com o banco

    PreparedStatement pst ;   //Comando SQL com parâmetros
    Statement st ;           //Comando SQL sem parâmetros
    ResultSet rs ;          //resultado de consultas

    String database = "faculdade";
    String user = "root";
    String password  = "root";
    String url = "jdbc:mysql://localhost:3306/" + database;

    public Connection connectToDb() {
        try
        {
            connection = DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados");
        }
        return null ;
    }

}