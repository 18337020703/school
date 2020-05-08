package com.wang.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HttpUtil {
    /*
        1. 获取上传文件的网络路径
        2. 上传文件
     */
    public static String getHttpUrl(MultipartFile file, HttpServletRequest request, HttpSession session, String dir) throws IOException {
        // 获取路径
        String realPath = session.getServletContext().getRealPath(dir);
//        String newFileNamePrefix=new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())+
//                UUID.randomUUID().toString();
//        String newFileNameSuffix="."+ FilenameUtils.getExtension(file.getOriginalFilename());
//        //新的文件名
//        String newFileName=newFileNamePrefix+newFileNameSuffix;
        //创建日期目录
        String dataDayString=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
       // File dataDay = new File(realPath, dataDayString);
        // 判断路径文件夹是否存在
        File f = new File(realPath,dataDayString);
        if(!f.exists()){
            f.mkdirs();
        }
        // 防止重名操作
        String originalFilename = file.getOriginalFilename();
        originalFilename = new Date().getTime()+"_"+originalFilename;
//        if ("png".equals(FilenameUtils.getExtension(file.getOriginalFilename()))){
//            Thumbnails.of(file.getInputStream()).outputFormat("jpg").scale(0.2f).outputQuality(0.2f).toFile(new File(f,originalFilename));
//        }else {
            try {
                Thumbnails.of(file.getInputStream()).size(400,400).keepAspectRatio(false).outputQuality(0.2f).toFile(new File(f,originalFilename));
                // file.transferTo(new File(f,originalFilename));
            } catch (IOException e) {
                e.printStackTrace();
            }
       // }

        // 相对路径 : ../XX/XX/XX.jpg
        // 网络路径 : http://IP:端口/项目名/文件存放位置
        String http = request.getScheme();
        String localHost = null;
        try {
            localHost = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        // 网络路径拼接
       //String uri = http+"://"+localHost.split("/")[1]+":"+serverPort+contextPath+dir+"/"+dataDayString+"/"+originalFilename;
        String uri = http+"://"+"49.234.18.34"+":"+serverPort+contextPath+dir+"/"+dataDayString+"/"+originalFilename;

        return uri;
    }
}
