package pl.net.rogala.eventyclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    private ClientService clientService;

    @Autowired
    public MainController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String showListOfAllEvents(Model model) {
        model.addAttribute("eventList", clientService.prepareListOfEvents());
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
        model.addAttribute("filteredList", clientService.prepareListOfFilteredEvents(filterForm));
        return "filteredEvents";
    }
}



