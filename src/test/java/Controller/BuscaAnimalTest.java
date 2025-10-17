package Controller;

import Model.Animal;
import org.junit.jupiter.api.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste para {@link BuscaAnimal}.
 * <p>
 * Testa a funcionalidade de busca de animais pelo nome em um arquivo JSON.
 * </p>
 *
 * @author Henrick Ferreira
 */
public class BuscaAnimalTest {

    private static final String CAMINHO_ARQUIVO = "animal.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Cria um arquivo JSON de teste com alguns animais cadastrados.
     */
    @BeforeEach
    public void setUp() {
        List<Animal> animais = new ArrayList<>();
        animais.add(new Animal("A001", "Maui", "Cachorro", "SRD", "2018-01-01", "M", "Disponível", "Reitoria", Arrays.asList("Henrick")));
        animais.add(new Animal("A002", "Dona Mel", "Gato", "SRD", "2020-01-01", "F", "Em observação", "Biblioteca", Arrays.asList("João")));
        animais.add(new Animal("A003", "Pitoco", "Cachorro", "Poodle", "2019-06-10", "M", "Em tratamento", "Modulo 1", Arrays.asList("Maria")));

        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
            gson.toJson(animais, writer);
        } catch (IOException e) {
            fail("Erro ao criar arquivo de teste: " + e.getMessage());
        }
    }

    /**
     * Testa a busca por um animal existente no arquivo.
     */
    @Test
    public void testBuscaAnimalExistente() {
        BuscaAnimal busca = new BuscaAnimal();
        List<Animal> resultado = busca.buscaAnimais("Maui");

        assertFalse(resultado.isEmpty(), "A lista não deve estar vazia");
        assertEquals("Maui", resultado.get(0).getNome());
    }

    /**
     * Testa a busca por um animal inexistente no arquivo.
     */
    @Test
    public void testBuscaAnimalInexistente() {
        BuscaAnimal busca = new BuscaAnimal();
        List<Animal> resultado = busca.buscaAnimais("Zeus");

        assertTrue(resultado.isEmpty(), "A lista deve estar vazia");
    }

    /**
     * Testa o comportamento quando o arquivo JSON não existe.
     */
    @Test
    public void testArquivoInexistente() {
        // Remove o arquivo para simular erro
        java.io.File arquivo = new java.io.File(CAMINHO_ARQUIVO);
        if (arquivo.exists()) {
            arquivo.delete();
        }

        BuscaAnimal busca = new BuscaAnimal();
        List<Animal> resultado = busca.buscaAnimais("Maui");

        assertTrue(resultado.isEmpty(), "Deve retornar lista vazia em caso de erro de leitura");
    }
}
