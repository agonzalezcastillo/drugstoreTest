package com.intendencia.drugstore.availability.rest.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class RestClient {

    private final static Integer DISTRICT_ID = new Integer(7);

    public String getDrugstoresByDistrict(Integer districtId) {

        RestTemplate rt = new RestTemplate();
        String response;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Integer> map= new LinkedMultiValueMap<String, Integer>();
        map.add("id_region", 7);

        HttpEntity<MultiValueMap<String, Integer>> entityReq = new HttpEntity<>(map,headers);
        try {
            response = rt.exchange("https://farmanet.minsal.cl/maps/index.php/ws/getLocalesRegion",
                    HttpMethod.POST, entityReq,
                    String.class).getBody();
        }catch(HttpClientErrorException e){
            log.error("Has been an error consulting the drugstore API");
            throw new HttpClientErrorException(e.getStatusCode(), e.getStatusText());
        }
        log.info("api response", response);

        return response;
    }

    public String getDistrictById() {
        RestTemplate rt = new RestTemplate();
        String response;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Integer> map= new LinkedMultiValueMap<String, Integer>();
        map.add("reg_id", 7);

        HttpEntity<MultiValueMap<String, Integer>> entityReq = new HttpEntity<>(map,headers);

        try {
            response = rt.exchange("https://midastest.minsal.cl/farmacias/maps/index.php/utilidades/maps_obtener_comunas_por_regiones",
                    HttpMethod.POST, entityReq,
                    String.class).getBody();
        }catch(HttpClientErrorException e){
            log.error("Has been an error consulting the district API");
            throw new HttpClientErrorException(e.getStatusCode(), e.getStatusText());
        }

        log.info("api response", response);

        return response;
    }
}
