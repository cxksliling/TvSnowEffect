package com.example.li.tveffect;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.support.v8.renderscript.*;


public class MainActivity extends AppCompatActivity {

    ImageView mImageView;

    RenderScript rs;
    Allocation aIn;
    Allocation aOut;
    Bitmap[] mBitmaps = new Bitmap[9];
    Bitmap mOutBitmap;
    ScriptC_hi5 scriptC_hi;
    int a = 0;
    SaveImageToRaw saveImageToRaw;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.image1);

        //获得不被拉伸的bitmap
        BitmapFactory.Options bfoOptions = new BitmapFactory.Options();
        bfoOptions.inScaled = false;
        //final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a6, bfoOptions);
        //Log.d("shibendan", "" + bitmap.getConfig());
        Bitmap bitmap = Bitmap.createBitmap(360, 640, Bitmap.Config.ARGB_8888);
        //bitmap.eraseColor(Color.WHITE);



        mOutBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        rs = RenderScript.create(this);
        scriptC_hi = new ScriptC_hi5(rs);

        aIn = Allocation.createFromBitmap(rs, bitmap);
        aOut = Allocation.createFromBitmap(rs, bitmap);

        saveImageToRaw = new SaveImageToRaw(this);


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //for (int i = 0; i < 9; i++) {
//                    scriptC_hi.forEach_invert(aIn, aOut);
//                    aOut.copyTo(mOutBitmap);
//                    //mBitmaps[i] = mOutBitmap;
//                //}
//                showPictureChange();
//
//            }
//        }).start();

        showPictureChange();


    }

    private int nextInt(int i) {
        return (i + 1) % 9;
    }

    private void showPictureChange() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0 ; i<9;i++) {
                    scriptC_hi.forEach_invert(aIn, aOut);
                    aOut.copyTo(mOutBitmap);
                    mBitmaps[i] = mOutBitmap.copy(mOutBitmap.getConfig(), false);
                }

                while (true) {
                    a = nextInt(a);



                    //Bitmap bitmap = mOutBitmap.copy(mOutBitmap.getConfig(), false);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mImageView.setImageBitmap(mBitmaps[a]);
                        }
                    });

                    try {
                        Thread.sleep(30);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rs.destroy();
    }

}
