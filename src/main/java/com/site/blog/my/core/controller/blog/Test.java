package com.site.blog.my.core.controller.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class Test {
    @RequestMapping("/test")
    public void test(Model model, HttpSession session, HttpServletResponse response){
//        model.addAttribute("name","张三");
//        session.setAttribute("age","30");
//        return "redirect:blog/amaze/test";
        try {
            response.getWriter().write("{\"success\":0}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
