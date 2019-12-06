package com.intendencia.drugstore.availability.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class DistrictServiceImplTest {

    @InjectMocks
    private DistrictServiceImpl districtService;

    private String html;
    private List<String> districtsList;

    @Before
    public void setUp() throws Exception {

        html = "<option value='0' selected>Elija Comuna</option>\n" +
                "<option value='82'>ALHUE</option>\n" +
                "<option value='83'>BUIN</option>\n" +
                "<option value='84'>CALERA DE TANGO</option>";

    }

    @Test
    public void getDistrictsList() {
        districtsList = districtService.getDistrictsList(html);
        assertEquals(3,districtsList.size());
        assertNotEquals("Elija Comuna", districtsList.get(0));
        assertEquals("CALERA DE TANGO" , districtsList.get(2));
    }
}