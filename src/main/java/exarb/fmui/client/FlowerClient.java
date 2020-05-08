package exarb.fmui.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FlowerClient {

    private final RestTemplate restTemplate;
    private final String userHost;

    @Autowired
    public FlowerClient(RestTemplate restTemplate, @Value("${userHost}") final String userHost) {
        this.restTemplate = restTemplate;
        this.userHost = userHost;
    }
}
