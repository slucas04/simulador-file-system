package simuladorfilesystem;

import java.util.Scanner;

public class Shell {

    private FileSystemSimulator simulator;
    private Scanner scanner;

    public Shell() {
        this.simulator = new FileSystemSimulator();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the File System Shell!");
        System.out.println("Type 'help' for a list of commands.");

        while (true) {
            System.out.print("fs> ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String command = parts[0];

            try {
                switch (command) {
                    case "mkdir":
                        if (parts.length < 3) {
                            System.out.println("Usage: mkdir <path> <name>");
                        } else {
                            simulator.createDirectory(parts[1], parts[2]);
                            System.out.println("Directory created.");
                        }
                        break;

                    case "mkfile":
                        if (parts.length < 5) {
                            System.out.println("Usage: mkfile <path> <name> <type> <size>");
                        } else {
                            String path = parts[1];
                            String name = parts[2];
                            String type = parts[3];
                            long size = Long.parseLong(parts[4]);
                            simulator.createFile(path, name, type, size);
                            System.out.println("File created.");
                        }
                        break;

                    case "ls":
                        if (parts.length < 2) {
                            System.out.println("Usage: ls <path>");
                        } else {
                            simulator.listDirectory(parts[1]);
                        }
                        break;

                    case "rmfile":
                        if (parts.length < 3) {
                            System.out.println("Usage: rmfile <path> <name>");
                        } else {
                            simulator.removeFile(parts[1], parts[2]);
                            System.out.println("File removed.");
                        }
                        break;

                    case "rmdir":
                        if (parts.length < 2) {
                            System.out.println("Usage: rmdir <path>");
                        } else {
                            simulator.removeDirectory(parts[1]);
                            System.out.println("Directory removed.");
                        }
                        break;

                    case "rename":
                        if (parts.length < 4) {
                            System.out.println("Usage: rename <path> <oldName> <newName>");
                        } else {
                            simulator.renameFile(parts[1], parts[2], parts[3]);
                            System.out.println("File renamed.");
                        }
                        break;

                    case "renamedir":
                        if (parts.length < 4) {
                            System.out.println("Usage: renamedir <path> <oldName> <newName>");
                        } else {
                            simulator.renameDirectory(parts[1], parts[2], parts[3]);
                            System.out.println("Directory renamed.");
                        }
                        break;

                    case "cpfile":
                        if (parts.length < 4) {
                            System.out.println("Usage: cpfile <sourcePath> <fileName> <destinationPath>");
                        } else {
                            simulator.copyFile(parts[1], parts[2], parts[3]);
                            System.out.println("File copied.");
                        }
                        break;

                    case "journal":
                        simulator.printJournalLog();
                        break;

                    case "exit":
                        System.out.println("Exiting File System Shell. Goodbye!");
                        return;

                    case "help":
                        printHelp();
                        break;

                    default:
                        System.out.println("Unknown command. Type 'help' for a list of commands.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("  mkdir <path> <name>          - Create a new directory");
        System.out.println("  mkfile <path> <name> <type> <size> - Create a new file");
        System.out.println("  ls <path>                    - List the contents of a directory");
        System.out.println("  rmfile <path> <name>         - Remove a file");
        System.out.println("  rmdir <path>                 - Remove a directory");
        System.out.println("  rename <path> <oldName> <newName>  - Rename a file");
        System.out.println("  renamedir <path> <oldName> <newName> - Rename a directory");
        System.out.println("  cpfile <sourcePath> <fileName> <destinationPath> - Copy a file");
        System.out.println("  journal                      - Print the journaling log");
        System.out.println("  exit                         - Exit the shell");
        System.out.println("  help                         - Show this help message");
    }


}
