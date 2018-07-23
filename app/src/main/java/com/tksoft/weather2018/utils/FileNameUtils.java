package com.tksoft.weather2018.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 * Khanh Code, alternate by
 */
public class FileNameUtils {

    /**
     * Return file match with @ path
     */


    private static File checkDir(String path) {
        File localFile = new File(path);
        if ((!localFile.exists()) && (!localFile.mkdirs()))
            localFile = null;
        return localFile;
    }

    private static String getDatabaseDir(Context content) {
        String str = "/data/data/" + content.getPackageName() + "/databases";
        checkDir(str);
        return str;
    }

    @Deprecated
    public static boolean deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    } else {
                        files[i].delete();
                    }
                }
            }
        }
        return (directory.delete());
    }

    @SuppressLint("DefaultLocale")
    @Deprecated
    public static void copyDataToApp(Context context, String dbName) throws IOException {
        File localFile1 = context.getDatabasePath(dbName);
        String str = localFile1.getAbsolutePath();
        localFile1.delete();
        AssetManager am = context.getAssets();
        File localFile2 = new File(str);
        if (!localFile2.exists()) {
            checkDir(getDatabaseDir(context));
            localFile2.createNewFile();
        }
        FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
        byte[] arrayOfByte = new byte[1024]; // 4096

        int r;
        for (int i = 1; i <= 1; i++) {
            String fn = String.format("%d.db", i);
            InputStream is = am.open(fn);
            while ((r = is.read(arrayOfByte)) != -1)
                localFileOutputStream.write(arrayOfByte, 0, r);
            is.close();
        }
        localFileOutputStream.flush();
        localFileOutputStream.close();
    }


    public static boolean exists(String filePath) {
        try {
            if (filePath.isEmpty())
                return false;
            File f = new File(filePath);
            return f.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static final String ANSI_INVALID_CHARACTERS = "\\/:*?\"<>|";

    public static boolean isValidateName(String name) {
        for (int i = 0; i < ANSI_INVALID_CHARACTERS.length(); i++) {
            if (name.contains(String.valueOf(ANSI_INVALID_CHARACTERS.charAt(i)))) return false;
        }
        return true;
    }

    @Deprecated
    public static void createDirFolder(String filePath, Context context) {
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    public static boolean deleteFile(String filePath) {
        return new File(filePath).delete();
    }

    /**
     * @param url
     * @return
     */
    public static String getFileName(String url) {
//        return StringUtils.createKey(url);
        if (!TextUtils.isEmpty(url)) {
            url = url.substring(1 + TextUtils.lastIndexOf(url, '/'),
                    url.length());
            return url;
        }
        return null;
    }

    public static String getFileNameFromPath(String path) {
        if (!TextUtils.isEmpty(path)) {
            path = path.substring(TextUtils.lastIndexOf(path, '/') + 1);
            return path;
        }
        return path;
    }

    public static String getFileNameWithoutExtension(String path) {
        if (!TextUtils.isEmpty(path)) {
            int i = 1 + TextUtils.lastIndexOf(path, '/');
            int j = TextUtils.lastIndexOf(path, '.');
            if (j == -1)
                j = path.length();
            path = path.substring(i, j);
        }
        return path;
    }

    public static String getExtension(String path) {
        String extension = null;

        int i = path.lastIndexOf('.');
        if (i > 0) {
            extension = path.substring(i + 1).toLowerCase();
        }
        return extension;
    }

    /**
     * @param url = file path or whatever suitable URL you want.
     * @return : mimetype.
     * @author : ManhPhi
     */
    public static String getMimeType(String url) {
        String type = null;
        String extension = getExtension(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    /**
     * Create new file to folder with fileName
     *
     * @param data     : string to write into file
     * @param folder   : path of file
     * @param fileName : name of file
     */

    public static void writeFile(String data, String folder, String fileName) {
        if (data == null) {
            return;
        }
        File fileFolder = new File(folder);
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }

        File file = new File(folder, fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter oWriter = new OutputStreamWriter(fOut);
            oWriter.write(data);
            oWriter.flush();
            oWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String data, String path) {
        File file = new File(path);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter oWriter = new OutputStreamWriter(fOut);
            oWriter.write(data);
            oWriter.flush();
            oWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read file to string
     *
     * @param folder   path of file
     * @param fileName name of file
     * @return string if file exit, null if file not exit
     */
    public static String readFile(String folder, String fileName) {

        File file = new File(folder, fileName);
        if (file.exists()) {
            FileInputStream fIn;
            try {
                fIn = new FileInputStream(file);
                Writer writer = new StringWriter();
                char[] buffer = new char[1024];
                Reader reader;
                reader = new BufferedReader(new InputStreamReader(fIn, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
                fIn.close();
                return writer.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Example: String data = FileNameUtils.readAcessFile(this,
     * "tip/"+fileName+".txt");
     */
    public static String readAcessFile(Context context, String fileName) {
        try {
            InputStream localInputStream = context.getAssets().open(fileName, AssetManager.ACCESS_STREAMING);
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            Reader reader;
            reader = new BufferedReader(new InputStreamReader(localInputStream, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            localInputStream.close();
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Example: String data = FileNameUtils.readAcessFile(this,
     * "tip/"+fileName+".txt");
     */
    public static byte[] readAcessFileToBytes(Context context, String fileName) {
        try {
            InputStream localInputStream = context.getAssets().open(fileName, AssetManager.ACCESS_STREAMING);
            byte[] fileData = new byte[(int) localInputStream.available()];
            DataInputStream dis = new DataInputStream(localInputStream);
            dis.readFully(fileData);
            dis.close();
            return fileData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Read file to string
     */
    public static String readFile(String filePath) throws Exception {
        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream fIn;
            try {
                fIn = new FileInputStream(file);
                Writer writer = new StringWriter();
                char[] buffer = new char[1024];
                Reader reader;
                reader = new BufferedReader(new InputStreamReader(fIn, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
                fIn.close();
                return writer.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] readFileToBytes(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                byte[] fileData = new byte[(int) file.length()];
                DataInputStream dis = new DataInputStream(new FileInputStream(file));
                dis.readFully(fileData);
                dis.close();
                return fileData;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * copy file from Src to DSt and can rename
     *
     * @param fileSource
     * @param fileTarget
     * @param nameTarget
     */
    public static boolean copyFile(File fileSource, File fileTarget, String nameTarget) {
        if (!fileSource.exists())
            return false;
        try {
            String mPath = fileTarget.getAbsolutePath();
            if (fileSource.isDirectory()) {
                File[] listFiles = fileSource.listFiles();
                File mFolder;
                if (nameTarget != null) {
                    mFolder = new File(mPath + "/" + nameTarget);
                } else {
                    mFolder = new File(mPath + "/" + fileSource.getName());
                }
                if (!mFolder.exists()) {
                    mFolder.mkdirs();
                }
                for (File mFile : listFiles) {
                    FileInputStream mFileInputStream = new FileInputStream(mFile);
                    FileOutputStream mFileOutputStream = new FileOutputStream(mFolder.getAbsolutePath() + "/"
                            + mFile.getName());
                    byte[] buffer = new byte[1024];
                    int len1 = 0;
                    while ((len1 = mFileInputStream.read(buffer)) > 0) {
                        mFileOutputStream.write(buffer, 0, len1);
                    }
                    mFileOutputStream.flush();
                    mFileOutputStream.close();
                    mFileInputStream.close();
                }
            } else {
                String name;
                if (nameTarget != null)
                    name = nameTarget;
                else
                    name = fileSource.getName();
                FileInputStream mFileInputStream = new FileInputStream(fileSource);
                FileOutputStream mFileOutputStream = new FileOutputStream(fileTarget.getAbsolutePath() + "/" + name);
                byte[] buffer = new byte[1024];
                int len1 = 0;
                while ((len1 = mFileInputStream.read(buffer)) > 0) {
                    mFileOutputStream.write(buffer, 0, len1);
                }
                mFileOutputStream.flush();
                mFileOutputStream.close();
                mFileInputStream.close();
            }

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * copy file from src to dst<br>
     * fileTarget is Dir
     */
    public static boolean copyFile(File pathSource, File pathTarget) {
        return copyFile(pathSource, pathTarget, null);
    }

    /**
     * copy file from src to dst<br>
     * fileTarget is Dir
     */
    public static boolean copyFileWithPath(String pathSource, String pathTarget) {
        File fileSource = new File(pathSource);
        File fileTarget = new File(pathTarget);
        return copyFile(fileSource, fileTarget);
    }

    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        input.close();
        output.flush();
        output.close();
    }

    public static void moveFileWithPath(String pathSource, String pathTarget) {
        copyFileWithPath(pathSource, pathTarget);
        deleteFile(pathSource);
    }

    /**
     * Copy files in assetFolderPath to sdcardFolder
     *
     * @param assetFolderPath : ie: sample1 , sample1/sample2
     * @throws Exception
     */

    public static void copyAssetFolderToSdCard(String assetFolderPath, String sdcardFolder, Activity activity) throws Exception {
        AssetManager assetManager = activity.getAssets();
        String assets[] = null;
        assets = assetManager.list(assetFolderPath);
        if (assets.length == 0) {
            copyAssetFileToSdCard(assetFolderPath, sdcardFolder, activity);
        } else {
            for (int i = 0; i < assets.length; ++i) {
                copyAssetFolderToSdCard(assetFolderPath + "/" + assets[i], sdcardFolder, activity);
            }
        }
    }

    /**
     * Copy filename to sdcardFolder
     *
     * @param assetFilename : i.e., "docs/AndroidWebServerConfigActivity.html".
     */
    public static void copyAssetFileToSdCard(String assetFilename, String sdcardFolder, Activity activity) throws Exception {
        AssetManager assetManager = activity.getAssets();
        InputStream in = null;
        OutputStream out = null;
        in = assetManager.open(assetFilename);
        String newFileName = sdcardFolder + FileNameUtils.getFileName(assetFilename);
        out = new FileOutputStream(newFileName);

        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        in.close();
        in = null;
        out.flush();
        out.close();
        out = null;
    }

    @SuppressLint("NewApi")
    public static boolean isExternalStorageRemovable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    public static File getExternalCacheDir(Context context) {
        if (hasExternalCacheDir()) {
            return context.getExternalCacheDir();
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }

    public static boolean hasExternalCacheDir() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    /**
     * Extract Here
     *
     * @return
     */

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // LocalStorageProvider

            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    public static boolean unpackZip(String dirOut, String fileIn, boolean isDeleteFile) {
        InputStream is;
        ZipInputStream zis;
        try {
            String filename;
            is = new FileInputStream(fileIn);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[8192];
            int count;
            while ((ze = zis.getNextEntry()) != null) {
                // zapis do souboru
                filename = ze.getName();
                String name = "";
                for (int i = filename.length() - 1; i >= 0; i--) {
                    if (filename.charAt(i) == '/')
                        break;
                    name = filename.charAt(i) + name;
                }
                String path = filename.replace(name, "");
                File filePath = new File(dirOut + path);
                if (!filePath.exists())
                    filePath.mkdirs();
                if (ze.isDirectory()) {
                    File fmd = new File(dirOut + filename);
                    fmd.mkdirs();
                    continue;
                }
                FileOutputStream fout = new FileOutputStream(dirOut + filename);
                // cteni zipu a zapis
                while ((count = zis.read(buffer)) != -1) {
                    fout.write(buffer, 0, count);
                }
                fout.close();
                zis.closeEntry();
            }
            zis.close();
            if (isDeleteFile) {
                FileNameUtils.deleteFile(fileIn);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void unzip(String zipFile, String location) throws IOException {
        try {
            File f = new File(location);
            if (!f.isDirectory()) {
                f.mkdirs();
            }
            ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile));
            try {
                ZipEntry ze = null;
                while ((ze = zin.getNextEntry()) != null) {
                    String path = location + ze.getName();
                    if (ze.isDirectory()) {
                        File unzipFile = new File(path);
                        if (!unzipFile.isDirectory()) {
                            unzipFile.mkdirs();
                        }
                    } else {
                        FileOutputStream fout = new FileOutputStream(path, false);
                        try {
                            for (int c = zin.read(); c != -1; c = zin.read()) {
                                fout.write(c);
                            }
                            zin.closeEntry();
                        } finally {
                            fout.close();
                        }
                    }
                }
            } finally {
                zin.close();
            }
        } catch (Exception e) {
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static String formatFileNameWhenDuplicate(String destination, String fileName) {
        boolean checkExist;
        int index = 1;
        String formatFileName = fileName;
        do {
            if (checkExist = new File(destination + "/" + formatFileName).exists()) {
                formatFileName = FileNameUtils.addIndexToFile(fileName, index);
                index++;
            }
        } while (checkExist);
        return formatFileName;
    }

    public static String addIndexToFile(String fileName, int index) {
        int extensionIndex = fileName.lastIndexOf(".");
        if (extensionIndex == -1) return fileName + "(" + index + ")";
        else
            return fileName.substring(0, extensionIndex) + "(" + index + ")" + fileName.substring(extensionIndex);
    }

    public static String addOldIndexToFile(String fileName, int index) {
        int extensionIndex = fileName.lastIndexOf(".");
        if (extensionIndex == -1) return fileName + "_old" + index;
        else
            return fileName.substring(0, extensionIndex) + "_old" + index + fileName.substring(extensionIndex);
    }

    public static String formatOldFileNameWhenExist(String destination, String fileName) {
        boolean checkExist;
        int index = 1;
        String formatFileName = fileName;
        do {
            if (checkExist = new File(destination + "/" + formatFileName).exists()) {
                formatFileName = FileNameUtils.addOldIndexToFile(fileName, index);
                index++;
            }
        } while (checkExist);
        return formatFileName;
    }
}
