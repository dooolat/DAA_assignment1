package org.algos.metrics;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

/**
 * Маленький утиль для записи CSV: создание заголовка (если нет) и дописывание строк.
 */
public final class csvwriter {

    private csvwriter() {}

    public static void writeHeaderIfMissing(Path path, String header) throws IOException {
        if (Files.notExists(path)) {
            Path parent = path.getParent();
            if (parent != null && Files.notExists(parent)) {
                Files.createDirectories(parent);
            }
            Files.write(path, List.of(header), StandardCharsets.UTF_8);
            return;
        }
        // Если файл есть — проверим первую строку
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        if (lines.isEmpty() || !lines.get(0).equals(header)) {
            //prepend header
            List<String> newLines = new java.util.ArrayList<>();
            newLines.add(header);
            newLines.addAll(lines);
            Files.write(path, newLines, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    public static void appendLine(Path path, String line) throws IOException {
        Files.writeString(path, line + System.lineSeparator(), StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
