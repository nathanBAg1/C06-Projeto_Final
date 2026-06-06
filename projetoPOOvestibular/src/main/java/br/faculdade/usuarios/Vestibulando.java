package br.faculdade.usuarios;
import br.faculdade.curso.vestibular.Sala;
import br.faculdade.curso.vestibular.Vestibular;
import br.faculdade.exceptions.NotaInvalidaException;
import br.faculdade.exceptions.NotaNaoEncontradaException;
import br.faculdade.interfaces.ConsultarNota;

import java.util.ArrayList;
import java.util.List;

public class Vestibulando extends Pessoa implements ConsultarNota {

    private List<Vestibular> vestibulares = new ArrayList<>();
    private Sala sala;

    //ao logar um vestibulando,o vestibulando deve ser capaz de puxar seus dados como "pessoa" do banco e criar o construtor//
    public Vestibulando (String cpf ,String nome , String email)
    {
        super(cpf,nome,email);
    }
    //o vestibulando em especifico ao checar seus dados deve ser capaz de puxar do banco seus dados como vestibulando //
    @Override
    public void confereSeusDadosEspecificos()
    {
        System.out.println("Alocado para a sala " +  this.sala.getNumeroSala());
    }

    //adicionar a modificacao no bd dps
    @Override
    public void atualizaEmail(String novoemail)
    {
        setEmail(novoemail);
    }

    //o aluno pode ver os dados do vestibular//
    public void confereDadosVestibular()
    {
        for(Vestibular vestibular : vestibulares) {
            vestibular.DadosVestibular();
        }
    }

    //e preciso considerar a possibilidade do aluno checar a nota antes do supervisor a postar,um possivel caso de erro//
    @Override
    public void pesquisaNota()
    {
        for(Vestibular vestibular : this.vestibulares) {
            if (vestibular.getResultado() != null && vestibular.getResultado().getNota() >= 0) {
                System.out.println("Nota: " + vestibular.getResultado().getNota());
            } else if (vestibular.getResultado() == null) {
                throw new NotaNaoEncontradaException("Nota não encontrada.");
            } else if (vestibular.getResultado().getNota() < 0) {
                throw new NotaInvalidaException("Nota com valor inválido.");
            }
        }
    }

    public Sala getSala() throws NullPointerException{
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void addVestibular(Vestibular vestibular) {
        vestibulares.add(vestibular);
    }
}
