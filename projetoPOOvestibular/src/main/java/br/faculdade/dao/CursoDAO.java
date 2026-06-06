package br.faculdade.dao;

import br.faculdade.curso.Curso;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CursoDAO extends ConnectionDao{

    public Curso selectCurso(int id){
        Curso curso = null;
        connectToDb();
        String sql = "SELECT * FROM curso WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()) {
                curso = new Curso(
                        rs.getInt("id"),
                        rs.getString("nome")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o Curso: " + e.getMessage());

        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return curso;
    }

    public Map<Integer, Curso> selectTodosCursos(){
        connectToDb();

        int index = 1;
        Map<Integer, Curso> cursosDisponiveis = new HashMap<>();

        String sql = "SELECT * FROM curso";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Curso curso = new Curso(
                        rs.getInt("id"),
                        rs.getString("nome")
                );
                cursosDisponiveis.put(index, curso);
                index++;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar Cursos: " + e.getMessage());

        } finally {
            try {
                if(rs != null) rs.close();
                if(st != null) st.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return cursosDisponiveis;
    }
}
