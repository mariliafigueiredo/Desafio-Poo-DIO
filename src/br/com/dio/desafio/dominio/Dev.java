package br.com.dio.desafio.dominio;
import java.util.*;

public class Dev {
    private String nome;
    //abaixo tem uso, tanto de Herança (de Conteúdo) como Polimorfismo (LinkedHashSet instanciado em conteudos inscritos e concluídos)
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();//essa collection Set é pq só permite add elementos únicos
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();//essa collection Set é pq só permite add elementos únicos

    public void inscreverBootcamp(Bootcamp bootcamp){
        this.conteudosInscritos.addAll(bootcamp.getConteudos());//adição de tds os conteúdos do bootcamp no conteúdos inscritos
        bootcamp.getDevsInscritos().add(this);// adição do Dev inscrito no bootcamp
    }

    public void progredir() {
        Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();// primeiro conteudo e sequencia de acordo à ordem do bootcamp
        if(conteudo.isPresent()) {
            this.conteudosConcluidos.add(conteudo.get());
            this.conteudosInscritos.remove(conteudo.get());
        } else {
            System.err.println("Você não está matriculado em nenhum conteúdo!");
        }
    }

    public double calcularTotalXp() {
        Iterator<Conteudo> iterator = this.conteudosConcluidos.iterator();
        double soma = 0;
        while(iterator.hasNext()){
            double next = iterator.next().calcularXp();
            soma += next;
        }
        return soma;

        /*return this.conteudosConcluidos
                .stream()
                .mapToDouble(Conteudo::calcularXp) - aqui o método lambda foi substituído pelo reference method
                .sum();*/
    }

    //Getters and Setters abaixo (Alt insert)

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public void setConteudosInscritos(Set<Conteudo> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public void setConteudosConcluidos(Set<Conteudo> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }

        //como estou trabalhando com Hash, preciso implementar equals & hash Code abaixo (usar Alt insert)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) && Objects.equals(conteudosInscritos, dev.conteudosInscritos) && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }
}
