package com.petclinic.web;

import com.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/animals")
public class PetClinicController {

    @Autowired
    private PetClinicService petClinicService;

    @GetMapping("/owners")
    public ModelAndView getOwners() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("owners",petClinicService.findOwners());
        mav.setViewName("owners");
        return mav;
    }

    @RequestMapping("/pcs")
    @ResponseBody
    public String welcome() {
        return "Welcome to the PetClinic Webapp";
    }


    @RequestMapping("/test/{teststring}")
    @ResponseBody
    public String writeString(@PathVariable String teststring) {
        return teststring;
    }
}
