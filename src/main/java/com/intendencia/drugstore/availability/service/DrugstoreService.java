package com.intendencia.drugstore.availability.service;

import com.intendencia.drugstore.availability.model.Store;

import java.util.List;

public interface DrugstoreService {

    List<Store> getStoreList(Integer districtId);
    List<Store> getByDistrictName(String district);
    List<Store> getByDrugstoreName(String name);
    List<Store> getByDrugstoreNameAndDistrictName(String districtName, String drugstoreName);

}
