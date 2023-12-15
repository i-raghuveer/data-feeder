package com.brahmin.community.info.datafeeder;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.*;

@Service
public class RegistrantService {

    private final RegistrantRepository registrantRepository;

    public RegistrantService(RegistrantRepository registrantRepository) {
        this.registrantRepository = registrantRepository;
    }

    public void readExcelAndSaveToDatabase() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("registration_details.xlsx");
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            // Get the header row
            Row headerRow = sheet.getRow(0);
            List<String> headers = new ArrayList<>();
            if (headerRow != null) {
                // Iterate through the cells of the header row
                Iterator<Cell> cellIterator = headerRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    headers.add(cell.getStringCellValue());
                }
            }

            // Process data rows using the header values obtained earlier
            Iterator<Row> rowIterator = sheet.iterator();
            // Skip the header row while iterating
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Create an object to store data according to headers
                Registrant registrant = new Registrant();

                // Iterate through cells of the current row
                Iterator<Cell> cellIterator = row.cellIterator();
                int columnIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String header = headers.get(columnIndex);

                    switch(header)  {
                        case "first_name" : registrant.setFirstName(cell.getStringCellValue());
                        case "last_name" : registrant.setLastName(cell.getStringCellValue());
                        case "address" : registrant.setAddress(cell.getStringCellValue());
                        case "designation" : registrant.setDesignation(cell.getStringCellValue());
                    }

                    if ("phone_number".equalsIgnoreCase(header)){
                        registrant.setPhoneNumber(
                                Long.parseLong(new DataFormatter().formatCellValue(cell)));

                    }

                    columnIndex++;
                }
                System.out.println("registrant: "+registrant);
                // Save the registrant object to the database
                registrantRepository.save(registrant);
            }

            workbook.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    public List<Registrant> getDetails(){
        return registrantRepository.findAll();
    }

    public List<Registrant> getDetailsByPhoneNumber(long phoneNumber){
        return registrantRepository.findByPhoneNumber(phoneNumber);
    }

    public List<Registrant> getDetailsByDesignation(String designation){
        return registrantRepository.findByDesignationContaining(designation);
    }

    public List<Registrant> search(String firstName, String lastName,String designation, String phoneNumber){

        if(!phoneNumber.isBlank()){
            return getDetailsByPhoneNumber(Long.parseLong(phoneNumber));
        } else if (!designation.isBlank()) {
            return getDetailsByDesignation(designation);
        } else if (!firstName.isBlank()) {
            return registrantRepository.findByFirstNameContaining(firstName);
        } else if (!lastName.isBlank()) {
            return registrantRepository.findByLastNameContaining(lastName);
        }

        return registrantRepository.
                findByFirstNameContainingOrLastNameContainingOrDesignationContainingOrPhoneNumber
                        (firstName,lastName,designation,(!phoneNumber.isBlank())?Long.parseLong(phoneNumber):0);
    }
}
