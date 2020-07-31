package wl1929.travel.web.servlet;

import wl1929.travel.domain.Category;
import wl1929.travel.service.CategoryService;
import wl1929.travel.service.impl.CategoryServiceImpl;
import wl1929.travel.web.servlet.BaseServlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/29 14:34
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService service = new CategoryServiceImpl();

    /**
     * 查询所有
     * @author : wangli4773@163.com
     * @date : 2020/7/29 16:09
     * @param request :
     * @param response :
     * @return : void
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.调用service查询所有
        List<Category> cs = service.findAll();
        // 2.序列化json返回
        writeValue(cs, response);
    }
}
