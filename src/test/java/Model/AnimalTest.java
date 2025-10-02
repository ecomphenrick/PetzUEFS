package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    private Animal animal;

    @BeforeEach
    void setUp() {
        animal = new Animal(
                "A001",                           // iD
                "Rex",                            // nome
                "Cão",                             // especie
                "Labrador",                        // raca
                LocalDate.of(2020, 10, 14),       // dataNascimento
                "M",                               // sexo
                "Disponível"                       // situacaoAtual
        );
    }


    @Test
    void getDataNascimento() {
        assertEquals(LocalDate.of(2020, 10, 14), animal.getDataNascimento());
    }

    @Test
    void setDataNascimento() {
        animal.setDataNascimento(LocalDate.of(2021, 11, 12));
        assertEquals(LocalDate.of(2021, 11, 12), animal.getDataNascimento());
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
        assertEquals("Disponível", animal.getSituacaoAtual());
    }

    @Test
    void setSituacaoAtual() {
        animal.setSituacaoAtual("Adotado");
        assertEquals("Adotado", animal.getSituacaoAtual());
    }
}
