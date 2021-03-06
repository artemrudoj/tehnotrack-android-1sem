package com.artem.hometask.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.artem.hometask.utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

/**
 * Created by artem on 26.04.16.
 */
public class ImageDownloadManager {
    private static ImageDownloadManager mInstance = null;
    private LruCache<String, Bitmap> memoryCache;


    private ImageDownloadManager(){
    }

    void initCache() {
        if(memoryCache == null) {
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
            final int cacheSize = maxMemory / 8;
            memoryCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
                }
            };
        }
    }

    void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if(memoryCache == null) throw new IllegalStateException("memoryCache can not be null");
        memoryCache.put(key, bitmap);
    }

    Bitmap getBitmapFromMemCache(String key) {
        if(memoryCache == null) throw new IllegalStateException("memoryCache can not be null");
        return memoryCache.get(key);
    }

    public void setImageViewByUrl(Context context, String name, ImageView iv, Integer width) {
        if(iv.getTag() != null) {
            iv.setImageBitmap(null);
        }
        iv.setTag(name);
        int _imageWidth;
        if(width == null) {
            _imageWidth = Utils.getDisplayWidth(context);
        } else {
            _imageWidth = width;
        }
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
        if(params.width != _imageWidth) {
            params.width = _imageWidth;
            params.height = _imageWidth;
            iv.setLayoutParams(params);
        }
        final Bitmap bm = getBitmapFromMemCache(name);
        if (null != bm) {
            iv.setImageBitmap(bm);
        } else {
            LoadImageTask lt = new LoadImageTask(context, iv, name, _imageWidth);
            lt.execute();
        }
    }

    public static ImageDownloadManager getInstance(){
        if(mInstance == null)
        {
            mInstance = new ImageDownloadManager();
            mInstance.initCache();

        }
        return mInstance;
    }

    private class LoadImageTask extends AsyncTask<Void, Void, Bitmap> {
        private final WeakReference<ImageView> _weakIv;
        private final WeakReference<Context> _context;
        private final String _name;
        int _imageWidth;
        public LoadImageTask(Context context, ImageView iv, String name, Integer width) {

            super();
            _weakIv = new WeakReference<>(iv);
            _context = new WeakReference<>(context);
            _name = name;
            _imageWidth = width;

        }


        protected Bitmap decodeFile(File file) {
            try {
                InputStream is = new FileInputStream(file);
                BitmapFactory.Options opt = new BitmapFactory.Options();
                opt.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(is, null, opt);
                int sc = calculateInSampleSize(opt, _imageWidth, _imageWidth);
                opt.inSampleSize = sc;
                opt.inJustDecodeBounds = false;
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file), null, opt);
                if (bitmap != null) {
                    Log.d("LOAD_IMAGE", " name = " + _name + " w = " + bitmap.getWidth() + " h = " + bitmap.getHeight());
                }
                return bitmap;
            } catch (IOException e) {
                Log.e("LoadImageTask", "LoadImageTask.LoadBitmap IOException " + e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                Context context = _context.get();
                Bitmap bitmap;
                File file;
                if (context != null) {
                    file = new File(context.getCacheDir(), _name.replace("/", ""));
                    bitmap = decodeFile(file);
                    if (null == bitmap ) {
                        URL url = new URL(_name);
                        InputStream is = url.openConnection().getInputStream();
                        OutputStream os = new FileOutputStream(file);
                        Utils.CopyStream(is, os);
                        os.close();
                        bitmap = decodeFile(file);
                    }
                    return bitmap;
                }
            } catch (IOException e) {
                Log.e("LoadImageTask", "LoadImageTask.LoadBitmap IOException " + e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Bitmap bm = ImageDownloadManager.this.getBitmapFromMemCache(_name);
            if (bm == null && bitmap != null) {
                ImageDownloadManager.this.addBitmapToMemoryCache(_name, bitmap);
                bm = bitmap;
            }
            ImageView iv = _weakIv.get();
            if (iv != null && iv.getTag() != null && _name.equals(iv.getTag())) {
                iv.setImageBitmap(bm);
            }
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
