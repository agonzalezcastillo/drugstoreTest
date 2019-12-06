package com.intendencia.drugstore.availability.service.impl;

import com.intendencia.drugstore.availability.helper.StoreMapperHelper;
import com.intendencia.drugstore.availability.model.Store;
import com.intendencia.drugstore.availability.rest.client.RestClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DrugstoreServiceImplTest {

    @InjectMocks
    private DrugstoreServiceImpl drugstoreService;

    @Mock
    private RestClient restClient;

    @Mock
    private StoreMapperHelper storeMapperHelper;

    private Integer districtId;
    private String storeListJson;
    private List<Store> storeList;
    private Store st1;
    private Store st2;
    private List<Store> filteredStoreList;
    private String districtName;
    private String drugStoreName;

    @Before
    public void setUp() throws Exception {

         districtId = 7;
         districtName = "BUIN";
         drugStoreName = "AHUMADA";
         storeListJson = "[\n" +
                 "    {\n" +
                 "        \"local_nombre\": \"TORRES MPD\",\n" +
                 "        \"comuna_nombre\": \"RECOLETA\",\n" +
                 "        \"localidad_nombre\": \"RECOLETA\",\n" +
                 "        \"local_direccion\": \"AVENIDA EL SALTO 2972\",\n" +
                 "        \"local_telefono\": \"+560225053570\",\n" +
                 "        \"local_lat\": -33.3996351,\n" +
                 "        \"local_lng\": -70.62894990000001\n" +
                 "    },\n" +
                 "    {\n" +
                 "        \"local_nombre\": \"AHUMADA\",\n" +
                 "        \"comuna_nombre\": \"BUIN\",\n" +
                 "        \"localidad_nombre\": \"BUIN\",\n" +
                 "        \"local_direccion\": \"SAN MARTIN 174\",\n" +
                 "        \"local_telefono\": \"+560226313086\",\n" +
                 "        \"local_lat\": -33.732,\n" +
                 "        \"local_lng\": -70.735941\n" +
                 "    },";

         st1 = Store.builder()
                .districtName("BUIN")
                .lattitude(-40.5)
                .locationName("BUIN")
                .longitude(-50.5)
                .name("AHUMADA")
                .phone("123456")
                .storeAdress("CALLE 1")
                .build();

        st2 = Store.builder()
                .districtName("SANTIAGO")
                .lattitude(-40.5)
                .locationName("CENTRO")
                .longitude(-50.5)
                .name("AHUMADA")
                .phone("456789")
                .storeAdress("CALLE 2")
                .build();

        storeList = new ArrayList<>();

        storeList.add(st1);
        storeList.add(st2);
        filteredStoreList = new ArrayList<>();

    }

    @Test
    public void getStoreList() {
        when(restClient.getDrugstoresByDistrict(anyInt())).thenReturn(storeListJson);
        when(storeMapperHelper.mapStores(anyString())).thenReturn(storeList);
        storeList = drugstoreService.getStoreList(districtId);

        verify(restClient).getDrugstoresByDistrict(anyInt());
        verify(storeMapperHelper).mapStores(anyString());
        assertEquals(2 , storeList.size());
        assertEquals("BUIN" , storeList.get(0).getDistrictName());
        assertEquals("AHUMADA" , storeList.get(0).getName());
        assertEquals("SANTIAGO" , storeList.get(1).getDistrictName());
        assertEquals("AHUMADA" , storeList.get(1).getName());
    }

    @Test
    public void getByDistrictName() {
        when(drugstoreService.getStoreList(anyInt())).thenReturn(storeList);
        filteredStoreList = drugstoreService.getByDistrictName(districtName);

        assertEquals(1,filteredStoreList.size());
        assertEquals("BUIN",filteredStoreList.get(0).getDistrictName());
    }

    @Test
    public void getByDrugstoreName() {
        when(drugstoreService.getStoreList(anyInt())).thenReturn(storeList);
        filteredStoreList = drugstoreService.getByDrugstoreName(drugStoreName);

        assertEquals(2,filteredStoreList.size());
        assertEquals("BUIN",filteredStoreList.get(0).getDistrictName());
    }

    @Test
    public void getByDrugstoreNameAndDistrictName() {
        when(drugstoreService.getStoreList(anyInt())).thenReturn(storeList);
        filteredStoreList = drugstoreService.getByDrugstoreNameAndDistrictName(districtName,drugStoreName);

        assertEquals(1,filteredStoreList.size());
        assertEquals("BUIN",filteredStoreList.get(0).getDistrictName());
        assertEquals("AHUMADA",filteredStoreList.get(0).getName());
    }
}