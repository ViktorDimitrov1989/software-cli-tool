package org.generator;

import com.beust.jcommander.JCommander;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.generator.commands.GenerateImagesCommandGroup;

@Slf4j
public class ImageGeneratorCli {

    public static void main(String ... args) throws Exception {
        log.info("Running the main method");

        val root = new ImageGeneratorCli();
        val commander = new JCommander(root);
        commander.setProgramName("software-cli-image-generator");

        new GenerateImagesCommandGroup().addTo(commander);

        Cli.builder()
                .commander(commander)
                .build()
                .run(args);
    }

}
