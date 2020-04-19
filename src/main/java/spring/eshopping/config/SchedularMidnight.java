package spring.eshopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.eshopping.utils.SendEmail;

@Component
public class SchedularMidnight {

    @Autowired
    SendEmail sendEmail;

    @Scheduled(cron = "0 0 0 * * *",zone = "Indian/Maldives")
    public void sendEmailToSeller() {
        sendEmail.sendEmail("ACCEPTED/REJECTED","SOME DETAILS","sellerEmail");
    }
}
