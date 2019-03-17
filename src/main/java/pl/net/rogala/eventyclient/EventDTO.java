package pl.net.rogala.eventyclient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDate;

@Getter
@Setter
@ToString
public class EventDTO {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate stopDate;
}
