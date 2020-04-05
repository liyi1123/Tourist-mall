package com.stylefeng.guns.modular.system.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.log.LogManager;
import com.stylefeng.guns.core.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码生成
 *
 * @author fengshuonan
 * @date 2017-05-05 23:10
 */
@Controller
@RequestMapping("/kaptcha")
public class KaptchaController {
    private static Logger log = LoggerFactory.getLogger(KaptchaController.class);
    @Autowired
    private GunsProperties gunsProperties;

    @Autowired
    private Producer producer;

    /**
     * 生成验证码
     */
    @RequestMapping("")
    public void index(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        response.setDateHeader("Expires", 0);

        // 设置标准HTTP/1.1无缓存头。
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // 设置IE扩展的HTTP/1.1无缓存头（使用添加头）.
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // 设置标准HTTP/1.0无缓存头 Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // 返回一个jpeg图片 return a jpeg
        response.setContentType("image/jpeg");

        // 为图像创建文本 create the text for the image
        String capText = producer.createText();
        log.info("验证码:--> " + capText);
        // 将文本存储在会话中 store the text in the session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // 用文本创建图像 create the image with the text
        BufferedImage bi = producer.createImage(capText);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 写输出数据 write the data out
        try {
            ImageIO.write(bi, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 返回图片
     *
     * @author stylefeng
     * @Date 2017/5/24 23:00
     */
    @RequestMapping("/{pictureId}")
    public void renderPicture(@PathVariable("pictureId") String pictureId, HttpServletResponse response) {
        String path = gunsProperties.getFileUploadPath() + pictureId + ".jpg";
        try {
            byte[] bytes = FileUtil.toByteArray(path);
            response.getOutputStream().write(bytes);
        }catch (Exception e){
            //如果找不到图片就返回一个默认图片
            try {
                response.sendRedirect("/static/img/girl.gif");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
