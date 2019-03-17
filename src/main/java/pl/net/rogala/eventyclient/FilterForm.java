package pl.net.rogala.eventyclient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.MessageInterpolator;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Component
@Getter
@Setter
public class FilterForm {
    @NotNull(message = "Należy podać początek zakres!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull(message = "Należy podać koniec zakresu!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate stopDate;
}
