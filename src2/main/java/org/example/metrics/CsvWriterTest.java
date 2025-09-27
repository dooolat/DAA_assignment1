package org.example.metrics;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvWriterTest {

    @Test
    void headerAndAppendWork() throws Exception {
        Path tmp = Files.createTempFile("metrics_test", ".csv");
        tmp.toFile().deleteOnExit();

        String header = Metrics.csvHeader();
        CsvWriter.writeHeaderIfMissing(tmp, header);

        Metrics m = new Metrics();
        m.startTimer();
        m.incComparisons();
        m.addAllocations(3);
        m.stopTimer();

        CsvWriter.appendLine(tmp, m.toCsvLine(10));

        List<String> lines = Files.readAllLines(tmp);
        assertTrue(lines.size() >= 2);
        assertEquals(header, lines.get(0));
    }
}
