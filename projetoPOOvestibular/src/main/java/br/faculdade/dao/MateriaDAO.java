package br.faculdade.dao;

import br.faculdade.curso.vestibular.Materia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO extends ConnectionDao{

    public List<Materia> selectMaterias(int id_vestibular){
        List<Materia> materias = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM materia WHERE id_vestibular=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id_vestibular);
            rs = pst.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia(
                        rs.getInt("id"),
                        rs.getString("materia")
                );
                materias.add(materia);
            }
        } catch (SQLException e){
            System.out.println("Erro ao buscar Matérias: " + e.getMessage());
        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }



        return materias;
    }

    public Materia selectMateriaPorId(int id) {
        connectToDb();
        String sql = "SELECT id, materia FROM materia WHERE id = ?";
        Materia materia = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                materia = new Materia(rs.getInt("id"), rs.getString("materia"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar Matéria: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return materia; // null = nao disponivel no BD
    }

}