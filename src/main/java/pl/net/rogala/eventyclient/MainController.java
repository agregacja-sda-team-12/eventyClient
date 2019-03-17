package pl.net.rogala.eventyclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    private RestTemplate restTemplate;

    @Autowired
    public MainController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/")
    public String showListOfAllEvents(Model model){
        EventDTO[] events = restTemplate.getForObject("http://localhost:8090/api/events", EventDTO[].class);
        List<EventDTO> eventDTOList = Arrays.asList(events);
//        System.out.println(eventDTOList);
        model.addAttribute("eventList", eventDTOList);
        return "home";
    }
    @GetMapping("/filter")
    public String showFilterFormForDateRange(Model model){
        model.addAttribute("filterForm", new FilterForm());
        return "filter";
    }

    @PostMapping("/filter")
    public String handleFilterForm(@ModelAttribute @Valid FilterForm filterForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/filter";
        }
        return "redirect: /filteredEvents";
    }
//TODO: get params from PostMapping("/filter") and put them into params of api's url
    @GetMapping("/filteredEvents")
    public String showFilteredEvents(Model model){
        EventDTO[] events = restTemplate.getForObject("http://localhost:8090/api/events/{startDate},{stopDate}", EventDTO[].class);
            List<EventDTO> filteredEventDTOList = Arrays.asList(events);
            model.addAttribute("filteredEvents", filteredEventDTOList);
            return "filtered";
        }
    }

