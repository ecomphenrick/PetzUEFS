package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTutoraTest {

    private PessoaTutora pessoa;

    @BeforeEach
    void setUp() {
        pessoa = new PessoaTutora();
        pessoa.setNome("Maria Silva");
        pessoa.setEmail("maria@email.com");
        pessoa.setTelefone("123456789");
        pessoa.setEndereco("Rua A, 123");
        pessoa.setiD("1");
    }

    @Test
    void getEmail() {
        assertEquals("maria@email.com", pessoa.getEmail());
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
        assertEquals("1", pessoa.getiD());
    }

    @Test
    void setiD() {
        pessoa.setiD("99");
        assertEquals("99", pessoa.getiD());
    }

    @Test
    void getTelefone() {
        assertEquals("123456789", pessoa.getTelefone());
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
