package hse_bank;

import hse_bank.MainClasses.Coordinator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HseBankApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HseBankApplication.class, args);
        Coordinator coordinator = context.getBean(Coordinator.class);
        coordinator.coordinate();
    }

}
