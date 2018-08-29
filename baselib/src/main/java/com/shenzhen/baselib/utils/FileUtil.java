package com.shenzhen.baselib.utils;

import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;

public class FileUtil
{
    /**
     * sd卡是否存在
     *
     * @return
     */
    public static boolean isSdcardExist()
    {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获得sd卡根目录file对象引用
     *
     * @return
     */
    public static File getSdCardRootFile()
    {
        if (isSdcardExist())
        {
            return Environment.getExternalStorageDirectory();
        } else
        {
            return null;
        }
    }

    public static boolean copy(String oldFilePath, String newFilePath)
    {
        if (!TextUtils.isEmpty(oldFilePath) && !TextUtils.isEmpty(newFilePath))
        {
            File oldFile = new File(oldFilePath);
            File newFile = new File(newFilePath);
            if (newFile.exists())
            {
                newFile.delete();
            }
            if (oldFile.exists())
            {
                FileInputStream fis = null;
                FileOutputStream fos = null;
                FileChannel channelIn = null;
                FileChannel channelOut = null;
                try
                {
                    fis = new FileInputStream(oldFile);
                    fos = new FileOutputStream(newFile);
                    channelIn = fis.getChannel();
                    channelOut = fos.getChannel();
                    channelIn.transferTo(0, channelIn.size(), channelOut);
                    return true;
                } catch (Exception e)
                {
                    e.printStackTrace();
                } finally
                {
                    try
                    {
                        fis.close();
                        channelIn.close();
                        fos.close();
                        channelOut.close();
                    } catch (Exception e2)
                    {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    public static boolean saveStringToFile(String content, String filePath)
    {
        if (content == null || TextUtils.isEmpty(filePath))
        {
            return false;
        } else
        {
            File file = new File(filePath);
            PrintWriter pw = null;
            try
            {
                if (!file.exists())
                {
                    file.createNewFile();
                }
                if (file.exists())
                {
                    pw = new PrintWriter(file);
                    // pw = new PrintWriter(new BufferedWriter(
                    // new FileWriter(file)));
                    pw.write(content);
                    return true;
                } else
                {
                    return false;
                }
            } catch (Exception e)
            {
                return false;
            } finally
            {
                if (pw != null)
                {
                    pw.close();
                }
            }

        }
    }

    @SuppressWarnings("resource")
    public static String readStringFromFile(String filePath)
    {
        if (TextUtils.isEmpty(filePath))
        {
            return null;
        }
        File file = new File(filePath);
        if (file.exists())
        {
            try
            {

                StringBuffer strBuffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String strLine = "";
                while ((strLine = reader.readLine()) != null)
                {
                    strBuffer.append(strLine);
                }
                return strBuffer.toString();
            } catch (Exception e)
            {
                return null;
            }
        } else
        {
            return null;
        }
    }

    /**
     * 获得文件夹下所有文件的大小
     *
     * @param dirFile
     * @return
     */
    public static long getFileSize(File dirFile)
    {
        long size = 0;
        File flist[] = dirFile.listFiles();
        for (int i = 0; i < flist.length; i++)
        {
            if (flist[i].isDirectory())
            {
                size = size + getFileSize(flist[i]);
            } else
            {
                size = size + flist[i].length();
            }
        }
        return size;
    }

    public static long getFileSize(String dirPath)
    {
        File dirFile = new File(dirPath);
        if (dirFile.exists() && dirFile.isDirectory())
        {
            return getFileSize(dirFile);
        } else
        {
            return 0;
        }
    }

    /**
     *
     * @param file
     * @return
     */
    public static boolean deleteFile(File file)
    {
        if (file != null && file.exists())
        {
            if (file.isFile())
            {
                return file.delete();
            } else
            {
                File[] files = file.listFiles();
                boolean flag = true;
                for (int i = 0; i < files.length; i++)
                {
                    if (!deleteFile(files[i]))
                    {
                        flag = false;
                    }
                }
                if (flag)
                {
                    return file.delete();
                } else
                {
                    return false;
                }
            }
        } else
        {
            return false;
        }
    }

    /**
     * 格式化文件大小
     *
     * @param fileLength
     * @return
     */
    public static String formatFileSize(long fileLength)
    {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileLength <= 0)
        {
            fileSizeString = "0.00B";
        } else if (fileLength < 1024)
        {
            fileSizeString = df.format((double) fileLength) + "B";
        } else if (fileLength < 1048576)
        {
            fileSizeString = df.format((double) fileLength / 1024) + "KB";
        } else if (fileLength < 1073741824)
        {
            fileSizeString = df.format((double) fileLength / 1048576) + "MB";
        } else
        {
            fileSizeString = df.format((double) fileLength / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 获取某个文件目录下的文件夹数量
     *
     * @param dirFile
     * @return
     */
    public long getDirFileNumber(File dirFile)
    {
        long size = 0;
        File flist[] = dirFile.listFiles();
        size = flist.length;
        for (int i = 0; i < flist.length; i++)
        {
            if (flist[i].isDirectory())
            {
                size = size + getDirFileNumber(flist[i]);
                size--;
            }
        }
        return size;
    }

}
