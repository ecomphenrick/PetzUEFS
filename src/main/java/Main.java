import View.MenusPrincipais.MenuPrincipal;

import java.io.IOException;

/**
 * Classe principal do sistema.
 * <p>
 * Inicializa o programa e exibe o menu principal.
 * </p>
 *
 * @author Henrick
 * @version 1.0
 */
public class Main {

    /**
     * Método principal (entry point) do sistema.
     * <p>
     * Cria uma instância do MenuPrincipal e inicia o menu.
     * </p>
     *
     * @param args Argumentos de linha de comando (não utilizados)
     * @throws IOException Caso ocorra algum erro de I/O ao iniciar o menu
     */
    public static void main(String[] args) throws IOException {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.MenuPrincipal();
    }
}
