import java.util.Scanner;
//https://github.com/Joaopedropetryovelar/Projeto-Final---Java.git
class Cliente {

    String nome;
    int id;
    int telefone;
    String cidade;

    public Cliente(String nome, int id, int telefone, String cidade) {
        this.nome = nome;
        this.id = id;
        this.telefone = telefone;
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Codigo: " + id + " | Nome: " + nome +" | Telefone: " + telefone +" | Cidade: " + cidade;
    }
}

class Produto {

    double preco;
    int estoque;
    String nome;
    int id;
    String marca;
    String descricao;

    public Produto(String nome, int id, double preco, int estoque, String marca, String descrisao) {
        this.nome = nome;
        this.id = id;
        this.preco = preco;
        this.estoque = estoque;
        this.marca = marca;
        this.descricao = descrisao;
    }

    @Override
    public String toString() {
        return "Codigo: " + id + " | Nome: " + nome + " | Marca: " + marca + " | Preço: " + preco + " | Estoque: " + estoque+ " | Descrição: " + descricao;
    }
}

class Maquiagem extends Produto {

    public Maquiagem(int id, String nome, String marca, double preco, int estoque, String descricao) {
        super(nome, id, preco, estoque, marca, descricao);
    }
}

class Perfume extends Produto {

    public Perfume(int id, String nome, String marca, double preco, int estoque, String descricao) {
        super(nome, id, preco, estoque, marca, descricao);
    }
}

public class LojaCosmeticos {

    static Cliente[] registrosClientes = new Cliente[20];
    static Produto[] registrosProdutos = new Produto[20];

    static int totalClientes = 0;
    static int totalProdutos = 0;

    

