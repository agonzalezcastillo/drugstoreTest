package com.intendencia.drugstore.availability.service.impl;

import com.intendencia.drugstore.availability.helper.StoreMapperHelper;
import com.intendencia.drugstore.availability.model.Store;
import com.intendencia.drugstore.availability.rest.client.RestClient;
import com.intendencia.drugstore.availability.service.DrugstoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DrugstoreServiceImpl implements DrugstoreService {

    private RestClient restClient;

    private StoreMapperHelper storeMapperHelper;

    private final static Integer DISTRICT_ID = new Integer(7);

    public DrugstoreServiceImpl(RestClient restClient, StoreMapperHelper storeMapperHelper){
        this.restClient = restClient;
        this.storeMapperHelper = storeMapperHelper;
    }


    @Override
    public List<Store> getStoreList(Integer districtId) {
        String storesJson = restClient.getDrugstoresByDistrict(districtId);
        return storeMapperHelper.mapStores(storesJson);
    }

    @Override
    public List<Store> getByDistrictName(String district) {
        List<Store> storeList = this.getStoreList(DISTRICT_ID);
        return storeList.stream().filter( p -> p.getDistrictName().trim().equalsIgnoreCase(district.trim()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Store> getByDrugstoreName(String name) {
        List<Store> storeList = this.getStoreList(DISTRICT_ID);
        return storeList.stream().filter( p -> p.getName().trim().equalsIgnoreCase(name.trim()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Store> getByDrugstoreNameAndDistrictName(String districtName, String drugstoreName) {
        List<Store> storeList = this.getStoreList(DISTRICT_ID);

        return storeList.stream().filter( p -> p.getDistrictName().trim().equalsIgnoreCase(districtName.trim()))
                .filter(p -> p.getName().trim().equalsIgnoreCase(drugstoreName.trim()))
                .collect(Collectors.toList());
    }
}
