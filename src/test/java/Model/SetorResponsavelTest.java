package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SetorResponsavelTest {

    private SetorResponsavel setor;
    private PessoaTutora tutor;
    private Animal animal;

    @BeforeEach
    void setUp() {
        // Criando tutor
        tutor = new PessoaTutora(
                "P001",
                "Maria Silva",
                "Rua A, 123",
                "75992658825",
                "maria@gmail.com",
                List.of("Pitoco")
        );

        // Criando animal
        animal = new Animal(
                "A001",
                "Rex",
                "Cão",
                "Labrador",
                "2020-10-14",
                "M",
                "Disponível para adoção",
                "Reitoria",
                List.of("Maria Silva")
        );

        // Criando setor com listas inicializadas
        setor = new SetorResponsavel(
                "S001",
                "Reitoria",
                "Rua Principal, 100",
                List.of(tutor),
                List.of(animal)
        );
    }

    @Test
    void getEndereco() {
        assertEquals("Rua Principal, 100", setor.getEndereco());
    }

    @Test
    void setEndereco() {
        setor.setEndereco("Avenida Central, 200");
        assertEquals("Avenida Central, 200", setor.getEndereco());
    }

    @Test
    void getiD() {
        assertEquals("S001", setor.getiD());
    }

    @Test
    void setiD() {
        setor.setiD("S002");
        assertEquals("S002", setor.getiD());
    }

    @Test
    void getNome() {
        assertEquals("Reitoria", setor.getNome());
    }

    @Test
    void setNome() {
        setor.setNome("Biblioteca");
        assertEquals("Biblioteca", setor.getNome());
    }

    @Test
    void getPessoaTutoras() {
        assertEquals(1, setor.getPessoaTutoras().size());
        assertEquals("Maria Silva", setor.getPessoaTutoras().get(0).getNome());
    }

    @Test
    void setPessoaTutoras() {
        PessoaTutora novoTutor = new PessoaTutora(
                "P002",
                "João Pereira",
                "Rua B, 456",
                "987654321",
                "joao@gmail.com",
                List.of("Bidu")
        );
        setor.setPessoaTutoras(List.of(novoTutor));
        assertEquals("João Pereira", setor.getPessoaTutoras().get(0).getNome());
    }

    @Test
    void getAnimais() {
        assertEquals(1, setor.getAnimais().size());
        assertEquals("Rex", setor.getAnimais().get(0).getNome());
    }

    @Test
    void setAnimais() {
        Animal novoAnimal = new Animal(
                "A002",
                "Bidu",
                "Cão",
                "Poodle",
                "2019-06-10",
                "M",
                "Em observação",
                "Reitoria",
                List.of("João Pereira")
        );
        setor.setAnimais(List.of(novoAnimal));
        assertEquals("Bidu", setor.getAnimais().get(0).getNome());
    }
}

