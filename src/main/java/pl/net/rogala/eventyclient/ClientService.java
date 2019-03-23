package pl.net.rogala.eventyclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientService {
    private RestTemplate restTemplate;

    @Autowired
    public ClientService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<EventDTO> prepareListOfEvents() {
        EventDTO[] events = restTemplate.getForObject("http://localhost:8090/api/events", EventDTO[].class);
        List<EventDTO> eventDTOList = Arrays.asList(events);
        return eventDTOList;
    }

    public List<EventDTO> prepareListOfFilteredEvents(FilterForm filterForm) {
        Map<String, String> params = new HashMap<>();
        params.put("startDate", filterForm.getStartDate().toString());
        params.put("stopDate", filterForm.getStopDate().toString());
        EventDTO[] events = restTemplate.getForObject("http://localhost:8090/api/events/{startDate},{stopDate}", EventDTO[].class, params);
        List<EventDTO> filteredEventDTOList = Arrays.asList(events);
        return filteredEventDTOList;
    }
}
