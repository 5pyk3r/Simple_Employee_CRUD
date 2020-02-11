package com.app.controllers;

import com.app.services.EmployeeRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListController {

    private EmployeeRepo employeeRepo;

    public ListController(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/listOfEmployees")
    public String getList(Model model){
        model.addAttribute("list", employeeRepo.findAll());

        return "listOfEmployees";
    }
}
