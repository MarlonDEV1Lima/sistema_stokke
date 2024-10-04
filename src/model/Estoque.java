package model;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Produto> produtos;
    private int nextId;

    public Estoque() {
        produtos = new ArrayList<>();
        nextId = 1; // Começa com o ID 1
    }

    // Adiciona um produto ao estoque
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    // Busca um produto pelo ID
    public Produto buscarProdutoPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    // Retorna a lista de produtos
    public List<Produto> getProdutos() {
        return produtos;
    }

    // Gera o próximo ID disponível
    public int getNextId() {
        return nextId++;
    }
}
