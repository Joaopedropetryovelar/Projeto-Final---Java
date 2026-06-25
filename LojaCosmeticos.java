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
        return "Codigo: " + id + " | Nome: " + nome + " | Telefone: " + telefone + " | Cidade: " + cidade;
    }
}

class Produto {

    double preco;
    int estoque;

    String nome;
    int id;
    String marca;
    String descricao;

    public Produto(String nome, int id, double preco, int estoque, String marca, String descricao) {
        this.nome = nome;
        this.id = id;
        this.preco = preco;
        this.estoque = estoque;
        this.marca = marca;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Codigo: " + id + " | Nome: " + nome + " | Marca: " + marca + " | Preço: R$" + String.format("%.2f", preco) + " | Estoque: " + estoque + " | Descrição: " + descricao;
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

class ItemVenda {

    Produto produto;
    int quantidade;
    double subtotal;

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = produto.preco * quantidade;
    }

    @Override
    public String toString() {
        return String.format("  %-25s | Qtd: %3d | Unit: R$%8.2f | Subtotal: R$%8.2f",
        produto.nome, quantidade, produto.preco, subtotal);
    }
}


class Venda {

    static int contadorId = 1;

    int id;
    Cliente cliente;
    ItemVenda[] itens;
    int totalItens;
    double totalVenda;
    String status; // "ABERTA", "FINALIZADA", "CANCELADA"

    public Venda(Cliente cliente) {
        this.id = contadorId++;
        this.cliente = cliente;
        this.itens = new ItemVenda[50];
        this.totalItens = 0;
        this.totalVenda = 0.0;
        this.status = "ABERTA";
    }

    public boolean adicionarItem(Produto produto, int quantidade) {
        if (totalItens >= itens.length) {
            System.out.println("Limite de itens por venda atingido.");
            return false;
        }
        if (quantidade > produto.estoque) {
            System.out.println("Estoque insuficiente! Disponível: " + produto.estoque);
            return false;
        }
        // Verifica se o produto já está na venda e soma a quantidade
        for (int i = 0; i < totalItens; i++) {
            if (itens[i].produto.id == produto.id) {
                int novaQtd = itens[i].quantidade + quantidade;
                if (novaQtd > produto.estoque) {
                    System.out.println("Estoque insuficiente! Disponível: " + produto.estoque + " | Já no carrinho: " + itens[i].quantidade);
                    return false;
                }
                itens[i].quantidade = novaQtd;
                itens[i].subtotal = produto.preco * novaQtd;
                calcularTotal();
                return true;
            }
        }
        itens[totalItens] = new ItemVenda(produto, quantidade);
        totalItens++;
        calcularTotal();
        return true;
    }

    public boolean removerItem(int produtoId) {
        for (int i = 0; i < totalItens; i++) {
            if (itens[i].produto.id == produtoId) {
                // Desloca os itens para preencher o espaço
                for (int j = i; j < totalItens - 1; j++) {
                    itens[j] = itens[j + 1];
                }
                itens[totalItens - 1] = null;
                totalItens--;
                calcularTotal();
                return true;
            }
        }
        return false;
    }

    private void calcularTotal() {
        totalVenda = 0.0;
        for (int i = 0; i < totalItens; i++) {
            totalVenda += itens[i].subtotal;
        }
    }

    public void imprimirCupom() {
        System.out.println("\n========================================");
        System.out.println("          LOJA DE COSMÉTICOS            ");
        System.out.println("========================================");
        System.out.println("Venda #" + id + " | Status: " + status);
        System.out.println("Cliente: " + cliente.nome + " (ID: " + cliente.id + ")");
        System.out.println("Cidade: " + cliente.cidade);
        System.out.println("----------------------------------------");
        System.out.printf("%-25s | %5s | %10s | %10s%n", "Produto", "Qtd", "Unit.", "Subtotal");
        System.out.println("----------------------------------------");
        for (int i = 0; i < totalItens; i++) {
            System.out.println(itens[i]);
        }
        System.out.println("========================================");
        System.out.printf("  TOTAL DA VENDA: R$ %.2f%n", totalVenda);
        System.out.println("========================================\n");
    }

