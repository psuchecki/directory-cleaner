package com.orsastudio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ContentRemover {
    public void remove(ConfigFileInfo configFileInfo) {
        configFileInfo.getFileNames().stream().forEach(this::removeFile);
        configFileInfo.getDirNames().stream().forEach(this::removeDir);
    }

    private void removeDir(String dirName) {
        try {
            Files.walkFileTree(Paths.get(dirName), new RecursiveDirectoryVisitor());
        } catch (IOException e) {
            System.out.println("Failed to remove " + dirName);
        }
    }

    private void removeFile(String fileName) {
        try {
            Files.delete(Paths.get(fileName));
        } catch (IOException e) {
            System.out.println("Failed to remove " + fileName);
        }
    }

    private class RecursiveDirectoryVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            try {
                Files.delete(file);
            } catch (IOException e) {
                System.out.println("Failed to remove " + file);
            }

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            try {
                Files.delete(dir);
            } catch (IOException e) {
                System.out.println("Failed to remove " + dir);
            }

            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            try {
                Files.delete(file);
            } catch (IOException e) {
                System.out.println("Failed to remove " + file);
            }

            return FileVisitResult.CONTINUE;
        }
    }
}
