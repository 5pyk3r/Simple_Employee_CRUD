package com.app.controllers;

import com.app.model.Employee;
import com.app.services.EmployeeRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Controller
public class EmployeeController {

    private EmployeeRepo employeeRepo;

    public EmployeeController(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/")
    public String getHomePage(Model model, Employee employee){
        model.addAttribute("employeeObject" , employee);
        return "home";
    }

    @PostMapping("/addEmployee")
    public String saveOrUpdateEmployee(@Valid @ModelAttribute("employeeObject") Employee employee, BindingResult result){

        if(result.hasErrors()){
            return "home";
        }
            employeeRepo.save(employee);

        return "redirect:/listOfEmployees";
    }

    @GetMapping("{id}/delete")
    public String deleteEmployee(@PathVariable Long id){
        log.println("Deleting employee with ID: " + id);
        employeeRepo.deleteById(id);
        return "redirect:/listOfEmployees";
    }

    @GetMapping("{id}/update")
    public String updateEmployee(@PathVariable Long id, Model model){
        Employee employee = employeeRepo.findById(id).get();
        model.addAttribute("employeeObject", employee);
        log.println("Updating employee with id: " + id);
        return "update";
    }
}
