import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static List<List<String>> allChar = new ArrayList<>();

    public static void main(String[] args) {
        int ind = 0;
        for(String[] language: CharactersContainers.CHARACTER_2D_ARRAY) {
            fileGen(8, 8, language, CharactersContainers.CHARACTER_ARRAY_NAMES[ind] + "_" + 8 + "x" + 8 + ".h");
            fileGen(16, 16, language, CharactersContainers.CHARACTER_ARRAY_NAMES[ind]  + "_" + 16 + "x" + 16 + ".h");
            ind++;
            break;
        }
    }
    private  static void fileGen(int width,int height,String[] allChars , String fileName){
        allChar.clear();
        for (String ele : allChars) {
            Font font = new Font("Arial", Font.PLAIN,  width);
            createImageFromText(ele, width, height, font);
            break;
        }
        generateTextFile(width, height,fileName,allChars);
    }

    public static void createImageFromText(String text, int width, int height,Font font) {
        BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bimage.createGraphics();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(Color.BLACK);

        g2d.setFont(font);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        FontMetrics metrics = g2d.getFontMetrics();
        int x = (width - metrics.stringWidth(text)) / 2;
        int y = (height - metrics.getHeight()) / 2 + metrics.getAscent();

        g2d.drawString(text, x, y);

        g2d.dispose();
        allChar.add(getImagePixelValues(bimage, width));

         try {
             String fileName = String.format(text+".png", text, width, height);
             File file = new File(fileName);
             ImageIO.write(bimage, "bmp", file);
             System.out.println("Image saved as " + file.getName());
         } catch (IOException e) {
             System.out.println("Error saving image: " + e.getMessage());
         }

    }

    private static List<String> getImagePixelValues(BufferedImage bimage, int width) {
        List<String> byteValuesList = new ArrayList<>();
        for (int y = 0; y < bimage.getHeight(); y++) {
            int rowValue = 0; // This will store the compressed value for the entire row
            int bitCount = 0; // This will track the number of bits accumulated for the current row

            for (int x = 0; x < bimage.getWidth(); x++) {
                int pixel = bimage.getRGB(x, y);

                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = pixel & 0xFF;

                int grayscale = (int) (0.299 * red + 0.587 * green + 0.114 * blue);

                int binaryValue = (grayscale < 128) ? 1 : 0;

                // Shift the current row value to make room for the new bit
                rowValue = (rowValue << 1) | binaryValue;
                bitCount++;

                // If the row is full (8 or 16 bits), process it
                if (bitCount == width) {
                    if (width == 8) {
                        // Add the 8-bit compressed value
                        byteValuesList.add(String.format("0x%02X", rowValue));
                    } else if (width == 16) {
                        // Add the 16-bit compressed value
                        byteValuesList.add(String.format("0x%04X", rowValue));
                    }
                    // Reset for the next row
                    rowValue = 0;
                    bitCount = 0;
                }
            }

            // If there are any remaining bits after the inner loop finishes (for rows not fully processed)
            if (bitCount > 0) {
                if (width == 8) {
                    byteValuesList.add(String.format("0x%02X", rowValue));
                } else if (width == 16) {
                    byteValuesList.add(String.format("0x%04X", rowValue));
                }
            }
        }

        return byteValuesList;
    }



    public static void generateTextFile(int width, int height,String fileName,String[] allChars) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("const u8 font" + width + "x" + height + "[] = {\n");
            writer.write("    0x00, 0x00, // size\n");
            writer.write("    " + width + ",         // width\n");
            writer.write("    " + height + ",         // height\n");
            writer.write("    0x" + allChars[0] + ",     // first char\n");
            writer.write("    " + allChar.size() + ",        // char count\n\n");
            writer.write("    // Fixed width; char width table not used\n");

            for (int i = 0; i < allChar.size(); i++) {
                List<String> innerList = allChar.get(i);
                writer.write("    ");
                for (int j = 0; j < innerList.size(); j++) {
                    writer.write(innerList.get(j));
                    if (j < innerList.size() - 1) {
                        writer.write(", ");
                    }
                }
                String correspondingChar = allChars[i];
                writer.write(", // " + correspondingChar + "\n");
            }

            writer.write("};\n");
            System.out.println("Header file generated successfully: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
