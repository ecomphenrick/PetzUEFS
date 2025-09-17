package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    private Animal animal;

    @BeforeEach
    void setUp() {
        animal = new Animal();
        animal.setiD("A001");
        animal.setNome("Rex");
        animal.setRaca("Labrador");
        animal.setSexo("M");
        animal.setEspecie("Cão");
        animal.setDataNascimento("2020-05-01");
        animal.setSituacaoAtual("Disponível");
    }

    @Test
    void getDataNascimento() {
        assertEquals("2020-05-01", animal.getDataNascimento());
    }

    @Test
    void setDataNascimento() {
        animal.setDataNascimento("2021-01-01");
        assertEquals("2021-01-01", animal.getDataNascimento());
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
        animal.setiD("B002");
        assertEquals("B002", animal.getiD());
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
        assertEquals("Disponível", animal.getSituacaoAtual());
    }

    @Test
    void setSituacaoAtual() {
        animal.setSituacaoAtual("Adotado");
        assertEquals("Adotado", animal.getSituacaoAtual());
    }
}
