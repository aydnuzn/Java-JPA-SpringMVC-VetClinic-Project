package com.works.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/takvim")
public class TakvimController {
    @GetMapping("")
    public String takvim() {
        return "takvim";
    }

}
