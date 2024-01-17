//package com.brahmin.community.info.datafeeder;
//
//import jakarta.annotation.PostConstruct;
//import org.apache.poi.ss.usermodel.*;
//import org.springframework.stereotype.Service;
//
//import java.io.InputStream;
//import java.util.Iterator;
//import java.util.*;
//
//@Service
//public class RegistrantService {
//
//    private final RegistrantRepository registrantRepository;
//
//    public RegistrantService(RegistrantRepository registrantRepository) {
//        this.registrantRepository = registrantRepository;
//    }
//
//    @PostConstruct
//    public void readExcelAndSaveToDatabase() {
//        try {
//            ClassLoader classLoader = getClass().getClassLoader();
//            InputStream inputStream = classLoader.getResourceAsStream("registration_details.xlsx");
//            Workbook workbook = WorkbookFactory.create(inputStream);
//
//            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
//
//            // Get the header row
//            Row headerRow = sheet.getRow(0);
//            List<String> headers = new ArrayList<>();
//            if (headerRow != null) {
//                // Iterate through the cells of the header row
//                Iterator<Cell> cellIterator = headerRow.cellIterator();
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    headers.add(cell.getStringCellValue());
//                }
//            }
//            System.out.println("headers: "+headers);
//            // Process data rows using the header values obtained earlier
//            Iterator<Row> rowIterator = sheet.iterator();
//            // Skip the header row while iterating
//            if (rowIterator.hasNext()) {
//                rowIterator.next();
//            }
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//
//                // Create an object to store data according to headers
//                Registrant registrant = new Registrant();
//
//                // Iterate through cells of the current row
//                Iterator<Cell> cellIterator = row.cellIterator();
//                DataFormatter formatter=new DataFormatter();
//                int columnIndex = 0;
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    String header = headers.get(columnIndex);
//                    System.out.println("header: "+header);
//                    switch(header)  {
//                        case "first_name" : registrant.setFirstName(cell.getStringCellValue()); break;
//                        case "last_name" : registrant.setLastName(cell.getStringCellValue()); break;
//                        case "address" : registrant.setAddress(cell.getStringCellValue()); break;
//                        case "occupation" : registrant.setOccupation(cell.getStringCellValue()); break;
//                        case "care_of" : registrant.setCareOf(cell.getStringCellValue()); break;
//                        case "relation" : registrant.setRelation(cell.getStringCellValue()); break;
//                        case "dob" : registrant.setDob(formatter.formatCellValue(cell)); break;
//                        case "registration_date" : registrant.setRegistrationDate(formatter.formatCellValue(cell)); break;
//                        case "gothram" : registrant.setGothram(cell.getStringCellValue()); break;
//                        case "native_place" : registrant.setNativePlace(cell.getStringCellValue()); break;
//                        case "set" : registrant.setSet(cell.getStringCellValue()); break;
//                        case "sub_set" : registrant.setSubSet(formatter.formatCellValue(cell)); break;
//                        case "married" : registrant.setMarried(cell.getStringCellValue()); break;
//                        case "phone_number" : registrant.setPhoneNumber(
//                                Long.parseLong(formatter.formatCellValue(cell))); break;
//                    }
//
//                    columnIndex++;
//                }
//                System.out.println("registrant: "+registrant);
//                // Save the registrant object to the database
//                registrantRepository.save(registrant);
//            }
//
//            workbook.close();
//            inputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle exceptions
//        }
//    }
//
//    public List<Registrant> getDetails(){
//        return registrantRepository.findAll();
//    }
//
//    public List<Registrant> getDetailsByPhoneNumber(long phoneNumber){
//        return registrantRepository.findByPhoneNumber(phoneNumber);
//    }
//
//    public List<Registrant> getDetailsByOccupation(String designation){
//        return registrantRepository.findByOccupationContainingIgnoreCase(designation);
//    }
//
//    public List<Registrant> searchKey(String searchKeyword, String selectedColumn){
//
//        System.out.println("searchKeyword: "+searchKeyword);
//
//        switch (selectedColumn){
//            case "phoneNumber": return getDetailsByPhoneNumber(Long.parseLong(searchKeyword));
//            case "occupation": return getDetailsByOccupation(searchKeyword);
//            case "firstName": return registrantRepository.findByFirstNameContainingIgnoreCase(searchKeyword);
//            case "lastName": return registrantRepository.findByLastNameContainingIgnoreCase(searchKeyword);
//            case "gothram": return registrantRepository.findByGothramContainingIgnoreCase(searchKeyword);
//            case "set": return registrantRepository.findBySetContainingIgnoreCase(searchKeyword);
//            case "married": return registrantRepository.findByMarriedContainingIgnoreCase(searchKeyword);
//            case "address": return registrantRepository.findByAddressContainingIgnoreCase(searchKeyword);
//            default: return registrantRepository.findAll();
//    }
//}
//
//    public List<Registrant> search(String firstName, String lastName,String occupation, String phoneNumber, String gothram, String set, String married){
//
//        System.out.println("firstName: "+firstName+"lastName: "+lastName+"occupation: "+occupation+"phoneNumber: "+phoneNumber+"gothram: "+gothram+"set: "+set+"married: "+married);
//
//        if(phoneNumber!=null && !phoneNumber.isBlank()){
//            return getDetailsByPhoneNumber(Long.parseLong(phoneNumber));
//        } else if (!occupation.isBlank()) {
//            return getDetailsByOccupation(occupation);
//        } else if (!firstName.isBlank()) {
//            return registrantRepository.findByFirstNameContainingIgnoreCase(firstName);
//        } else if (!lastName.isBlank()) {
//            return registrantRepository.findByLastNameContainingIgnoreCase(lastName);
//        } else if (!gothram.isBlank()) {
//            return registrantRepository.findByGothramContainingIgnoreCase(gothram);
//        } else if (!set.isBlank()) {
//            return registrantRepository.findBySetContainingIgnoreCase(set);
//        } else if (!married.isBlank()) {
//            return registrantRepository.findByMarriedContainingIgnoreCase(married);
//        }
//
//        return registrantRepository.
//                findByFirstNameContainingOrLastNameContainingOrOccupationContainingOrPhoneNumber
//                        (firstName,lastName,occupation,(!phoneNumber.isBlank())?Long.parseLong(phoneNumber):0);
//    }
//}