    @Override
    public String toString() {
        return "Venda #" + id + " | Cliente: " + cliente.nome + " | Itens: " + totalItens + " | Total: R$" + String.format("%.2f", totalVenda) + " | Status: " + status;
    }
}

public class LojaCosmeticos {

    static Cliente[] registrosClientes = new Cliente[20];
    static Produto[] registrosProdutos = new Produto[20];
    static Venda[] registrosVendas = new Venda[100];

    static int totalClientes = 0;
    static int totalProdutos = 0;
    static int totalVendas = 0;


    public static int buscarClientePorId(int id) {
        for (int i = 0; i < totalClientes; i++) {
            if (registrosClientes[i].id == id) return i;
        }
        return -1;
    }

    public static int buscarProdutoPorId(int id) {
        for (int i = 0; i < totalProdutos; i++) {
            if (registrosProdutos[i].id == id) return i;
        }
        return -1;
    }

    public static int buscarVendaPorId(int id) {
        for (int i = 0; i < totalVendas; i++) {
            if (registrosVendas[i].id == id) return i;
        }
        return -1;
    }


    public static int contadorClienteId = 1;

public static void cadastrarCliente(Scanner scanner) {
    if (totalClientes >= registrosClientes.length) {
        System.out.println("Limite de clientes atingido.");
        return;
    }

    System.out.println("\n==== Cadastro do Cliente ====");

    int id = contadorClienteId++;

   System.out.print("Nome: ");
String nome = scanner.nextLine();

if (nome == null || nome.trim().isEmpty() || !nome.matches("^[\\p{L} ]+$")) {
    System.out.println("Nome inválido. Cadastro cancelado.");
    return;
}

System.out.print("Telefone: ");
String telefoneStr = scanner.nextLine();

if (telefoneStr == null || telefoneStr.trim().isEmpty() || !telefoneStr.trim().matches("\\d+")) {
    System.out.println("Telefone inválido. Cadastro cancelado.");
    return;
}

int telefone = Integer.parseInt(telefoneStr.trim());

System.out.print("Cidade: ");
String cidade = scanner.nextLine();

if (cidade == null || cidade.trim().isEmpty() || !cidade.matches("^[\\p{L} ]+$")) {//verifica se a cidade contém apenas letras e espaços e sem deixar o campo vazio
    System.out.println("Cidade inválida. Cadastro cancelado.");
    return;
}
    registrosClientes[totalClientes] = new Cliente(nome, id, telefone, cidade);
    totalClientes++;

    System.out.println("Cliente cadastrado com sucesso!");
    System.out.println("ID gerado automaticamente: " + id);
}

public static void listarClientes() {
    if (totalClientes == 0) {
        System.out.println("Nenhum cliente cadastrado.");
        return;
    }

    System.out.println("\n==== LISTA DE CLIENTES ====");

    for (int i = 0; i < totalClientes; i++) {
        System.out.println(registrosClientes[i]);
    }
}

