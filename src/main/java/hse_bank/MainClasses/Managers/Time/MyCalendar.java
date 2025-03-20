package hse_bank.MainClasses.Managers.Time;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyCalendar implements Calendar {
    @Override
    public String getDate() {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return formatter.format(currentDate);
    }
}
