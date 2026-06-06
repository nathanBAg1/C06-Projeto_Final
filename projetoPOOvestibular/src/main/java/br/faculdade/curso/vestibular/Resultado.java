package br.faculdade.curso.vestibular;

public class Resultado {

    protected Integer nota;

    public Resultado(Integer nota) {
        this.nota = nota;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}