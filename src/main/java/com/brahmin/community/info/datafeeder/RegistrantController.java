package com.brahmin.community.info.datafeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RegistrantController {

    private final RegistrantService registrantService;

    @Autowired
    public RegistrantController(RegistrantService registrantService) {
        this.registrantService = registrantService;
    }

    @GetMapping("/uploadExcel")
    @ResponseBody
    public void uploadExcelFile() {
        registrantService.readExcelAndSaveToDatabase();
    }

    @GetMapping("/details")
    @ResponseBody
    public List<Registrant> getDetails(){
        return registrantService.getDetails();
    }

    @GetMapping("/details/phone")
    @ResponseBody
    public List<Registrant> getDetailsByPhoneNumber(@RequestParam("phone_number") long phoneNumber){
        return registrantService.getDetailsByPhoneNumber(phoneNumber);
    }

    @GetMapping("/details/designation")
    @ResponseBody
    public List<Registrant> getDetailsByDesignation(@RequestParam("designation") String designation){
        return registrantService.getDetailsByDesignation(designation);
    }

//    @GetMapping("/details/name")
//    @ResponseBody
//    public List<Registrant> getDetailsByName(@RequestParam("first_name") String firstName,@RequestParam("last_name") String lastName){
//        return registrantService.getDetailsByName(firstName, lastName);
//    }

    @GetMapping("/search")
    public String showSearchForm() {
        return "search"; // Assuming "search.html" is your Thymeleaf template for the search form
    }

    @PostMapping("/search")
    public String performSearch(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("designation") String designation,
                                @RequestParam("phoneNumber") String phoneNumber,
                                Model model) {
        List<Registrant> searchResults = registrantService.search(firstName,lastName,designation,phoneNumber);
        model.addAttribute("results", searchResults);
        return "search"; // Assuming "search.html" is your Thymeleaf template for the search form
    }
}
