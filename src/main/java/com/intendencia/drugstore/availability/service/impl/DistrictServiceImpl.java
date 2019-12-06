package com.intendencia.drugstore.availability.service.impl;

import com.intendencia.drugstore.availability.service.DistrictService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DistrictServiceImpl implements DistrictService {

    @Override
    public List<String> getDistrictsList(String html) {

        Document doc = Jsoup.parse(html);
        Elements options = doc.select("option");
        List<String> districts = new ArrayList<>();

        options.stream().filter( e -> !e.text().equalsIgnoreCase("Elija Comuna")).collect(Collectors.toList()).forEach(
                p -> districts.add(p.text())
        );

        return districts;
    }
}
