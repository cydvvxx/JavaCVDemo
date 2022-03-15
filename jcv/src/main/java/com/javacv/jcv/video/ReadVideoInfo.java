package com.javacv.jcv.video;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ReadVideoInfo {


    public static String getTargetPath(String targetPath ,String filePath,int n) throws IOException {

        File file = new File(filePath);
        if (file.exists()){
            System.out.println("file exists");
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(file);
            grabber.start();
            int length = grabber.getLengthInFrames();
            int f = 0;
            Frame frame = null;
            while (f <=length){
                frame = grabber.grabImage();
                if (f > n){
                    break;
                }
                if (frame == null){
                    break;
                }
                Java2DFrameConverter c = new Java2DFrameConverter();
                BufferedImage bufferedImage = c.getBufferedImage(frame);
                String TF= targetPath+f+".jpg";
                File targetTile = new File(TF);
                if (!targetTile.getParentFile().exists()){
                    System.out.println("parent-file="+targetTile.getParentFile());
                    targetTile.getParentFile().mkdir();
                }
                ImageIO.write(bufferedImage,"jpg",targetTile);
                f++;
            }
            grabber.close();
            grabber.stop();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        getTargetPath("D:/study/test/sishi","E:/file/sishi.mp4",10);
    }
}
