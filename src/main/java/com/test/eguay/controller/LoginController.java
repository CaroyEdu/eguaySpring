package com.test.eguay.controller;

import com.test.eguay.dto.UserDTO;
import com.test.eguay.repository.CategoryRepository;
import com.test.eguay.service.CategoryService;
import com.test.eguay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoginController {

    protected UserService userService ;
    private CategoryService categoryService;

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/login")
    public String doListar (Model model) {

        return "login";
    }

        @PostMapping("/authenticate")
        public String doAutentica (Model model, HttpSession session,
                               @RequestParam("username") String user, @RequestParam("password") String password) {
        String goTo = "redirect:/";
        UserDTO admin = this.userService.loginUser(user, password);
        session.setAttribute("user", admin);
        if (admin == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            goTo = "login";
        }


        return goTo;
    }
    @GetMapping("/Disconnect")
    public String doLogout (Model model , HttpSession session) {
        session.setAttribute("user", null);

        return "redirect:/";
    }


}
