package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@RibbonClient(name = "contract-rest-service", configuration = RibbonConfiguration.class)
public class ContractRestClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(ContractRestClientApplication.class, args);
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
}

//@RestController
//class MessageRestController {
//
//	@Autowired
//	private DiscoveryClient discoveryClient;
//
//	@Autowired
//	private ContractRestClientService contractRestClientService;
//
//	//@Autowired
//	//private FeignDemo demo;
//
//	private final RestTemplate restTemplate;
//
//	MessageRestController(RestTemplateBuilder restTemplateBuilder) {
//		this.restTemplate = restTemplateBuilder.build();
//	}
//
//
//	@RequestMapping("/list-discovered-services")
//	String indexService() {
//
//		List<String> services = discoveryClient.getServices();
//
//		StringBuilder sbud = new StringBuilder();
//
//		for (int i=0; i<services.size(); i++){
//			sbud.append(services.get(i)).append("<br>");
//		}
//		return sbud.toString();
//	}
//
//	@RequestMapping("/test")
//	String test(){
//		return "test passed";
//	}
//
//	@RequestMapping("/service1")
//	String respondPoint(){
//		final String contractRestClientServiceOutput = contractRestClientService.invokeService2();
//		return "service1 -> " + contractRestClientServiceOutput;
//	}
//
//	@RequestMapping("/message/{personId}")
//	String getMessage(@PathVariable("personId") Long personId) {
//		Person person = this.restTemplate.getForObject("http://10.101.26.84:8000/person/{personId}", Person.class, personId);
//		return "Hello " + person.getName();
//	}
//
//}
