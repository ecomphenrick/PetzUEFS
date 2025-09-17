package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetorResponsavelTest {

    private SetorResponsavel setor;

    @BeforeEach
    void setUp() {
        setor = new SetorResponsavel();
        setor.setiD("S001");
        setor.setNome("Adoção");
        setor.setEndereco("Rua Principal, 100");
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
        assertEquals("Adoção", setor.getNome());
    }

    @Test
    void setNome() {
        setor.setNome("Veterinário");
        assertEquals("Veterinário", setor.getNome());
    }
}
