package com.tksoft.weather2018.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.io.File;

public class MediaStoreUtils {

    public static void addToMediaStore(final Context context, String filePath) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Uri uri = FileUtils.getUriFromPath(context, filePath);
            MediaStoreUtils.notifyToMediaStoreAboveV24(context, uri);
        } else {
            MediaStoreUtils.addToMediaStoreV24(context, filePath);
        }
    }

    public static void notifyToMediaStoreAboveV24(Context context, Uri uri) {
        try {
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(uri);
            context.sendBroadcast(intent);
        } catch (Exception e) {
        }
    }

    private static void addToMediaStoreV24(Context context, String filePath) {
        File file = new File(filePath);
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, file.getName());
        values.put(MediaStore.Images.Media.DISPLAY_NAME, file.getName());
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.DATE_MODIFIED, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.DATA, filePath);
        Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        context.getContentResolver().insert(contentUri, values);
    }

}
