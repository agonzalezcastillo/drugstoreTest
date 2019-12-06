package com.intendencia.drugstore.availability.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Store {


    @JsonProperty("local_nombre")
    private String name;

    @JsonProperty("comuna_nombre")
    private String districtName;

    @JsonProperty("localidad_nombre")
    private String locationName;

    @JsonProperty("local_direccion")
    private String storeAdress;

    @JsonProperty("local_telefono")
    private String phone;

    @JsonProperty("local_lat")
    private Double lattitude;

    @JsonProperty("local_lng")
    private Double longitude;


    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", districtName='" + districtName + '\'' +
                ", locationName='" + locationName + '\'' +
                ", storeAdress='" + storeAdress + '\'' +
                ", phone='" + phone + '\'' +
                ", lattitude=" + lattitude +
                ", longitude=" + longitude +
                '}';
    }
}
