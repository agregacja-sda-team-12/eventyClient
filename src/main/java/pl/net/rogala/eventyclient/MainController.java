package pl.net.rogala.eventyclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private RestTemplate restTemplate;

    @Autowired
    public MainController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/")
    public String showListOfAllEvents(Model model) {
        EventDTO[] events = restTemplate.getForObject("http://localhost:8090/api/events", EventDTO[].class);
        List<EventDTO> eventDTOList = Arrays.asList(events);
//        System.out.println(eventDTOList);
        model.addAttribute("eventList", eventDTOList);
        return "home";
    }

    @GetMapping("/filter")
    public String showFilterFormForDateRange(Model model) {
        model.addAttribute("filterForm", new FilterForm());
        return "filter";
    }

    @PostMapping("/filter")
    public String handleFilterForm(@ModelAttribute @Valid FilterForm filterForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/filter";
        }
        Map<String, String> params = new HashMap<>();
        params.put("startDate", filterForm.getStartDate().toString());
        params.put("stopDate", filterForm.getStopDate().toString());
        EventDTO[] events = restTemplate.getForObject("http://localhost:8090/api/events/{startDate},{stopDate}", EventDTO[].class, params);
        List<EventDTO> filteredEventDTOList = Arrays.asList(events);
        model.addAttribute("filteredList",filteredEventDTOList);
        return "filteredEvents";
    }
}



