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
        Path path = Paths.get(fileName);
        removeFromPath(path);
    }

    private class RecursiveDirectoryVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            removeFromPath(file);

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            removeFromPath(dir);

            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            removeFromPath(file);

            return FileVisitResult.CONTINUE;
        }
    }

    private void removeFromPath(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.out.println("Failed to remove " + path);
        }
    }
}
