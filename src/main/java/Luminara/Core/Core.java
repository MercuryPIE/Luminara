package Luminara.Core;

import javafx.scene.control.Alert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Core {

    public static int ReadFile(File FileData, File SaveLocation) throws IOException {
        byte[] FileContent = Files.readAllBytes(FileData.toPath());
        int FileSize = FileContent.length;

        if (FileSize == 0){
            System.out.println("File is too small, please select another file.");
            DisplayAlert();
            return 1;
        }

        int[] ImageDimensions = GetImageSizes(FileSize);
        System.out.println(VideoManager.CalculateVideoLength(ImageDimensions, 30, 16));


        BufferedImage FileImage = getBufferedImage(ImageDimensions, FileSize, FileContent);

        // Append the File extension to the end of the save location.
        ImageIO.write(FileImage, "PNG", SaveLocation);
        return 0;
    }


    private static BufferedImage getBufferedImage(int[] ImageDimensions, int FileSize, byte[] FileContent) {
        int ImageWidth = ImageDimensions[0];
        int ImageHeight = ImageDimensions[1];

        BufferedImage FileImage = new BufferedImage(ImageWidth, ImageHeight, BufferedImage.TYPE_INT_ARGB);

        int ByteIndex = 0;

        for(int Y = 0; Y < ImageHeight; Y++){
            for(int X = 0; X < ImageWidth; X++){
                int R = ByteIndex < FileSize ? Byte.toUnsignedInt(FileContent[ByteIndex++]) : 0;
                int G = ByteIndex < FileSize ? Byte.toUnsignedInt(FileContent[ByteIndex++]) : 0;
                int B = ByteIndex < FileSize ? Byte.toUnsignedInt(FileContent[ByteIndex++]) : 0;
                int A = ByteIndex < FileSize ? Byte.toUnsignedInt(FileContent[ByteIndex++]) : 0;

                int RGBA = (A << 24) | (R << 16) | (G << 8) | B;
                FileImage.setRGB(X, Y, RGBA);
            }
        }
        return FileImage;
    }

    private static int[] GetImageSizes(int FileSize){
        double AspectRation = 16.0 / 9.0;
        int TotalPixels = (int) Math.ceil(FileSize / 4.0);

        int ImageWidth = (int) Math.ceil(Math.sqrt(TotalPixels * AspectRation));
        int ImageHeight = (int) Math.ceil(ImageWidth / AspectRation);

        while (ImageWidth * ImageHeight < TotalPixels){
            ImageWidth++;
            ImageHeight = (int) Math.ceil(ImageWidth / AspectRation);
        }

        return new int[]{ImageWidth, ImageHeight};
    }

    private static void DisplayAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("File To Small");
        alert.setHeaderText("The File is Too small");
        alert.setContentText("the file you have selected is too small, please select a file larger");
        alert.showAndWait();
    }

    public static byte[] ExtractFile(File FileData) throws IOException {
        byte[] FileContent = Files.readAllBytes(FileData.toPath());
        int FileSize = FileContent.length;

        int[] ImageDimensions = GetImageSizes(FileSize);
        BufferedImage FileImage = getBufferedImage(ImageDimensions, FileSize, FileContent);

        try(ByteArrayOutputStream OutputStream = new ByteArrayOutputStream()){
            ImageIO.write(FileImage, "PNG", OutputStream);
            return OutputStream.toByteArray();
        }
    }
}
