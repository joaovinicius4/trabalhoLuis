package trabalholuis;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            String nomeLoja = lerTexto(entrada, "Digite o nome da loja: ");
            CadastroProdutos cadastro = new CadastroProdutos(nomeLoja);
            int opcao;

            do {
                System.out.println("\n=== " + cadastro.getNomeLoja() + " ===");
                System.out.println("1 - Cadastrar produto");
                System.out.println("2 - Listar produtos");
                System.out.println("3 - Buscar produto pelo ID");
                System.out.println("4 - Atualizar produto");
                System.out.println("5 - Excluir produto");
                System.out.println("6 - Calcular valor de um produto em estoque");
                System.out.println("0 - Sair");
                opcao = lerInteiro(entrada, "Escolha uma opcao: ", 0);

                switch (opcao) {
                    case 1: {
                        int id = lerInteiro(entrada, "ID: ", 1);
                        if (cadastro.buscarPorId(id) != null) {
                            System.out.println("Ja existe um produto com esse ID.");
                            break;
                        }
                        String nome = lerTexto(entrada, "Nome: ");
                        double preco = lerPreco(entrada);
                        int quantidade = lerInteiro(entrada, "Quantidade: ", 0);
                        Produto produto = new Produto(id, nome, preco, quantidade);
                        cadastro.cadastrar(produto);
                        System.out.println("Produto cadastrado com sucesso!");
                        break;
                    }
                    case 2:
                        cadastro.exibirProdutos();
                        break;
                    case 3: {
                        int id = lerInteiro(entrada, "ID para buscar: ", 1);
                        Produto produto = cadastro.buscarPorId(id);
                        if (produto == null) {
                            System.out.println("Produto nao encontrado.");
                        } else {
                            System.out.println(produto);
                        }
                        break;
                    }
                    case 4: {
                        int id = lerInteiro(entrada, "ID para atualizar: ", 1);
                        if (cadastro.buscarPorId(id) == null) {
                            System.out.println("Produto nao encontrado.");
                            break;
                        }
                        String nome = lerTexto(entrada, "Novo nome: ");
                        double preco = lerPreco(entrada);
                        int quantidade = lerInteiro(entrada, "Nova quantidade: ", 0);
                        cadastro.atualizar(id, nome, preco, quantidade);
                        System.out.println("Produto atualizado com sucesso!");
                        break;
                    }
                    case 5: {
                        int id = lerInteiro(entrada, "ID para excluir: ", 1);
                        if (cadastro.excluir(id)) {
                            System.out.println("Produto excluido com sucesso!");
                        } else {
                            System.out.println("Produto nao encontrado.");
                        }
                        break;
                    }
                    case 6: {
                        int id = lerInteiro(entrada, "ID do produto: ", 1);
                        Produto produto = cadastro.buscarPorId(id);
                        if (produto == null) {
                            System.out.println("Produto nao encontrado.");
                        } else {
                            System.out.printf("Valor de %s em estoque: R$ %.2f%n",
                                    produto.getNome(), produto.calcularValorEstoque());
                        }
                        break;
                    }
                    case 0:
                        System.out.println("Programa encerrado.");
                        break;
                    default:
                        System.out.println("Opcao invalida. Escolha de 0 a 6.");
                }
            } while (opcao != 0);
        }
    }

    private static String lerTexto(Scanner entrada, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = entrada.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("O texto nao pode ficar vazio.");
        }
    }

    private static int lerInteiro(Scanner entrada, String mensagem, int minimo) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(entrada.nextLine().trim());
                if (valor >= minimo) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // Solicita novamente quando a entrada nao e um inteiro valido.
            }
            System.out.println("Digite um numero inteiro maior ou igual a " + minimo + ".");
        }
    }

    private static double lerPreco(Scanner entrada) {
        while (true) {
            System.out.print("Preco (exemplo: 12,50): ");
            try {
                String texto = entrada.nextLine().trim().replace(',', '.');
                double preco = Double.parseDouble(texto);
                if (Double.isFinite(preco) && preco >= 0) {
                    return preco;
                }
            } catch (NumberFormatException e) {
                // Aceita virgula ou ponto como separador decimal.
            }
            System.out.println("Digite um preco valido maior ou igual a zero.");
        }
    }
}
