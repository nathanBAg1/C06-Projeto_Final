package br.faculdade.dao;

import br.faculdade.curso.vestibular.Resultado;
import br.faculdade.curso.vestibular.Vestibular;
import br.faculdade.usuarios.Vestibulando;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VestibulandoPrestaVestibularDAO extends ConnectionDao{

    public List<Integer> selectIdVestibularesPrestados(String cpf_vestibulando){
        List<Integer> ids_vestibulares = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM vestibulando_presta_vestibular WHERE cpf_vestibulando=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf_vestibulando);
            rs = pst.executeQuery();
            while(rs.next()) {

                ids_vestibulares.add(rs.getInt("id_vestibular"));

            }
        } catch (SQLException e) {
            System.out.println("Erro ao Selecionar a relação entre Vestibulando e Vestibular: " + e.getMessage());

        } finally {
            try {
                if(rs != null) rs.close();
                if(st != null) st.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return ids_vestibulares;
    }

    public boolean insertVestibulandoPrestaVestibular(Vestibulando vestibulando, Vestibular vestibular){
        connectToDb();
        String sql = "INSERT INTO vestibulando_presta_vestibular(id_vestibular, cpf_vestibulando, numero_inscricao) VALUES (?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, vestibular.getId());
            pst.setString(2, vestibulando.getCpf());
            pst.setInt(3, selectMaxNumeroInscricao(vestibulando, vestibular));
            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao inserir a relação entre Vestibulando e Vestibular: " + e.getMessage());
            return false;

        } finally {
            try {
                if(pst != null) pst.close();
                if(rs != null) rs.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }


    public int selectMaxNumeroInscricao(Vestibulando vestibulando, Vestibular vestibular){
        connectToDb();
        String sql = "SELECT MAX(numero_inscricao) FROM vestibulando_presta_vestibular";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()) {
                return rs.getInt("MAX(numero_inscricao)") + 1;
            }

            return 0;

        } catch (SQLException e) {

            System.out.println("Erro ao buscar o maximo valor do numero de inscricao" + e.getMessage());

            return 0;
        } finally {
            try {
                if(rs != null) rs.close();
                if(st != null) st.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }


    public boolean lancarNota(String cpf_vestibulando, int id_vestibular, int nota){
        connectToDb();
        String sql = "UPDATE vestibulando_presta_vestibular " +
                "SET nota=? WHERE cpf_vestibulando=? AND id_vestibular=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, nota);
            pst.setString(2, cpf_vestibulando);
            pst.setInt(3, id_vestibular);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao lançar a nota: " + e.getMessage());
            return false;
        } finally {
            try {
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    // le a nota do vestibulando num vestibular (null = ainda nao corrigida)
    public Resultado selectNota(String cpf_vestibulando, int id_vestibular){
        connectToDb();
        String sql = "SELECT nota FROM vestibulando_presta_vestibular " +
                "WHERE cpf_vestibulando=? AND id_vestibular=?";
        Resultado resultado = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf_vestibulando);
            pst.setInt(2, id_vestibular);
            rs = pst.executeQuery();
            if (rs.next()) {
                int nota = rs.getInt("nota");
                // wasNull() distingue "nota NULL" (nao corrigida) de "nota 0"
                resultado = new Resultado(rs.wasNull() ? null : nota);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar a nota: " + e.getMessage());
        } finally {
            try {
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return resultado;
    }
}