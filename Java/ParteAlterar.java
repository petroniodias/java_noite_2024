public static void consultarProduto(Scanner sc, List<Produto> produtos) {
    System.out.print("Digite o nome do produto: ");
    String nome = sc.nextLine();
    boolean encontrado = false;
    for (Produto p : produtos) {
        if (p.getNome().equalsIgnoreCase(nome)) {
            System.out.println("Nome: " + p.getNome());
            System.out.println("Unidade: " + p.getUnidade());
            System.out.println("Quantidade: " + p.getQuantidade());
            System.out.println("Preço: " + p.getPreco());

            System.out.print("Deseja alterar algo? (S/N): ");
            String resposta = sc.nextLine().toUpperCase();
            if (resposta.equals("S")) {
                System.out.print("O que deseja alterar? (quantidade, preco, unidade): ");
                String campo = sc.nextLine();
                switch (campo) {
                    case "quantidade":
                        System.out.print("Digite a nova quantidade: ");
                        int novaQtde = sc.nextInt();
                        p.setQuantidade(novaQtde);
                        System.out.println("Quantidade alterada com sucesso!");
                        break;
                    case "preco":
                        System.out.print("Digite o novo preço: ");
                        double novoPreco = sc.nextDouble();
                        p.setPreco(novoPreco);
                        System.out.println("Preço alterado com sucesso!");
                        break;
                    case "unidade":
                        System.out.print("Digite a nova unidade: ");
                        String novaUnidade = sc.nextLine();
                        p.setUnidade(novaUnidade);
                        System.out.println("Unidade alterada com sucesso!");
                        break;
                    default:
                        System.out.println("Campo inválido.");
                }
            }
            encontrado = true;
            break;
        }
    }
    if (!encontrado) {
        System.out.println("Produto não encontrado.");
    }
}
