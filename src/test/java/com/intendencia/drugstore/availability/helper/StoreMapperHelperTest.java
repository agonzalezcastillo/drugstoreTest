package com.intendencia.drugstore.availability.helper;

import com.intendencia.drugstore.availability.model.Store;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StoreMapperHelperTest {

    @InjectMocks
    private StoreMapperHelper storeMapperHelper;

    private String districtName;
    private String drugStoreName;
    private Integer districtId;
    private String storeListJson;
    private List<Store> storeList;

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
                "    }]";
        storeList = new ArrayList<>();
    }

    @Test
    public void mapStores() {
        storeList = storeMapperHelper.mapStores(storeListJson);
        assertEquals(2 , storeList.size());
        assertEquals(new Double(-70.735941),storeList.get(1).getLongitude());
    }
}