package com.intendencia.drugstore.availability.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intendencia.drugstore.availability.model.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class StoreMapperHelper {

    public List<Store> mapStores(String json) {

        ObjectMapper om = new ObjectMapper();
        List<Store> storeList = new ArrayList<>();
        try {
            storeList = om.readValue(json, new TypeReference<List<Store>>() {});
        } catch ( IOException e) {
            log.error(e.getMessage());
        }

        return storeList;
    }
}
