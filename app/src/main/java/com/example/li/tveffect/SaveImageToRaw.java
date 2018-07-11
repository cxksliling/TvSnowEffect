package com.example.li.tveffect;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveImageToRaw {
    private Context context;
    private Bitmap bitmap;
    public SaveImageToRaw(Context context){
        this.context = context;
    }
    public void savaImage(String filename,Bitmap bitmap){
        try {
            FileOutputStream fos = context.openFileOutput(filename,context.MODE_PRIVATE);
            //bitmap转文件对象
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Bitmap getImage(String filename){
        try {
            FileInputStream fis = context.openFileInput(filename);
            bitmap = BitmapFactory.decodeStream(fis);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
