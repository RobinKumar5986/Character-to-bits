import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class TtfCharReader {

    public static void readCharactersFromTtf(String ttfFilePath) {
        try {
            // Resolve the file path
            File ttfFile = new File(ttfFilePath);
            if (!ttfFile.exists()) {
                System.err.println("Error: File not found at " + ttfFile.getAbsolutePath());
                return;
            }

            // Load the TTF file
            Font font = Font.createFont(Font.TRUETYPE_FONT, ttfFile);

            // Register the font with the graphics environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            // Set the font size to a readable size
            font = font.deriveFont(24f);

            // Print basic information about the font
            System.out.println("Font Name: " + font.getFontName());
            System.out.println("Font Family: " + font.getFamily());

            // Print all supported characters
            System.out.println("Supported Characters:");
            for (char c = 32; c < 256; c++) { // ASCII range for demonstration
                if (font.canDisplay(c)) {
                    System.out.print(c + " ");
                }
                break;
            }
            System.out.println();

        } catch (FontFormatException e) {
            System.err.println("The file is not a valid TTF font: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading the TTF file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Use a relative path to the .ttf file
        String ttfFilePath = "src/PonnalaRegular1.ttf"; // Adjust the file name if necessary
        readCharactersFromTtf(ttfFilePath);
    }
}
