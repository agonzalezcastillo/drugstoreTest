package com.intendencia.drugstore.availability.service;

import com.intendencia.drugstore.availability.rest.client.RestClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DistrictInfo {

    private DistrictService districtService;

    private RestClient restClient;

    public List<String> getDistrictInfo(){
        return districtService.getDistrictsList(restClient.getDistrictById());
    }

}
