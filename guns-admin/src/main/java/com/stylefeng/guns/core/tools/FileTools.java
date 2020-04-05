package com.stylefeng.guns.core.tools;

import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class FileTools {
    private static final String UPLOAD_DIRECTORY = "upload";

    public static String getFileInfo(HttpServletRequest request, HttpServletResponse response, MultipartFile file){
        String filePath = new String();
        String uploadPath = request.getSession().getServletContext().getRealPath("./")+ UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
        if(!file.isEmpty()){
            try{
                filePath = request.getSession().getServletContext().getRealPath("./")+UPLOAD_DIRECTORY + File.separator + file.getOriginalFilename();
                file.transferTo(new File(filePath));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return filePath;
    }
}
