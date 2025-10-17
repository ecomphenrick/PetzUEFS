package Controller;

import Model.PessoaTutora;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes para a classe {@link BuscaTutor}.
 * Verifica se a busca de tutores pelo nome funciona corretamente.
 *
 * @author Henrick Ferreira
 */
class BuscaTutorTest {

    private static final String CAMINHO_ARQUIVO = "tutor.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private PessoaTutora tutor;

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

        // Salvando em JSON
        List<PessoaTutora> tutores = new ArrayList<>();
        tutores.add(tutor);

        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
            gson.toJson(tutores, writer);
        } catch (IOException e) {
            fail("Erro ao criar arquivo de teste: " + e.getMessage());
        }
    }

    @Test
    void testBuscaTutorExistente() {
        BuscaTutor busca = new BuscaTutor();
        List<PessoaTutora> resultado = busca.buscaTutor("Maria Silva");

        assertFalse(resultado.isEmpty());
        assertEquals("Maria Silva", resultado.get(0).getNome());
        assertEquals("maria@gmail.com", resultado.get(0).getEmail());
        assertEquals(1, resultado.get(0).getAnimals().size());
        assertEquals("Pitoco", resultado.get(0).getAnimals().get(0));
    }

    @Test
    void testBuscaTutorInexistente() {
        BuscaTutor busca = new BuscaTutor();
        List<PessoaTutora> resultado = busca.buscaTutor("João Pereira");

        assertTrue(resultado.isEmpty());
    }

    @Test
    void testArquivoInexistente() {
        // Apaga o arquivo para simular erro
        java.io.File arquivo = new java.io.File(CAMINHO_ARQUIVO);
        if (arquivo.exists()) {
            arquivo.delete();
        }

        BuscaTutor busca = new BuscaTutor();
        List<PessoaTutora> resultado = busca.buscaTutor("Maria Silva");

        assertTrue(resultado.isEmpty());
    }
}
