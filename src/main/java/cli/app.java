package cli;

import org.algos.metrics.csvwriter;
import org.algos.metrics.timer;

import java.nio.file.Path;
import java.util.Random;

public class app {
    public static void main(String[] args) {
        // parse args manually (default values)
        String algo = "mergesort";
        int n = 1000;
        String out = "result.csv";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--algo" -> algo = args[++i];
                case "--n" -> n = Integer.parseInt(args[++i]);
                case "--out" -> out = args[++i];
            }
        }

        // generate random array
        int[] a = new int[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++) a[i] = rnd.nextInt();

        // measure and run (stub for now)
        timer t = new timer();
        t.start();

        if (algo.equals("mergesort")) {
            // call mergesort here
        } else if (algo.equals("quicksort")) {
            // call quicksort here
        } else if (algo.equals("select")) {
            // call select here
        } else if (algo.equals("closest")) {
            // call closest pair here
        }

        t.stop();

        // emit metrics
        try {
            Path outPath = Path.of(out);
            csvwriter.writeHeaderIfMissing(outPath, "algo,n,timeMillis");
            csvwriter.appendLine(outPath,
                    algo + "," + n + "," + t.elapsed());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("done: " + out);
    }
}
