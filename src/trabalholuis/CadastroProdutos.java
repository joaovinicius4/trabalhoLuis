package trabalholuis;

import java.util.ArrayList;
import java.util.List;

public class CadastroProdutos {
    private String nomeLoja;
    private final List<Produto> produtos;

    public CadastroProdutos(String nomeLoja) {
        this.nomeLoja = nomeLoja;
        this.produtos = new ArrayList<>();
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    // CREATE: cadastra um produto, impedindo IDs repetidos.
    public boolean cadastrar(Produto produto) {
        if (produto == null || buscarPorId(produto.getId()) != null) {
            return false;
        }
        produtos.add(produto);
        return true;
    }

    // READ: busca um produto pelo ID.
    public Produto buscarPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    // READ: devolve uma copia da lista de produtos cadastrados.
    public List<Produto> listar() {
        return new ArrayList<>(produtos);
    }

    // UPDATE: altera os dados do produto encontrado.
    public boolean atualizar(int id, String nome, double preco, int quantidade) {
        Produto produto = buscarPorId(id);
        if (produto == null) {
            return false;
        }
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setQuantidade(quantidade);
        return true;
    }

    // DELETE: exclui um produto pelo ID.
    public boolean excluir(int id) {
        Produto produto = buscarPorId(id);
        if (produto == null) {
            return false;
        }
        produtos.remove(produto);
        return true;
    }

    public void exibirProdutos() {
        System.out.println("Produtos da loja " + getNomeLoja() + ":");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        }
        for (Produto produto : listar()) {
            System.out.println(produto);
        }
    }
}