    public static void cadastrarMaquiagem(Scanner scanner) {
        if (totalProdutos >= registrosProdutos.length) {
            System.out.println("Limite de produtos atingido.");
            return;
        }

        System.out.println("\n==== Cadastro de Maquiagem ====");

        System.out.print("ID: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {
            System.out.println("ID inválido. Cadastro cancelado.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());

        if (buscarProdutoPorId(id) != -1) {
            System.out.println("Já existe um produto com este ID. Cadastro cancelado.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty() || !nome.matches("^[\\p{L} ]+$")) {
            System.out.println("Nome inválido. Cadastro cancelado.");
            return;
        }

        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        if (marca.trim().isEmpty() || !marca.matches("^[\\p{L} ]+$")) {
            System.out.println("Marca inválida. Cadastro cancelado.");
            return;
        }

        System.out.print("Estoque: ");
        String estoqueStr = scanner.nextLine();
        if (estoqueStr == null || estoqueStr.trim().isEmpty() || !estoqueStr.trim().matches("\\d+") || Integer.parseInt(estoqueStr.trim()) <= 0) {
            System.out.println("Estoque inválido. Cadastro cancelado.");
            return;
        }
        int estoque = Integer.parseInt(estoqueStr.trim());

        System.out.print("Preço: ");
        String precoStr = scanner.nextLine();
        if (precoStr == null || precoStr.trim().isEmpty() || !precoStr.trim().matches("\\d+(\\.\\d+)?")||Double.parseDouble(precoStr.trim()) <= 0) {
            System.out.println("Preço inválido. Cadastro cancelado.");
            return;
        }
        double preco = Double.parseDouble(precoStr.trim());

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        registrosProdutos[totalProdutos] = new Maquiagem(id, nome, marca, preco, estoque, descricao);
        totalProdutos++;
        System.out.println("Maquiagem cadastrada com sucesso!");
    }

    public static void cadastrarPerfume(Scanner scanner) {
        if (totalProdutos >= registrosProdutos.length) {
            System.out.println("Limite de produtos atingido.");
            return;
        }

        System.out.println("\n==== Cadastro de Perfume ====");

        System.out.print("ID: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {
            System.out.println("ID inválido. Cadastro cancelado.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());

        if (buscarProdutoPorId(id) != -1) {
            System.out.println("Já existe um produto com este ID. Cadastro cancelado.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty() || !nome.matches("^[\\p{L} ]+$")) {
            System.out.println("Nome inválido. Cadastro cancelado.");
            return;
        }

        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        if (marca.trim().isEmpty() || !marca.matches("^[\\p{L} ]+$")) {
            System.out.println("Marca inválida. Cadastro cancelado.");
            return;
        }

        System.out.print("Estoque: ");
        String estoqueStr = scanner.nextLine();
        if (estoqueStr == null || estoqueStr.trim().isEmpty() || !estoqueStr.trim().matches("\\d+") || Integer.parseInt(estoqueStr.trim()) <= 0) {
            System.out.println("Estoque inválido. Cadastro cancelado.");
            return;
        }
        int estoque = Integer.parseInt(estoqueStr.trim());

        System.out.print("Preço: ");
        String precoStr = scanner.nextLine();
        if (precoStr == null || precoStr.trim().isEmpty() || !precoStr.trim().matches("\\d+(\\.\\d+)?")||Double.parseDouble(precoStr.trim()) <= 0) {
            System.out.println("Preço inválido. Cadastro cancelado.");
            return;
        }
        double preco = Double.parseDouble(precoStr.trim());

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        registrosProdutos[totalProdutos] = new Perfume(id, nome, marca, preco, estoque, descricao);
        totalProdutos++;
        System.out.println("Perfume cadastrado com sucesso!");
    }


    public static void abrirVenda(Scanner scanner) {
        if (totalVendas >= registrosVendas.length) {
            System.out.println("Limite de vendas atingido.");
            return;
        }
        if (totalClientes == 0) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            return;
        }
        if (totalProdutos == 0) {
            System.out.println("Nenhum produto cadastrado. Cadastre produtos primeiro.");
            return;
        }

        System.out.println("\n==== Nova Venda ====");
        System.out.print("ID do cliente: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {
            System.out.println("ID inválido.");
            return;
        }
        int idCliente = Integer.parseInt(idStr.trim());

        int indiceCliente = buscarClientePorId(idCliente);
        if (indiceCliente == -1) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Venda venda = new Venda(registrosClientes[indiceCliente]);
        registrosVendas[totalVendas] = venda;
        totalVendas++;

        System.out.println("Venda #" + venda.id + " aberta para " + venda.cliente.nome + "!");

        // Loop de adição de itens
        boolean continuarAdicionando = true;
        while (continuarAdicionando) {
            System.out.println("\n--- Produtos disponíveis ---");
            listarProdutos();

            System.out.println("\n1 - Adicionar produto");
            System.out.println("2 - Remover produto");
            System.out.println("3 - Ver carrinho");
            System.out.println("4 - Finalizar venda");
            System.out.println("0 - Cancelar venda");
            System.out.print("Escolha: ");

            String opcStr = scanner.nextLine();
            if (opcStr == null || opcStr.trim().isEmpty() || !opcStr.trim().matches("-?\\d+")) {
                System.out.println("Opção inválida!");
                continue;
            }
            int opc = Integer.parseInt(opcStr.trim());

            switch (opc) {
                case 1:
                    adicionarItemVenda(scanner, venda);
                    break;

                case 2:
                    removerItemVenda(scanner, venda);
                    break;

                case 3:
                    venda.imprimirCupom();
                    break;

                case 4:
                    if (venda.totalItens == 0) {
                        System.out.println("Carrinho vazio! Adicione pelo menos um produto.");
                    } else {
                        finalizarVenda(venda);
                        continuarAdicionando = false;
                    }
                    break;

                case 0:
                    cancelarVenda(venda);
                    continuarAdicionando = false;
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void adicionarItemVenda(Scanner scanner, Venda venda) {
        System.out.print("ID do produto: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {
            System.out.println("ID inválido.");
            return;
        }
        int idProduto = Integer.parseInt(idStr.trim());

        int indiceProduto = buscarProdutoPorId(idProduto);
        if (indiceProduto == -1) {
            System.out.println("Produto não encontrado.");
            return;
        }

        Produto produto = registrosProdutos[indiceProduto];
        if (produto.estoque == 0) {
            System.out.println("Produto sem estoque!");
            return;
        }

        System.out.println("Produto: " + produto.nome + " | Preço: R$" + String.format("%.2f", produto.preco) + " | Estoque: " + produto.estoque);
        System.out.print("Quantidade: ");
        String qtdStr = scanner.nextLine();
        if (qtdStr == null || qtdStr.trim().isEmpty() || !qtdStr.trim().matches("\\d+")) {
            System.out.println("Quantidade inválida.");
            return;
        }
        int quantidade = Integer.parseInt(qtdStr.trim());
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero.");
            return;
        }

        if (venda.adicionarItem(produto, quantidade)) {
            System.out.printf("Adicionado: %dx %s | Subtotal: R$%.2f%n", quantidade, produto.nome, produto.preco * quantidade);
            System.out.printf("Total atual: R$%.2f%n", venda.totalVenda);
        }
    }

    private static void removerItemVenda(Scanner scanner, Venda venda) {
        if (venda.totalItens == 0) {
            System.out.println("Carrinho vazio.");
            return;
        }
        System.out.println("Itens no carrinho:");
        for (int i = 0; i < venda.totalItens; i++) {
            System.out.println("  [" + venda.itens[i].produto.id + "] " + venda.itens[i].produto.nome + " x" + venda.itens[i].quantidade);
        }
        System.out.print("ID do produto a remover: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {
            System.out.println("ID inválido.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());

        if (venda.removerItem(id)) {
            System.out.println("Item removido com sucesso!");
            System.out.printf("Total atual: R$%.2f%n", venda.totalVenda);
        } else {
            System.out.println("Produto não encontrado no carrinho.");
        }
    }

    private static void finalizarVenda(Venda venda) {
        // Desconta o estoque dos produtos
        for (int i = 0; i < venda.totalItens; i++) {
            venda.itens[i].produto.estoque -= venda.itens[i].quantidade;
        }
        venda.status = "FINALIZADA";
        System.out.println("\nVenda finalizada com sucesso!");
        venda.imprimirCupom();
    }

    private static void cancelarVenda(Venda venda) {
        venda.status = "CANCELADA";
        System.out.println("Venda #" + venda.id + " cancelada.");
    }

    public static void consultarVenda(Scanner scanner) {
        System.out.println("\n==== Consultar Venda ====");
        System.out.print("ID da venda: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {
            System.out.println("ID inválido.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());

        int indice = buscarVendaPorId(id);
        if (indice == -1) {
            System.out.println("Venda não encontrada.");
            return;
        }

        registrosVendas[indice].imprimirCupom();
    }

    public static void listarVendas() {
        if (totalVendas == 0) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }
        System.out.println("\n==== Lista de Vendas ====");
        for (int i = 0; i < totalVendas; i++) {
            System.out.println(registrosVendas[i]);
        }
    }

    public static void listarVendasPorCliente(Scanner scanner) {
        System.out.print("ID do cliente: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {
            System.out.println("ID inválido.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());

        int indiceCliente = buscarClientePorId(id);
        if (indiceCliente == -1) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Cliente cliente = registrosClientes[indiceCliente];
        System.out.println("\n==== Vendas do cliente: " + cliente.nome + " ====");
        boolean encontrou = false;
        double totalGasto = 0;

        for (int i = 0; i < totalVendas; i++) {
            if (registrosVendas[i].cliente.id == id) {
                System.out.println(registrosVendas[i]);
                if (registrosVendas[i].status.equals("FINALIZADA")) {
                    totalGasto += registrosVendas[i].totalVenda;
                }
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma venda encontrada para este cliente.");
        } else {
            System.out.printf("Total gasto (finalizadas): R$%.2f%n", totalGasto);
        }
    }

    public static void relatorioVendas() {
        System.out.println("\n========== RELATÓRIO DE VENDAS ==========");
        int finalizadas = 0, canceladas = 0, abertas = 0;
        double totalReceita = 0;

        for (int i = 0; i < totalVendas; i++) {
            Venda v = registrosVendas[i];
            switch (v.status) {
                case "FINALIZADA": finalizadas++; totalReceita += v.totalVenda; break;
                case "CANCELADA":  canceladas++;  break;
                case "ABERTA":     abertas++;     break;
            }
        }

        System.out.println("Total de vendas registradas : " + totalVendas);
        System.out.println("  Finalizadas               : " + finalizadas);
        System.out.println("  Canceladas                : " + canceladas);
        System.out.println("  Em aberto                 : " + abertas);
        System.out.printf( "  Receita total (finalizadas): R$%.2f%n", totalReceita);
        System.out.println("=========================================");
    }

    public static void listarProdutos() {
        if (totalProdutos == 0) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (int i = 0; i < totalProdutos; i++) {
            String tipo = (registrosProdutos[i] instanceof Maquiagem) ? "[Maquiagem]" : "[Perfume]  ";
            System.out.println("  " + tipo + " " + registrosProdutos[i]);
        }
    }


    public static void MenuProduto(Scanner scanner) {
        System.out.println("\n====== MENU PRODUTO ======");
        System.out.println("1 - Cadastrar Maquiagem");
        System.out.println("2 - Cadastrar Perfume");
        System.out.println("3 - Listar todos os produtos");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");

        String opcStr = scanner.nextLine();
        if (opcStr == null || opcStr.trim().isEmpty() || !opcStr.trim().matches("-?\\d+")) {
            System.out.println("Opção inválida!");
            return;
        }
        int opc = Integer.parseInt(opcStr.trim());

        switch (opc) {
            case 1: cadastrarMaquiagem(scanner); 
            break;
            case 2: cadastrarPerfume(scanner);
            break;
            case 3: listarProdutos();
            break;
            case 0: return;
            default: System.out.println("Opção inválida!");
        }
    }

    public static void consultarCliente(Scanner scanner) {
        System.out.println("\n==== Consultar Cliente ====");
        System.out.print("ID do cliente: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {
            System.out.println("ID inválido.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());
        int indice = buscarClientePorId(id);
        if (indice == -1) { System.out.println("Cliente não encontrado."); return; }
        System.out.println(registrosClientes[indice]);
    }

    public static void consultarProduto(Scanner scanner) {
        System.out.println("\n==== Consultar Produto ====");
        System.out.print("ID do produto: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {
            System.out.println("ID inválido.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());
        int indice = buscarProdutoPorId(id);
        if (indice == -1) { System.out.println("Produto não encontrado."); return; }
        System.out.println(registrosProdutos[indice]);
    }

    public static void alterarCliente(Scanner scanner) {
        System.out.println("\n==== Alterar Cliente ====");
        System.out.print("ID do cliente: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {
            System.out.println("ID inválido.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());
        int indice = buscarClientePorId(id);
        if (indice == -1) { System.out.println("Cliente não encontrado."); return; }

        Cliente cliente = registrosClientes[indice];
        System.out.println("Dados atuais -> " + cliente);
        System.out.println("1 - Nome\n2 - Telefone\n3 - Cidade\n0 - Voltar");
        System.out.print("O que deseja alterar? ");

        String opcStr = scanner.nextLine();
        if (opcStr == null || opcStr.trim().isEmpty() || !opcStr.trim().matches("-?\\d+")) {
            System.out.println("Opção inválida!");
            return;
        }
        int opc = Integer.parseInt(opcStr.trim());

        switch (opc) {
            case 1:
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                if (nome.trim().isEmpty() || !nome.matches("^[\\p{L} ]+$")) { System.out.println("Nome inválido."); return; }
                cliente.nome = nome;
                System.out.println("Nome alterado com sucesso!");
                break;
            case 2:
                System.out.print("Novo telefone: ");
                String telStr = scanner.nextLine();
                if (telStr == null || telStr.trim().isEmpty() || !telStr.trim().matches("-?\\d+")) { System.out.println("Telefone inválido."); return; }
                cliente.telefone = Integer.parseInt(telStr.trim());
                System.out.println("Telefone alterado com sucesso!");
                break;
            case 3:
                System.out.print("Nova cidade: ");
                String cidade = scanner.nextLine();
                if (cidade.trim().isEmpty()) { System.out.println("Cidade não pode ficar em branco."); return; }
                cliente.cidade = cidade;
                System.out.println("Cidade alterada com sucesso!");
                break;
            case 0: return;
            default: System.out.println("Opção inválida!");
        }
    }

    public static void alterarProduto(Scanner scanner) {
        System.out.println("\n==== Alterar Produto ====");
        System.out.print("ID do produto: ");
        String idStr = scanner.nextLine();
        if (idStr == null || idStr.trim().isEmpty() || !idStr.trim().matches("-?\\d+")) {
            System.out.println("ID inválido.");
            return;
        }
        int id = Integer.parseInt(idStr.trim());
        int indice = buscarProdutoPorId(id);
        if (indice == -1) { System.out.println("Produto não encontrado."); return; }

        Produto produto = registrosProdutos[indice];
        System.out.println("Dados atuais -> " + produto);
        System.out.println("1 - Nome\n2 - Marca\n3 - Preço\n4 - Estoque\n5 - Descrição\n0 - Voltar");
        System.out.print("O que deseja alterar? ");

        String opcStr = scanner.nextLine();
        if (opcStr == null || opcStr.trim().isEmpty() || !opcStr.trim().matches("-?\\d+")) {
            System.out.println("Opção inválida!");
            return;
        }
        int opc = Integer.parseInt(opcStr.trim());

        switch (opc) {
            case 1:
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                if (nome.trim().isEmpty() || !nome.matches("^[\\p{L} ]+$")) { System.out.println("Nome inválido."); return; }
                produto.nome = nome; System.out.println("Nome alterado!");
                break;
            case 2:
                System.out.print("Nova marca: ");
                String marca = scanner.nextLine();
                if (marca.trim().isEmpty()) { System.out.println("Marca não pode ficar em branco."); return; }
                produto.marca = marca; System.out.println("Marca alterada!");
                break;
            case 3:
                System.out.print("Novo preço: ");
                String precoStr = scanner.nextLine();
                if (precoStr == null || precoStr.trim().isEmpty() || !precoStr.trim().matches("\\d+(\\.\\d+)?")) { System.out.println("Preço inválido.");
                return; }
                produto.preco = Double.parseDouble(precoStr.trim()); System.out.println("Preço alterado!");
                break;
            case 4:
                System.out.print("Novo estoque: ");
                String estoqueStr = scanner.nextLine();
                if (estoqueStr == null || estoqueStr.trim().isEmpty() || !estoqueStr.trim().matches("\\d+")) { System.out.println("Estoque inválido."); return; }
                produto.estoque = Integer.parseInt(estoqueStr.trim()); System.out.println("Estoque alterado!");
                break;
            case 5:
                System.out.print("Nova descrição: ");
                produto.descricao = scanner.nextLine(); System.out.println("Descrição alterada!");
                break;
            case 0: return;
            default: System.out.println("Opção inválida!");
        }
    }

    public static void filtrarProdutosPorMarca(Scanner scanner) {
        System.out.print("Marca a filtrar: ");
        String marca = scanner.nextLine();
        if (marca.trim().isEmpty()) { System.out.println("Marca não pode ficar em branco."); return; }

        System.out.println("\n--- Produtos da marca " + marca + " ---");
        boolean encontrou = false;
        for (int i = 0; i < totalProdutos; i++) {
            if (registrosProdutos[i].marca.equalsIgnoreCase(marca.trim())) {
                System.out.println(registrosProdutos[i]);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum produto encontrado para esta marca.");
    }

    public static void filtrarProdutosPorFaixaPreco(Scanner scanner) {
        System.out.print("Preço mínimo: ");
        String minStr = scanner.nextLine();
        if (minStr == null || minStr.trim().isEmpty() || !minStr.trim().matches("\\d+(\\.\\d+)?")) 
        { System.out.println("Valor inválido."); return; }
        double precoMin = Double.parseDouble(minStr.trim());

        System.out.print("Preço máximo: ");
        String maxStr = scanner.nextLine();
        if (maxStr == null || maxStr.trim().isEmpty() || !maxStr.trim().matches("\\d+(\\.\\d+)?"))
        { System.out.println("Valor inválido."); return; }
        double precoMax = Double.parseDouble(maxStr.trim());

        if (precoMin > precoMax) { System.out.println("Preço mínimo não pode ser maior que o máximo."); return; }

        System.out.println("\n--- Produtos entre R$" + precoMin + " e R$" + precoMax + " ---");
        boolean encontrou = false;
        for (int i = 0; i < totalProdutos; i++) {
            if (registrosProdutos[i].preco >= precoMin && registrosProdutos[i].preco <= precoMax) {
                System.out.println(registrosProdutos[i]);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum produto nesta faixa de preço.");
    }

    public static void filtrarProdutosPorTipo(Scanner scanner) {
        System.out.println("1 - Maquiagem\n2 - Perfume\n0 - Voltar");
        System.out.print("Escolha o tipo: ");
        String opcStr = scanner.nextLine();
        if (opcStr == null || opcStr.trim().isEmpty() || !opcStr.trim().matches("-?\\d+")) 
        { System.out.println("Opção inválida!"); return; }
        int opc = Integer.parseInt(opcStr.trim());
        if (opc == 0) return;
        if (opc != 1 && opc != 2) { System.out.println("Opção inválida!"); return; }

        System.out.println("\n--- Produtos do tipo " + (opc == 1 ? "Maquiagem" : "Perfume") + " ---");
        boolean encontrou = false;
        for (int i = 0; i < totalProdutos; i++) {
            if ((opc == 1 && registrosProdutos[i] instanceof Maquiagem) || (opc == 2 && registrosProdutos[i] instanceof Perfume)) {
                System.out.println(registrosProdutos[i]);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum produto encontrado deste tipo.");
    }

    public static void filtrarProdutosPorEstoqueBaixo(Scanner scanner) {
        System.out.print("Mostrar produtos com estoque até: ");
        String limiteStr = scanner.nextLine();
        if (limiteStr == null || limiteStr.trim().isEmpty() || !limiteStr.trim().matches("\\d+"))
        { System.out.println("Valor inválido."); return; }
        int limite = Integer.parseInt(limiteStr.trim());

        System.out.println("\n--- Produtos com estoque até " + limite + " ---");
        boolean encontrou = false;
        for (int i = 0; i < totalProdutos; i++) {
            if (registrosProdutos[i].estoque <= limite) {
                System.out.println(registrosProdutos[i]);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum produto nesta faixa de estoque.");
    }

    public static void filtrarClientesPorCidade(Scanner scanner) {
        System.out.print("Cidade a filtrar: ");
        String cidade = scanner.nextLine();
        if (cidade.trim().isEmpty()) { System.out.println("Cidade não pode ficar em branco."); return; }

        System.out.println("\n--- Clientes da cidade " + cidade + " ---");
        boolean encontrou = false;
        for (int i = 0; i < totalClientes; i++) {
            if (registrosClientes[i].cidade.equalsIgnoreCase(cidade.trim())) {
                System.out.println(registrosClientes[i]);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum cliente encontrado nesta cidade.");
    }

    public static void MenuFiltros(Scanner scanner) {
        System.out.println("\n====== MENU FILTROS ======");
        System.out.println("1 - Filtrar Produtos por Marca");
        System.out.println("2 - Filtrar Produtos por Faixa de Preço");
        System.out.println("3 - Filtrar Produtos por Tipo");
        System.out.println("4 - Filtrar Produtos por Estoque Baixo");
        System.out.println("5 - Filtrar Clientes por Cidade");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");

        String opcStr = scanner.nextLine();
        if (opcStr == null || opcStr.trim().isEmpty() || !opcStr.trim().matches("-?\\d+")) {
            System.out.println("Opção inválida!");
            return;
        }
        int opc = Integer.parseInt(opcStr.trim());

        switch (opc) {
            case 1: filtrarProdutosPorMarca(scanner);
            break;

            case 2: filtrarProdutosPorFaixaPreco(scanner);
            break;

            case 3: filtrarProdutosPorTipo(scanner);
            break;

            case 4: filtrarProdutosPorEstoqueBaixo(scanner);
            break;

            case 5: filtrarClientesPorCidade(scanner);
             break;

            case 0: return;
            default: System.out.println("Opção inválida!");
        }
    }


    public static void MenuVendas(Scanner scanner) {
        System.out.println("\n====== MENU VENDAS ======");
        System.out.println("1 - Nova Venda");
        System.out.println("2 - Consultar Venda por ID");
        System.out.println("3 - Listar todas as Vendas");
        System.out.println("4 - Vendas por Cliente");
        System.out.println("5 - Relatório de Vendas");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");

        String opcStr = scanner.nextLine();
        if (opcStr == null || opcStr.trim().isEmpty() || !opcStr.trim().matches("-?\\d+")) {
            System.out.println("Opção inválida!");
            return;
        }
        int opc = Integer.parseInt(opcStr.trim());

        switch (opc) {
            case 1: abrirVenda(scanner); 
            break;  

            case 2: consultarVenda(scanner); 
            break;

            case 3: listarVendas();
            break;

            case 4: listarVendasPorCliente(scanner);
            break;

            case 5: relatorioVendas();
            break;

            case 0: return;
            default: System.out.println("Opção inválida!");
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== LOJA DE COSMÉTICOS =====");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2- Listar Clientes");
            System.out.println("3 - Menu Produtos");
            System.out.println("4 - Consultar Cliente");
            System.out.println("5 - Consultar Produto");
            System.out.println("6 - Alterar Cliente");
            System.out.println("7 - Alterar Produto");
            System.out.println("8 - Menu Filtros");
            System.out.println("9 - Menu Vendas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            String opcaoStr = scanner.nextLine();
            if (opcaoStr == null || opcaoStr.trim().isEmpty() || !opcaoStr.trim().matches("-?\\d+")) {
                System.out.println("Opção inválida!");
                continue;
            }
            int opcao = Integer.parseInt(opcaoStr.trim());

            switch (opcao) {
                case 1: cadastrarCliente(scanner); 
                break;

                case 2:listarClientes();
                break;

                case 3: MenuProduto(scanner);
                break;

                case 4: consultarCliente(scanner);
                break;

                case 5: consultarProduto(scanner);
                break;

                case 6: alterarCliente(scanner);
                break;

                case 7: alterarProduto(scanner);
                break;

                case 8: MenuFiltros(scanner);
                break;

                case 9: MenuVendas(scanner);
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