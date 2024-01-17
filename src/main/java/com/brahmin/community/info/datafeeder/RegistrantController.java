package com.brahmin.community.info.datafeeder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@Slf4j
public class RegistrantController {

//    private final RegistrantService registrantService;

//    private final ExcelService excelService;

//    @Autowired
//    public RegistrantController(RegistrantService registrantService, ExcelService excelService) {
//        this.registrantService = registrantService;
//        this.excelService = excelService;
//    }

//    @GetMapping("/details/phone")
//    @ResponseBody
//    public List<Registrant> getDetailsByPhoneNumber(@RequestParam("phone_number") long phoneNumber){
//        return registrantService.getDetailsByPhoneNumber(phoneNumber);
//    }
//
//    @GetMapping("/details/designation")
//    @ResponseBody
//    public List<Registrant> getDetailsByDesignation(@RequestParam("designation") String designation){
//        return registrantService.getDetailsByOccupation(designation);
//    }

    @GetMapping("/home")
    public String showHome() {
        return "home"; // Assuming "search.html" is your Thymeleaf template for the search form
    }

    @GetMapping("/gallery")
    public String showGallery() {
        return "gallery"; // Assuming "search.html" is your Thymeleaf template for the search form
    }

    @GetMapping("/upcoming-events")
    public String showupcomingevents() {
        return "upcomingevents"; // Assuming "search.html" is your Thymeleaf template for the search form
    }

    @GetMapping("/search")
    public String showSearchForm() {
        return "search"; // Assuming "search.html" is your Thymeleaf template for the search form
    }

//    @GetMapping("/uploadExcel")
//    @ResponseBody
//    public void uploadExcelFile() {
//        registrantService.readExcelAndSaveToDatabase();
//    }
//
//    @GetMapping("/details")
//    @ResponseBody
//    public List<Registrant> getDetails(){
//        return registrantService.getDetails();
//    }

//    @GetMapping("/details/name")
//    @ResponseBody
//    public List<Registrant> getDetailsByName(@RequestParam("first_name") String firstName,@RequestParam("last_name") String lastName){
//        return registrantService.getDetailsByName(firstName, lastName);
//    }

//    @PostMapping("/search")
//    public String performSearch(
//            @RequestParam(name = "selectedColumn", required = false, defaultValue = "") String selectedColumn,
//            @RequestParam(name = "searchKeyword", required = false, defaultValue = "") String searchKeyword,
//            @RequestParam(name = "firstName", required = false, defaultValue = "") String firstName,
//                                @RequestParam(name = "lastName", required = false, defaultValue = "") String lastName,
//                                @RequestParam(name = "occupation", required = false, defaultValue = "") String occupation,
//                                @RequestParam(name = "phoneNumber", required = false, defaultValue = "") String phoneNumber,
//            @RequestParam(name = "gothram", required = false, defaultValue = "") String gothram,
//            @RequestParam(name = "set", required = false, defaultValue = "") String set,
//            @RequestParam(name = "married", required = false, defaultValue = "") String married,
//                                Model model) {
//        System.out.println("searchKeyword: "+searchKeyword+" selectedColumn: "+selectedColumn);
//        System.out.println("firstName: "+firstName+"lastName: "+lastName+"occupation: "+occupation+"phoneNumber: "+phoneNumber+"gothram: "+gothram+"set: "+set+"married: "+married);
//        List<Registrant> searchResults = registrantService.searchKey(searchKeyword,selectedColumn);
//        model.addAttribute("results", searchResults);
//        return "search"; // Assuming "search.html" is your Thymeleaf template for the search form
//    }


}
