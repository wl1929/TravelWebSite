package wl1929.travel.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Description: 验证码
 * @Author wangli4773@163.com
 * @Created: 2020/07/30 14:45
 */
public class CheckCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 服务器通知浏览器不要缓存
        resp.setHeader("pragma","no-cache");
        resp.setHeader("cache-control","no-cache");
        resp.setHeader("expires","0");

        // 在内存中创建一个长80，宽30的图片，默认黑色背景
        // 参数一：长
        // 参数二：宽
        // 参数三：颜色
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取画笔
        Graphics graphics = image.getGraphics();
        // 设置画笔颜色为灰色
        graphics.setColor(Color.GRAY);
        // 填充图片
        graphics.fillRect(0, 0, width, height);

        // 产生4个随机验证码
        String chechCode = getCheckCode();
        // 将验证码放入HttpSession中
        req.getSession().setAttribute("CHECKCODE_SERVER", chechCode);

        // 设置画笔颜色为黄色
        graphics.setColor(Color.YELLOW);
        // 设置字体的小大
        graphics.setFont(new Font("黑体", Font.BOLD, 24));
        // 向图片上写入验证码
        graphics.drawString(chechCode, 15, 25);

        // 将内存中的图片输出到浏览器
        // 参数一：图片对象
        // 参数二：图片的格式，如PNG,JPG,GIF
        // 参数三：图片输出到哪里去
        ImageIO.write(image, "PNG", resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 产生4位随机字符串
     * @author : wangli4773@163.com
     * @date : 2020/7/30 14:58
     * @return : java.lang.String
     */
    public String getCheckCode() {
        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < 4; i++) {
            // 产生0到size-1的随机值
            int index = random.nextInt(size);
            // 在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            // 将c放入到StringBuffer中去
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
