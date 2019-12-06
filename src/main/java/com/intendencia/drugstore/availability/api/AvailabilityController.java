package com.intendencia.drugstore.availability.api;

import com.intendencia.drugstore.availability.model.Store;
import com.intendencia.drugstore.availability.service.DistrictInfo;
import com.intendencia.drugstore.availability.service.DrugstoreService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
@Api(value = "Events", description = "the metropolitan region drugstore availability API")
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:3001")
public class AvailabilityController {

    private DistrictInfo districtInfo;

    private DrugstoreService drugstoreService;

    @RequestMapping(value = "/districts" , produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<List<String>> getDistricts (){

        List<String> doc = districtInfo.getDistrictInfo();
        return new ResponseEntity<>(doc , HttpStatus.OK);

    }

    @RequestMapping(value = "/drugstores" , produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<Object> getDrugstores (){

        List<Store> doc = drugstoreService.getStoreList(7);
        return new ResponseEntity<>(doc , HttpStatus.OK);

    }

    @RequestMapping(value = "/drugstores/district" , produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<List<Store>> getDrugstoresByDistrict (
                                        @Valid @RequestParam(value = "district" , required = true) String district){

        List<Store> doc = drugstoreService.getByDistrictName(district);
        return new ResponseEntity<>(doc , HttpStatus.OK);

    }

    @RequestMapping(value = "/drugstores/name" , produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<List<Store>> getDrugstoresByName (
            @Valid @RequestParam(value = "name" , required = true) String name){

        List<Store> doc = drugstoreService.getByDrugstoreName(name);
        return new ResponseEntity<>(doc , HttpStatus.OK);

    }

    @RequestMapping(value = "/drugstores/district/name" , produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<List<Store>> getDrugstoresByDistrictAndName (
            @Valid @RequestParam(value = "districtName" , required = true) String districtName,
            @Valid @RequestParam(value = "drugstoreName" , required = true) String drugstoreName){

        List<Store> doc = drugstoreService.getByDrugstoreNameAndDistrictName(districtName,drugstoreName);
        return new ResponseEntity<>(doc , HttpStatus.OK);

    }



}
