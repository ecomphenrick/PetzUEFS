package View.MenusCadastro;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CadastroTutorVoidTest {

    @Test
    void testeCadastroTutorComDadosValidos() {
        String input = "T001\nHenrick\nRua A, 123\n11999999999\nhenrick@gmail.com\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        CadastroTutor cadastro = new CadastroTutor();

        // Apenas garante que não lança exceção
        assertDoesNotThrow(() -> cadastro.CadastroTutor());
    }

    @Test
    void testeCadastroComTelefoneInvalido() {
        // Primeiro telefone inválido, depois válido
        String input = "T002\nMaria\nRua B, 456\n123\n11988887777\nmaria@gmail.com\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        CadastroTutor cadastro = new CadastroTutor();

        // Apenas garante que não trava e finaliza
        assertDoesNotThrow(() -> cadastro.CadastroTutor());
    }

    @Test
    void testeCadastroComEmailInvalido() {
        // Primeiro e-mail inválido, depois válido
        String input = "T003\nJoão\nRua C, 789\n11977776666\njoao@@gmail..com\njoao@gmail.com\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        CadastroTutor cadastro = new CadastroTutor();

        // Apenas garante que não lança exceção
        assertDoesNotThrow(() -> cadastro.CadastroTutor());
    }
}



