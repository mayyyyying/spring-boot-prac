package hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
class PersonRestController {

	//private final PersonService personService;
	private final String hostName = System.getenv("HOSTNAME");

	//public PersonRestController(PersonService personService) {
	//	this.personService = personService;
	//}

	@GetMapping("/person/{id}")
	public String findPersonById(@PathVariable("id") Long id) {
		return "found person";
	}

	/**
	 * 探针检查响应类
	 * @return
	 */
	@GetMapping("/health")
	public String health() {
		return "OK";
	}

	@RequestMapping("/")
	public String ribbonPing(){
		//LOG.info("ribbonPing of {}", hostName);
		return hostName;
	}

	/**
	 * 返回hostname
	 * @return 当前应用所在容器的hostname.
	 */
	@RequestMapping("/name")
	public String getName() {
		return this.hostName
				+ ", "
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}
