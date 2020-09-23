package training.employees;

import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventStoreGatewayService {

    private JmsTemplate jmsTemplate;

    public void sendEvent(String message) {
        jmsTemplate.convertAndSend("eventsQueue", new EmployeeHasCreatedMessage(message));
    }

}
