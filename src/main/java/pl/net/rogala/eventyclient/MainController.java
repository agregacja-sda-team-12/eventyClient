package pl.net.rogala.eventyclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

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
    public String home(Model model){
        EventDTO[] events = restTemplate.getForObject("http://localhost:8090/api/events", EventDTO[].class);
        List<EventDTO> eventDTOList = Arrays.asList(events);
//        System.out.println(eventDTOList);
        model.addAttribute("eventList", eventDTOList);
        return "home";
    }
}
