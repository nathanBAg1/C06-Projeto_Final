package br.faculdade.dao;

import br.faculdade.curso.vestibular.Vestibular;

import java.sql.SQLException;

public class VestibularDAO extends ConnectionDao{

    public Vestibular selectVestibular(int id){
        connectToDb();
        Vestibular vestibular = null;
        String sql = "SELECT * FROM vestibular WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()) {
                vestibular = new Vestibular(
                        rs.getInt("id"),
                        rs.getString("data_realizacao")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Selecionar o Vestibular: " + e.getMessage());

        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return vestibular;
    }
}