    public static void cadastrarCliente(Scanner scanner) {

        if (totalClientes >= registrosClientes.length) {
            System.out.println("Limite de clientes atingido.");
            return;
        }

        System.out.println("\n==== Cadastro do Cliente ====");
        
        System.out.print("ID: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) { //verifica se o ID é um número inteiro válido
            System.out.println("ID inválido. Cadastro cancelado.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty()) {
            System.out.println("Nome não pode ficar em branco. Cadastro cancelado.");
            return;
        }
        if (!nome.matches("^[\\p{L} ]+$")) {
            System.out.println("Nome inválido. Não deve conter números ou símbolos. Cadastro cancelado.");
            return;
        }

        
        System.out.print("Telefone: ");
        String telefoneStr = scanner.nextLine();
        if (telefoneStr == null || telefoneStr.trim().isEmpty() || !telefoneStr.trim().matches("-?\\d+")) {//verifica se o telefone é um número inteiro válido
            System.out.println("Telefone inválido. Cadastro cancelado.");
            return;
        }
        int telefone = Integer.parseInt(telefoneStr.trim());

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        if (cidade.trim().isEmpty()) {
            System.out.println("Cidade não pode ficar em branco. Cadastro cancelado.");
            return;
        }

        registrosClientes[totalClientes] =
            new Cliente(nome, id, telefone, cidade);

        totalClientes++;

        System.out.println("Cliente cadastrado com sucesso!");
    }

    public static void cadastrarMaquiagem(Scanner scanner) {

        if (totalProdutos >= registrosProdutos.length) {
            System.out.println("Limite de produtos atingido.");
            return;
        }

        System.out.println("\n==== Cadastro de Maquiagem ====");

        System.out.print("ID: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {//verifica se o ID é um número inteiro válido
            System.out.println("ID inválido. Cadastro cancelado.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty()) {
            System.out.println("Nome não pode ficar em branco. Cadastro cancelado.");
            return;
        }
        if (!nome.matches("^[\\p{L} ]+$")) { //verifixa se o nome contém apenas letras
            System.out.println("Nome inválido. Não deve conter números ou símbolos. Cadastro cancelado.");
            return;
        }

        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        if (marca.trim().isEmpty()) {
            System.out.println("Marca não pode ficar em branco. Cadastro cancelado.");
            return;
        }

        System.out.print("Estoque: ");
        String estoqueStr = scanner.nextLine();
        if (estoqueStr == null || estoqueStr.trim().isEmpty() || !estoqueStr.trim().matches("-?\\d+")) {//verifica se o estoque é um número inteiro válido
            System.out.println("Estoque inválido. Cadastro cancelado.");
            return;
        }
        int estoque = Integer.parseInt(estoqueStr.trim());

        System.out.print("Preço: ");
        String precoStr = scanner.nextLine();
        if (precoStr == null || precoStr.trim().isEmpty() || !precoStr.trim().matches("-?\\d+(\\.\\d+)?")) {//verifica se o preço é um número válido (inteiro ou decimal)
            System.out.println("Preço inválido. Cadastro cancelado.");
            return;
        }
        double preco = Double.parseDouble(precoStr.trim());

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
       

        registrosProdutos[totalProdutos] = new Maquiagem(id, nome, marca, preco, estoque, descricao);
        totalProdutos++;

        System.out.println("Produto cadastrado com sucesso!");
    }

    public static void cadastrarPerfume(Scanner scanner) {

        if (totalProdutos >= registrosProdutos.length) {
            System.out.println("Limite de produtos atingido.");
            return;
        }

        System.out.println("\n==== Cadastro de Perfume ====");

        System.out.print("ID: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {//verifica se o ID é um número inteiro válido
            System.out.println("ID inválido. Cadastro cancelado.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty()) {
            System.out.println("Nome não pode ficar em branco. Cadastro cancelado.");
            return;
        }
        if (!nome.matches("^[\\p{L} ]+$")) {//  verifixa se o nome contém apenas letras
            System.out.println("Nome inválido. Não deve conter números ou símbolos. Cadastro cancelado.");
            return;
        }

        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        if (marca.trim().isEmpty()) {
            System.out.println("Marca não pode ficar em branco. Cadastro cancelado.");
            return;
        }

        System.out.print("Estoque: ");
        String estoqueStr = scanner.nextLine();
        if (estoqueStr == null || estoqueStr.trim().isEmpty() || !estoqueStr.trim().matches("-?\\d+")) {//verifica se o estoque é um número inteiro válido
            System.out.println("Estoque inválido. Cadastro cancelado.");
            return;
        }
        int estoque = Integer.parseInt(estoqueStr.trim());

        System.out.print("Preço: ");
        String precoStr = scanner.nextLine();
        if (precoStr == null || precoStr.trim().isEmpty() || !precoStr.trim().matches("-?\\d+(\\.\\d+)?")) {//verifica se o preço é um número válido (inteiro ou decimal)
            System.out.println("Preço inválido. Cadastro cancelado.");
            return;
        }
        double preco = Double.parseDouble(precoStr.trim());

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        

        registrosProdutos[totalProdutos] =new Perfume(id, nome, marca, preco, estoque, descricao);

        totalProdutos++;

        System.out.println("Produto cadastrado com sucesso!");
    }

    public static void MenuProduto(Scanner scanner) {

        System.out.println("\n====== MENU PRODUTO ======");
        System.out.println("1 - Cadastrar Maquiagem");
        System.out.println("2 - Cadastrar Perfume");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");

        String opcStr = scanner.nextLine();
        if (opcStr == null || opcStr.trim().isEmpty() || !opcStr.trim().matches("-?\\d+")) {//verifica se a entrada é um número inteiro
            System.out.println("Opção inválida!");
            return;
        }
        int opc = Integer.parseInt(opcStr.trim());

        switch (opc) {

            case 1:
                cadastrarMaquiagem(scanner);
                break;
            case 2:
                cadastrarPerfume(scanner);
                break;

            case 0:
                return;

            default:
                System.out.println("Opção inválida!");
        }
    }

    public static void listarRegistrosClientes() {

        if (totalClientes == 0) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("\n--- Lista de Clientes ---");

        for (int i = 0; i < totalClientes; i++) {
            System.out.println(registrosClientes[i]);
        }
    }

    public static void listarRegistrosProdutos() {

        if (totalProdutos == 0) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n--- Lista de Produtos ---");

        for (int i = 0; i < totalProdutos; i++) {
            System.out.println(registrosProdutos[i]);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== LOJA DE COSMÉTICOS =====");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Menu Produtos");
            System.out.println("3 - Listar Clientes");
            System.out.println("4 - Listar Produtos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int opcao;
            String opcaoStr = scanner.nextLine();
            if (opcaoStr == null || opcaoStr.trim().isEmpty() || !opcaoStr.trim().matches("-?\\d+")) { //verifica se a entrada é um número inteiro
                System.out.println("Opção inválida!");
                continue;
            }
            opcao = Integer.parseInt(opcaoStr.trim());

            switch (opcao) {

                case 1:
                    cadastrarCliente(scanner);
                    break;

                case 2:
                    MenuProduto(scanner);
                    break;

                case 3:
                    listarRegistrosClientes();
                    break;

                case 4:
                    listarRegistrosProdutos();
                    break;

                case 0:
                    System.out.println("Programa encerrado.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}