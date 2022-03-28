package com.rota;
import io.micronaut.configuration.picocli.PicocliRunner;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "rota", description = "...",
        mixinStandardHelpOptions = true)
public class RotaCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(RotaCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
