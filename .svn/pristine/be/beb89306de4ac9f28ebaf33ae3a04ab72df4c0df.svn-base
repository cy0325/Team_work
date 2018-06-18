package org.nwnu.pub.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * -----------------------------------------
 * @文件: ExcelUtil.java
 * @描述: 导出Excel具类
 * -----------------------------------------
 */
public class ZipUtil {
    /**
     *  压缩并导出文件
     * @param zipPath 压缩文件临时路径  路径最后不要有 /
     * @param zipName 压缩为文件名 **.zip
     * @param createFilesPath 需要压缩的文件列表
     * @param
     * @param response
     * @return
     * @throws IOException
     */
    public static boolean downloadZip(String zipPath, String zipName, List<String>createFilesPath, HttpServletResponse response) {
        byte[] buffer = new byte[1024];
        String strZipPath=zipPath+"/"+zipName;
        try {
            File tmpZip=new File(zipPath);
            if (!tmpZip.exists())
                tmpZip.mkdirs();
            File tmpZipFile = new File(strZipPath);
            if (!tmpZipFile.exists())
                tmpZipFile.createNewFile();

            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));

            File[] file1 =new File[createFilesPath.size()] ;

            for(int i=0;i<createFilesPath.size();i++){
                file1[i]=new File(createFilesPath.get(i));
            }
            for (int i = 0; i < file1.length; i++) {
                FileInputStream fis = new FileInputStream(file1[i]);
                out.putNextEntry(new ZipEntry(file1[i].getName()));
                int len;
                // 读入需要下载的文件的内容，打包到zip文件
                while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                out.closeEntry();
                fis.close();
            }
            out.close();
            downloadFile(zipPath,zipName,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 以压缩文件导出
     * @param fileName
     * @param filePath
     * @param response
     */
    public static void downloadFile(String filePath,String fileName,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        try {
            File file=new File(filePath,fileName);
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
