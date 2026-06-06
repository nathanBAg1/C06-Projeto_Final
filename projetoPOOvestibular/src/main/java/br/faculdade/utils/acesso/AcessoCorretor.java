package br.faculdade.utils.acesso;

import br.faculdade.exceptions.UsuarioVazioException;
import br.faculdade.usuarios.Corretor;
import br.faculdade.utils.login.LoginCorretor;
import br.faculdade.utils.telas.TelaCorretor;

public class AcessoCorretor {

    public static void acessoCorretor() {

        Corretor corretor;

        System.out.println("Bem Vindo Corretor");

        corretor = LoginCorretor.loginCorretor();

        try {
            if (corretor != null) {
                TelaCorretor.telaCorretor(corretor);
            } else {
                throw new UsuarioVazioException("Não foi possível completar seu acesso.");
            }
        } catch (UsuarioVazioException e) {
            System.out.println(e.getMessage());
        }

    }

}