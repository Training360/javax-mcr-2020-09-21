package training.employees;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressesGatewayService {

    private RestTemplate restTemplate;

    private String url;

    public AddressesGatewayService(RestTemplateBuilder restTemplateBuilder,
                                   @Value("${addresses.url}") String url
                                   ) {
        restTemplate = restTemplateBuilder.build();
        this.url = url;
    }

    public Address getAddressByName(String name) {
        return restTemplate.getForObject(url,
                Address.class, name);
    }
}
