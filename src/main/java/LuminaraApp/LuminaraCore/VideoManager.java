package LuminaraApp.LuminaraCore;

public class VideoManager {
    public static int CalculateVideoLength(int[] Dimension, int TargetFPS, int PixelsPerFrame){
        int FrameCount = (Dimension[0] * Dimension[1]) / PixelsPerFrame;
        return FrameCount / TargetFPS;
    }
}
