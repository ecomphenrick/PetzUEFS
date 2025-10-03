package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTutoraTest {

    private PessoaTutora pessoa;

    @BeforeEach
    void setUp() {
        pessoa = new PessoaTutora("P001", "Maria Silva", "Rua A, 123", "75992658825", "maria@gmail.com");
    }

    @Test
    void getEmail() {
        assertEquals("maria@gmail.com", pessoa.getEmail());
    }

    @Test
    void setEmail() {
        pessoa.setEmail("novoemail@email.com");
        assertEquals("novoemail@email.com", pessoa.getEmail());
    }

    @Test
    void getEndereco() {
        assertEquals("Rua A, 123", pessoa.getEndereco());
    }

    @Test
    void setEndereco() {
        pessoa.setEndereco("Avenida Central, 456");
        assertEquals("Avenida Central, 456", pessoa.getEndereco());
    }

    @Test
    void getiD() {
        assertEquals("P001", pessoa.getiD());
    }

    @Test
    void setiD() {
        pessoa.setiD("99");
        assertEquals("99", pessoa.getiD());
    }

    @Test
    void getTelefone() {
        assertEquals("75992658825", pessoa.getTelefone());
    }

    @Test
    void setTelefone() {
        pessoa.setTelefone("987654321");
        assertEquals("987654321", pessoa.getTelefone());
    }

    @Test
    void getNome() {
        assertEquals("Maria Silva", pessoa.getNome());
    }

    @Test
    void setNome() {
        pessoa.setNome("João Pereira");
        assertEquals("João Pereira", pessoa.getNome());
    }
}
