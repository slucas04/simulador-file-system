package simuladorfilesystem;

import java.util.*;
import java.time.LocalDateTime;

class File {
    private String name;
    private String type;
    private long size;
    private LocalDateTime createdAt;

    public File(String name, String type, long size) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.createdAt = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public long getSize() {
        return size;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

class Directory {
    private String name;
    private List<File> files = new ArrayList<>();
    private List<Directory> subdirectories = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<File> getFiles() {
        return files;
    }

    public List<Directory> getSubdirectories() {
        return subdirectories;
    }

    public void addFile(File file) throws IllegalArgumentException {
        if (files.stream().anyMatch(f -> f.getName().equals(file.getName()))) {
            throw new IllegalArgumentException("File with this name already exists.");
        }
        files.add(file);
    }

    public void removeFile(String fileName) {
        files.removeIf(f -> f.getName().equals(fileName));
    }

    public void addSubdirectory(Directory directory) throws IllegalArgumentException {
        if (subdirectories.stream().anyMatch(d -> d.getName().equals(directory.getName()))) {
            throw new IllegalArgumentException("Directory with this name already exists.");
        }
        subdirectories.add(directory);
    }

    public void removeSubdirectory(String dirName) {
        subdirectories.removeIf(d -> d.getName().equals(dirName));
    }


}

class Journal {
    private List<String> log = new ArrayList<>();

    public void log(String operation) {
        log.add(LocalDateTime.now() + " - " + operation);
    }

    public void printLog() {
        System.out.println("Journal Log:");
        for (String entry : log) {
            System.out.println(entry);
        }
    }

    public List<String> getLog() {
        return log;
    }
}

class FileSystemSimulator {
    private Directory root = new Directory("root");
    private Journal journal = new Journal();

    public void createDirectory(String path, String name) throws Exception {
        Directory parent = navigateToDirectory(path);
        if (parent != null) {
            Directory newDir = new Directory(name);
            parent.addSubdirectory(newDir);
            journal.log("Created directory: " + path + "/" + name);
        } else {
            throw new Exception("Directory not found: " + path);
        }
    }

    public void createFile(String path, String name, String type, long size) throws Exception {
        Directory parent = navigateToDirectory(path);
        if (parent != null) {
            File file = new File(name, type, size);
            parent.addFile(file);
            journal.log("Created file: " + path + "/" + name);
        } else {
            throw new Exception("Directory not found: " + path);
        }
    }

    public void removeFile(String path, String name) throws Exception {
        Directory dir = navigateToDirectory(path);
        if (dir != null) {
            dir.removeFile(name);
            journal.log("Removed file: " + path + "/" + name);
        } else {
            throw new Exception("Directory not found: " + path);
        }
    }

    public void copyFile(String sourcePath, String sourceFileName, String destPath) throws Exception {
        Directory sourceDir = navigateToDirectory(sourcePath);
        Directory destDir = navigateToDirectory(destPath);

        if (sourceDir == null || destDir == null) {
            throw new Exception("Source or destination directory not found.");
        }

        File sourceFile = sourceDir.getFiles().stream()
                .filter(f -> f.getName().equals(sourceFileName))
                .findFirst()
                .orElseThrow(() -> new Exception("File not found: " + sourceFileName));

        File copiedFile = new File(sourceFile.getName(), sourceFile.getType(), sourceFile.getSize());
        destDir.addFile(copiedFile);

        journal.log("Copied file: " + sourcePath + "/" + sourceFileName + " to " + destPath);
    }




    public void removeDirectory(String path) throws Exception {
        String parentPath = path.substring(0, path.lastIndexOf('/'));
        String dirName = path.substring(path.lastIndexOf('/') + 1);
        Directory parent = navigateToDirectory(parentPath);

        if (parent != null) {
            parent.removeSubdirectory(dirName);
            journal.log("Removed directory: " + path);
        } else {
            throw new Exception("Invalid path: " + path);
        }
    }

    public void renameDirectory(String path, String oldName, String newName) throws Exception {
        Directory parent = navigateToDirectory(path);

        if (parent == null) {
            throw new Exception("Parent directory not found: " + path);
        }

        Directory dirToRename = parent.getSubdirectories().stream()
                .filter(d -> d.getName().equals(oldName))
                .findFirst()
                .orElseThrow(() -> new Exception("Directory not found: " + oldName));

        if (parent.getSubdirectories().stream().anyMatch(d -> d.getName().equals(newName))) {
            throw new IllegalArgumentException("Directory with the new name already exists.");
        }

        dirToRename.setName(newName);
        journal.log("Renamed directory: " + oldName + " to " + newName);
    }


    public void renameFile(String path, String oldName, String newName) throws Exception {
        Directory dir = navigateToDirectory(path);
        if (dir != null) {
            File file = dir.getFiles().stream()
                    .filter(f -> f.getName().equals(oldName))
                    .findFirst()
                    .orElseThrow(() -> new Exception("File not found: " + oldName));

            file.setName(newName);
            journal.log("Renamed file: " + oldName + " to " + newName);
        } else {
            throw new Exception("Directory not found: " + path);
        }
    }

    public void listDirectory(String path) throws Exception {
        Directory dir = navigateToDirectory(path);
        if (dir != null) {
            System.out.println("Contents of directory: " + path);
            if (dir.getFiles().isEmpty() && dir.getSubdirectories().isEmpty()) {
                System.out.println("  [Empty directory]");
            } else {
                for (File file : dir.getFiles()) {
                    System.out.println("  [File] " + file.getName());
                }
                for (Directory subDir : dir.getSubdirectories()) {
                    System.out.println("  [Dir] " + subDir.getName());
                }
            }
        } else {
            throw new Exception("Directory not found: " + path);
        }
    }

    private Directory navigateToDirectory(String path) {
        String[] parts = path.split("/");
        Directory current = root;

        for (String part : parts) {
            if (part.isEmpty()) continue;

            Directory next = current.getSubdirectories().stream()
                    .filter(d -> d.getName().equals(part))
                    .findFirst().orElse(null);

            if (next == null) {
                return null;
            }
            current = next;
        }
        return current;
    }

    public void printJournalLog() {
        journal.printLog();
    }
}


