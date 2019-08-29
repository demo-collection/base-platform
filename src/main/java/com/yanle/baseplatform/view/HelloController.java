package com.yanle.baseplatform.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class HelloController {

    @GetMapping("/home")
    public String hello(Model model) {
        model.addAttribute("name", "yanle");
        model.addAttribute("age", 27);
        return "hello";
    }
}
