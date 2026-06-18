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

    public static int buscarClientePorId(int id) {

        for (int i = 0; i < totalClientes; i++) {
            if (registrosClientes[i].id == id) {
                return i;
            }
        }
        return -1;
    }

    public static int buscarProdutoPorId(int id) {

        for (int i = 0; i < totalProdutos; i++) {
            if (registrosProdutos[i].id == id) {
                return i;
            }
        }
        return -1;
    }

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

    public static void consultarCliente(Scanner scanner) {

        System.out.println("\n==== Consultar Cliente ====");
        System.out.print("ID do cliente: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {//verifica se o ID é um número inteiro válido
            System.out.println("ID inválido.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());

        int indice = buscarClientePorId(id);
        if (indice == -1) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println(registrosClientes[indice]);
    }

    public static void consultarProduto(Scanner scanner) {

        System.out.println("\n==== Consultar Produto ====");
        System.out.print("ID do produto: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {//verifica se o ID é um número inteiro válido
            System.out.println("ID inválido.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());

        int indice = buscarProdutoPorId(id);
        if (indice == -1) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.println(registrosProdutos[indice]);
    }

    public static void alterarCliente(Scanner scanner) {

        System.out.println("\n==== Alterar Cliente ====");
        System.out.print("ID do cliente: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {//verifica se o ID é um número inteiro válido
            System.out.println("ID inválido.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());

        int indice = buscarClientePorId(id);
        if (indice == -1) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Cliente cliente = registrosClientes[indice];
        System.out.println("Dados atuais -> " + cliente);

        System.out.println("1 - Nome");
        System.out.println("2 - Telefone");
        System.out.println("3 - Cidade");
        System.out.println("0 - Voltar");
        System.out.print("O que deseja alterar? ");

        String opcStr = scanner.nextLine();
        if (opcStr == null || opcStr.trim().isEmpty() || !opcStr.trim().matches("-?\\d+")) {//verifica se a entrada é um número inteiro
            System.out.println("Opção inválida!");
            return;
        }
        int opc = Integer.parseInt(opcStr.trim());

        switch (opc) {

            case 1:
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                if (nome.trim().isEmpty() || !nome.matches("^[\\p{L} ]+$")) {//verifica se o nome contém apenas letras
                    System.out.println("Nome inválido. Alteração cancelada.");
                    return;
                }
                cliente.nome = nome;
                System.out.println("Nome alterado com sucesso!");
                break;

            case 2:
                System.out.print("Novo telefone: ");
                String telefoneStr = scanner.nextLine();
                if (telefoneStr == null || telefoneStr.trim().isEmpty() || !telefoneStr.trim().matches("-?\\d+")) {//verifica se o telefone é um número inteiro válido
                    System.out.println("Telefone inválido. Alteração cancelada.");
                    return;
                }
                cliente.telefone = Integer.parseInt(telefoneStr.trim());
                System.out.println("Telefone alterado com sucesso!");
                break;

            case 3:
                System.out.print("Nova cidade: ");
                String cidade = scanner.nextLine();
                if (cidade.trim().isEmpty()) {
                    System.out.println("Cidade não pode ficar em branco. Alteração cancelada.");
                    return;
                }
                cliente.cidade = cidade;
                System.out.println("Cidade alterada com sucesso!");
                break;

            case 0:
                return;

            default:
                System.out.println("Opção inválida!");
        }
    }

    public static void alterarProduto(Scanner scanner) {

        System.out.println("\n==== Alterar Produto ====");
        System.out.print("ID do produto: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {//verifica se o ID é um número inteiro válido
            System.out.println("ID inválido.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());

        int indice = buscarProdutoPorId(id);
        if (indice == -1) {
            System.out.println("Produto não encontrado.");
            return;
        }

        Produto produto = registrosProdutos[indice];
        System.out.println("Dados atuais -> " + produto);

        System.out.println("1 - Nome");
        System.out.println("2 - Marca");
        System.out.println("3 - Preço");
        System.out.println("4 - Estoque");
        System.out.println("5 - Descrição");
        System.out.println("0 - Voltar");
        System.out.print("O que deseja alterar? ");

        String opcStr = scanner.nextLine();
        if (opcStr == null || opcStr.trim().isEmpty() || !opcStr.trim().matches("-?\\d+")) {//verifica se a entrada é um número inteiro
            System.out.println("Opção inválida!");
            return;
        }
        int opc = Integer.parseInt(opcStr.trim());

        switch (opc) {

            case 1:
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                if (nome.trim().isEmpty() || !nome.matches("^[\\p{L} ]+$")) {//verifica se o nome contém apenas letras
                    System.out.println("Nome inválido. Alteração cancelada.");
                    return;
                }
                produto.nome = nome;
                System.out.println("Nome alterado com sucesso!");
                break;

            case 2:
                System.out.print("Nova marca: ");
                String marca = scanner.nextLine();
                if (marca.trim().isEmpty()) {
                    System.out.println("Marca não pode ficar em branco. Alteração cancelada.");
                    return;
                }
                produto.marca = marca;
                System.out.println("Marca alterada com sucesso!");
                break;

            case 3:
                System.out.print("Novo preço: ");
                String precoStr = scanner.nextLine();
                if (precoStr == null || precoStr.trim().isEmpty() || !precoStr.trim().matches("-?\\d+(\\.\\d+)?")) {//verifica se o preço é um número válido (inteiro ou decimal)
                    System.out.println("Preço inválido. Alteração cancelada.");
                    return;
                }
                produto.preco = Double.parseDouble(precoStr.trim());
                System.out.println("Preço alterado com sucesso!");
                break;

            case 4:
                System.out.print("Novo estoque: ");
                String estoqueStr = scanner.nextLine();
                if (estoqueStr == null || estoqueStr.trim().isEmpty() || !estoqueStr.trim().matches("-?\\d+")) {//verifica se o estoque é um número inteiro válido
                    System.out.println("Estoque inválido. Alteração cancelada.");
                    return;
                }
                produto.estoque = Integer.parseInt(estoqueStr.trim());
                System.out.println("Estoque alterado com sucesso!");
                break;

            case 5:
                System.out.print("Nova descrição: ");
                produto.descricao = scanner.nextLine();
                System.out.println("Descrição alterada com sucesso!");
                break;

            case 0:
                return;

            default:
                System.out.println("Opção inválida!");
        }
    }

    public static void filtrarProdutosPorMarca(Scanner scanner) {

        System.out.print("Marca a filtrar: ");
        String marca = scanner.nextLine();
        if (marca.trim().isEmpty()) {
            System.out.println("Marca não pode ficar em branco.");
            return;
        }

        System.out.println("\n--- Produtos da marca " + marca + " ---");
        boolean encontrou = false;
        for (int i = 0; i < totalProdutos; i++) {
            if (registrosProdutos[i].marca.equalsIgnoreCase(marca.trim())) {
                System.out.println(registrosProdutos[i]);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum produto encontrado para esta marca.");
        }
    }

    public static void filtrarProdutosPorFaixaPreco(Scanner scanner) {

        System.out.print("Preço mínimo: ");
        String minStr = scanner.nextLine();
        if (minStr == null || minStr.trim().isEmpty() || !minStr.trim().matches("-?\\d+(\\.\\d+)?")) {//verifica se o preço é um número válido (inteiro ou decimal)
            System.out.println("Preço mínimo inválido.");
            return;
        }
        double precoMin = Double.parseDouble(minStr.trim());

        System.out.print("Preço máximo: ");
        String maxStr = scanner.nextLine();
        if (maxStr == null || maxStr.trim().isEmpty() || !maxStr.trim().matches("-?\\d+(\\.\\d+)?")) {//verifica se o preço é um número válido (inteiro ou decimal)
            System.out.println("Preço máximo inválido.");
            return;
        }
        double precoMax = Double.parseDouble(maxStr.trim());

        if (precoMin > precoMax) {
            System.out.println("Preço mínimo não pode ser maior que o preço máximo.");
            return;
        }

        System.out.println("\n--- Produtos entre " + precoMin + " e " + precoMax + " ---");
        boolean encontrou = false;
        for (int i = 0; i < totalProdutos; i++) {
            if (registrosProdutos[i].preco >= precoMin && registrosProdutos[i].preco <= precoMax) {
                System.out.println(registrosProdutos[i]);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum produto encontrado nesta faixa de preço.");
        }
    }

    public static void filtrarProdutosPorTipo(Scanner scanner) {

        System.out.println("1 - Maquiagem");
        System.out.println("2 - Perfume");
        System.out.println("0 - Voltar");
        System.out.print("Escolha o tipo: ");

        String opcStr = scanner.nextLine();
        if (opcStr == null || opcStr.trim().isEmpty() || !opcStr.trim().matches("-?\\d+")) {//verifica se a entrada é um número inteiro
            System.out.println("Opção inválida!");
            return;
        }
        int opc = Integer.parseInt(opcStr.trim());

        if (opc == 0) {
            return;
        }
        if (opc != 1 && opc != 2) {
            System.out.println("Opção inválida!");
            return;
        }

        System.out.println("\n--- Produtos do tipo " + (opc == 1 ? "Maquiagem" : "Perfume") + " ---");
        boolean encontrou = false;
        for (int i = 0; i < totalProdutos; i++) {
            // Se o usuário escolheu 1 E o produto na posição atual do array for uma instância da classe Maquiagem
            if (opc == 1 && registrosProdutos[i] instanceof Maquiagem) {
                System.out.println(registrosProdutos[i]); // Imprime os dados do produto (chama o método toString da classe)
                encontrou = true;
                
            } else if (opc == 2 && registrosProdutos[i] instanceof Perfume) {
                System.out.println(registrosProdutos[i]); // Imprime os dados do produto (chama o método toString da classe)
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum produto encontrado deste tipo.");
        }
    }

    public static void filtrarProdutosPorEstoqueBaixo(Scanner scanner) {

        System.out.print("Mostrar produtos com estoque até: ");
        String limiteStr = scanner.nextLine();
        if (limiteStr == null || limiteStr.trim().isEmpty() || !limiteStr.trim().matches("-?\\d+")) {//verifica se o limite é um número inteiro válido
            System.out.println("Valor inválido.");
            return;
        }
        int limite = Integer.parseInt(limiteStr.trim());

        System.out.println("\n--- Produtos com estoque até " + limite + " ---");
        boolean encontrou = false;
        for (int i = 0; i < totalProdutos; i++) {
            if (registrosProdutos[i].estoque <= limite) {
                System.out.println(registrosProdutos[i]);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum produto encontrado com estoque nesta faixa.");
        }
    }

    public static void filtrarClientesPorCidade(Scanner scanner) {

        System.out.print("Cidade a filtrar: ");
        String cidade = scanner.nextLine();
        if (cidade.trim().isEmpty()) {
            System.out.println("Cidade não pode ficar em branco.");
            return;
        }

        System.out.println("\n--- Clientes da cidade " + cidade + " ---");
        boolean encontrou = false;
        for (int i = 0; i < totalClientes; i++) {
            if (registrosClientes[i].cidade.equalsIgnoreCase(cidade.trim())) {
                System.out.println(registrosClientes[i]);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum cliente encontrado nesta cidade.");
        }
    }

    public static void MenuFiltros(Scanner scanner) {

        System.out.println("\n====== MENU FILTROS ======");
        System.out.println("1 - Filtrar Produtos por Marca");
        System.out.println("2 - Filtrar Produtos por Faixa de Preço");
        System.out.println("3 - Filtrar Produtos por Tipo (Maquiagem/Perfume)");
        System.out.println("4 - Filtrar Produtos por Estoque Baixo");
        System.out.println("5 - Filtrar Clientes por Cidade");
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
                filtrarProdutosPorMarca(scanner);
                break;
            case 2:
                filtrarProdutosPorFaixaPreco(scanner);
                break;
            case 3:
                filtrarProdutosPorTipo(scanner);
                break;
            case 4:
                filtrarProdutosPorEstoqueBaixo(scanner);
                break;
            case 5:
                filtrarClientesPorCidade(scanner);
                break;

            case 0:
                return;

            default:
                System.out.println("Opção inválida!");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== LOJA DE COSMÉTICOS =====");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Menu Produtos");
            System.out.println("3 - Consultar Cliente");
            System.out.println("4 - Consultar Produto");
            System.out.println("5 - Alterar Cliente");
            System.out.println("6 - Alterar Produto");
            System.out.println("7 - Menu Filtros");
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
                    consultarCliente(scanner);
                    break;

                case 4:
                    consultarProduto(scanner);
                    break;

                case 5:
                    alterarCliente(scanner);
                    break;

                case 6:
                    alterarProduto(scanner);
                    break;

                case 7:
                    MenuFiltros(scanner);
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