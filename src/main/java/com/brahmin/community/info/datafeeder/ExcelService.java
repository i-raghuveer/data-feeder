package com.brahmin.community.info.datafeeder;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

@Service
@Slf4j
public class ExcelService {

    @Value("${excel.file.url}")
    private String excelFileUrl;

    private String personalAccessToken = "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";

    public void insertRegistrantData(Registrant registrant) {
        try {
            log.info("Registrant: {}, excelurl: {}", registrant, excelFileUrl);
            // Load the existing Excel file from GitHub
            Workbook workbook = loadWorkbook(excelFileUrl);

            // Get the first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            // Find the header row to determine column indexes
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
            log.info("headers: {}",headers);

            int firstNameIndex = headerRow.getCell(0).getColumnIndex();
            int lastNameIndex = headerRow.getCell(1).getColumnIndex();
            int phoneNumberIndex = headerRow.getCell(2).getColumnIndex();
            int addressIndex = headerRow.getCell(3).getColumnIndex();
            int dobIndex = headerRow.getCell(4).getColumnIndex();
            int regDateIndex = headerRow.getCell(5).getColumnIndex();

            // Create a new row and insert data under the corresponding columns
            Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
            newRow.createCell(firstNameIndex).setCellValue(registrant.getFirstName());
            newRow.createCell(lastNameIndex).setCellValue(registrant.getLastName());
            newRow.createCell(phoneNumberIndex).setCellValue(registrant.getPhoneNumber());
            newRow.createCell(addressIndex).setCellValue(registrant.getAddress());
            newRow.createCell(dobIndex).setCellValue(registrant.getDob());
            newRow.createCell(regDateIndex).setCellValue(registrant.getRegistrationDate());

            // Write the modified workbook back to the Excel file
            writeWorkbookToGitHub(workbook, excelFileUrl);

            // Close the workbook to release resources
            workbook.close();
        } catch (Exception e) {
            log.error("error: ",e);
        }
    }

    private Workbook loadWorkbook(String fileApiUrl) throws IOException, URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + personalAccessToken);
        headers.setAccept(Collections.singletonList(MediaType.valueOf("application/vnd.github.v3.raw")));

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(fileApiUrl));

        byte[] response = restTemplate.exchange(requestEntity, byte[].class).getBody();
        try (InputStream inputStream = new ByteArrayInputStream(response)) {
            return new XSSFWorkbook(inputStream);
        }
    }

    private void writeWorkbookToGitHub(Workbook workbook, String fileApiUrl) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            byte[] workbookBytes = outputStream.toByteArray();
            String base64Content = Base64.getEncoder().encodeToString(workbookBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + personalAccessToken);
            headers.setContentType(MediaType.APPLICATION_JSON);

            RestTemplate restTemplate = new RestTemplate();

            String currentSha = getShaForFile(fileApiUrl);

            String requestBody = String.format("{\"message\":\"Update Excel file\",\"content\":\"%s\",\"sha\":\"%s\"}", base64Content, currentSha);

            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            URI uri = new URI(fileApiUrl);
            log.info("uri: {}, request: {}",uri,requestEntity);

            restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, String.class);

        } catch (Exception e) {
            log.error("error: ",e);
        }
    }

    private String getShaForFile(String fileApiUrl) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + personalAccessToken);
        ResponseEntity<String> responseEntity = restTemplate.exchange(new URI(fileApiUrl), HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return responseEntity.getHeaders().getETag();
    }
}
