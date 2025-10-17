package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    private Animal animal;

    @BeforeEach
    void setUp() {
        animal = new Animal(
                "A001",              // iD
                "Rex",               // nome
                "Cão",               // espécie
                "Labrador",          // raça
                "2020-10-14",        // dataNascimento (String)
                "M",                 // sexo
                "Disponível para adoção",        // situação atual
                "Reitoria",             // nomeSetor
                List.of("Henrick")   // tutores
        );
    }

    @Test
    void getDataNascimento() {
        assertEquals("2020-10-14", animal.getDataNascimento());
    }

    @Test
    void setDataNascimento() {
        animal.setDataNascimento("2021-11-12");
        assertEquals("2021-11-12", animal.getDataNascimento());
    }

    @Test
    void getEspecie() {
        assertEquals("Cão", animal.getEspecie());
    }

    @Test
    void setEspecie() {
        animal.setEspecie("Gato");
        assertEquals("Gato", animal.getEspecie());
    }

    @Test
    void getiD() {
        assertEquals("A001", animal.getiD());
    }

    @Test
    void setiD() {
        animal.setiD("A002");
        assertEquals("A002", animal.getiD());
    }

    @Test
    void getNome() {
        assertEquals("Rex", animal.getNome());
    }

    @Test
    void setNome() {
        animal.setNome("Max");
        assertEquals("Max", animal.getNome());
    }

    @Test
    void getRaca() {
        assertEquals("Labrador", animal.getRaca());
    }

    @Test
    void setRaca() {
        animal.setRaca("Golden Retriever");
        assertEquals("Golden Retriever", animal.getRaca());
    }

    @Test
    void getSexo() {
        assertEquals("M", animal.getSexo());
    }

    @Test
    void setSexo() {
        animal.setSexo("F");
        assertEquals("F", animal.getSexo());
    }

    @Test
    void getSituacaoAtual() {
        assertEquals("Disponível para adoção", animal.getSituacaoAtual());
    }

    @Test
    void setSituacaoAtual() {
        animal.setSituacaoAtual("Em observação");
        assertEquals("Em observação", animal.getSituacaoAtual());
    }

    @Test
    void getNomeSetor() {
        assertEquals("Reitoria", animal.getNomeSetor());
    }

    @Test
    void setNomeSetor() {
        animal.setNomeSetor("Biblioteca");
        assertEquals("Biblioteca", animal.getNomeSetor());
    }

    @Test
    void getTutores() {
        assertEquals(List.of("Henrick"), animal.getTutores());
    }

    @Test
    void setTutores() {
        animal.setTutores(List.of("Maria", "João"));
        assertEquals(List.of("Maria", "João"), animal.getTutores());
    }
}

