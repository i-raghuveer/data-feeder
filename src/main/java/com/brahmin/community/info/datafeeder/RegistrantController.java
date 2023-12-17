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
        return registrantService.getDetailsByOccupation(designation);
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
    public String performSearch(
            @RequestParam(name = "selectedColumn", required = false, defaultValue = "") String selectedColumn,
            @RequestParam(name = "searchKeyword", required = false, defaultValue = "") String searchKeyword,
            @RequestParam(name = "firstName", required = false, defaultValue = "") String firstName,
                                @RequestParam(name = "lastName", required = false, defaultValue = "") String lastName,
                                @RequestParam(name = "occupation", required = false, defaultValue = "") String occupation,
                                @RequestParam(name = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
            @RequestParam(name = "gothram", required = false, defaultValue = "") String gothram,
            @RequestParam(name = "set", required = false, defaultValue = "") String set,
            @RequestParam(name = "married", required = false, defaultValue = "") String married,
                                Model model) {
        System.out.println("searchKeyword: "+searchKeyword+" selectedColumn: "+selectedColumn);
        System.out.println("firstName: "+firstName+"lastName: "+lastName+"occupation: "+occupation+"phoneNumber: "+phoneNumber+"gothram: "+gothram+"set: "+set+"married: "+married);
        List<Registrant> searchResults = registrantService.searchKey(searchKeyword,selectedColumn);
//        List<Registrant> searchResults = registrantService.search(firstName,lastName,occupation,phoneNumber,gothram,set,married);
        model.addAttribute("results", searchResults);
        return "search"; // Assuming "search.html" is your Thymeleaf template for the search form
    }
}
