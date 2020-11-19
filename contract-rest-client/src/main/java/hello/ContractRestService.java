package hello;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ContractRestService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackName" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") })
    public String getDataFromSpringCloudK8SProvider(){
        return this.restTemplate.getForObject("http://contract-rest-service/name", String.class);
    }

    public String getDataFromSpringCloudK8SProvider2(){
        return this.restTemplate.getForObject("http://contract-rest-service/health", String.class);
    }

    /**
     * 熔断时调用的方法
     */
    private String getFallbackName() {
        return "Fallback"
                + ", "
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}