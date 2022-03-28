package com.rota;
import com.rota.entity.Chef;

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
        Chef c1 = new Chef(1l, "vik", "faszfej:)",10.5f,"84839220", 1l);
        System.out.println(c1);
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
