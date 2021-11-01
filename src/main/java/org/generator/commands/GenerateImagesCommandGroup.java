package org.generator.commands;

import com.beust.jcommander.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.generator.ThrowingRunnable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Parameters(
        commandNames = {"assemble"}
)
public class GenerateImagesCommandGroup implements CommandGroup {

    @Slf4j
    @Parameters(
            commandNames = "generate-images",
            commandDescription = "Generate images"
    )
    public static class GenerateImagesCommand implements ThrowingRunnable {

        @Override
        public void run() throws Exception {
            System.out.println("--------Executing the assemble command--------");
            String backgroundTestFolderPath = "src/test/resources/background/";
            String eyesTestFolderPath = "src/test/resources/eye/";
            String hatTestFolderPath = "src/test/resources/hat/";
            String maskTestFolderPath = "src/test/resources/mask/";
            String mouthTestFolderPath = "src/test/resources/mouth/";
            String noseTestFolderPath = "src/test/resources/nose/";
            String sunglassesTestFolderPath = "src/test/resources/sunglasses/";

            String backgroundImageName = "AUSTRALIA Brick wall 3.png";
            String eyeImageName = "2 elegance yellow_black_sky blue_yellow_white- _black_brown_black_white - kopie.png";
            String hatImageName = "2 funny yellow.png";
            String maskImageName = "1 halloween mask.png";
            String mouthImageName = "15 rainbow.png";
            String noseImageName = "9 minimal black.png";
            String sunglassesImageName = "1 USA red_white,.png";


            //first init
            BufferedImage backgroundImage = ImageIO.read(new File(backgroundTestFolderPath + backgroundImageName));
            int width = backgroundImage.getWidth();
            int height = backgroundImage.getHeight();

            BufferedImage result = new BufferedImage(
                    width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = result.getGraphics();


            List<String> images = new ArrayList<>();
            images.add(backgroundTestFolderPath + backgroundImageName);
            images.add(eyesTestFolderPath + eyeImageName);
            images.add(hatTestFolderPath + hatImageName);
            images.add(maskTestFolderPath + maskImageName);
            images.add(mouthTestFolderPath + mouthImageName);
            images.add(noseTestFolderPath + noseImageName);
            images.add(sunglassesTestFolderPath + sunglassesImageName);

            for(String image : images){
                BufferedImage bi = ImageIO.read(new File(image));
                g.drawImage(bi, bi.getMinX(), bi.getMinY(), null);
//                x += 256;
//                if(x > result.getWidth()){
//                    x = 0;
//                    y += bi.getHeight();
//                }
            }

            ImageIO.write(result,"png",new File("src/test/resources/results/result.png"));
        }
    }

    @Override
    public List<Object> getSubCommands() {
        return Arrays.asList(
                new GenerateImagesCommand()
        );
    }
}
