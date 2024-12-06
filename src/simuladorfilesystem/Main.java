package simuladorfilesystem;

public class Main {

    public static void main(String[] args) {
        System.out.println("Escolha o modo de execução:");
        System.out.println("1. Testar funcionalidades do FileSystemSimulator");
        System.out.println("2. Iniciar modo Shell");
        System.out.print("Digite sua escolha (1 ou 2): ");

        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                testarFileSystemSimulator();
            } else if (choice == 2) {
                iniciarShell();
            } else {
                System.out.println("Escolha inválida. Encerrando programa.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void testarFileSystemSimulator() {
        System.out.println("Testando funcionalidades do FileSystemSimulator...");

        FileSystemSimulator simulator = new FileSystemSimulator();

        try {
            System.out.println("\n== Criando Diretórios ==");
            simulator.createDirectory("/", "dir1");
            simulator.createDirectory("/dir1", "dir2");
            simulator.listDirectory("/"); // Verificar criação

            System.out.println("\n== Criando Arquivos ==");
            simulator.createFile("/dir1", "file1.txt", "txt", 120);
            simulator.createFile("/dir1/dir2", "file2.txt", "txt", 300);
            simulator.listDirectory("/dir1");

            System.out.println("\n== Renomeando Arquivo ==");
            simulator.renameFile("/dir1", "file1.txt", "file1-renamed.txt");
            simulator.listDirectory("/dir1");

            System.out.println("\n== Renomeando Diretório ==");
            simulator.renameDirectory("/dir1", "dir2", "renamed-dir");
            simulator.listDirectory("/dir1");

            System.out.println("\n== Copiando Arquivo ==");
            // Verifique se o caminho de origem está correto
            simulator.copyFile("/dir1", "file1-renamed.txt", "/dir1/renamed-dir");
            simulator.listDirectory("/dir1/renamed-dir"); // Verifique se o arquivo foi copiado corretamente

            System.out.println("\n== Removendo Arquivo ==");
            simulator.removeFile("/dir1/renamed-dir", "file1-renamed.txt");
            simulator.listDirectory("/dir1/renamed-dir");

            System.out.println("\n== Removendo Diretório ==");
            simulator.removeDirectory("/dir1/renamed-dir");
            simulator.listDirectory("/dir1");

            System.out.println("\n== Exibindo Journal ==");
            simulator.printJournalLog();

        } catch (Exception e) {
            System.out.println("Erro durante os testes: " + e.getMessage());
        }


        System.out.println("Testes finalizados.");
    }


    private static void iniciarShell() {
        System.out.println("Iniciando o modo Shell...");
        Shell shell = new Shell();
        shell.start();
    }
}
