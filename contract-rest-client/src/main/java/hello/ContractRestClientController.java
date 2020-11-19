package hello;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ContractRestClientController {

    @Autowired
    private ContractRestService contractRestService;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 探针检查响应类
     * @return
     */
    @RequestMapping("/health")
    public String health() {
        return "OKKKK";
    }

    /**
     * 远程调用account-service提供的服务
     * @return 多次远程调返回的所有结果.
     */
    @RequestMapping("/account")
    public String account() {

        StringBuilder sbud = new StringBuilder();

        for(int i=0;i<10;i++){
            sbud.append(contractRestService.getDataFromSpringCloudK8SProvider())
                    .append("<br>");
        }

        return sbud.toString();
    }

    @RequestMapping("/account2")
    public String account2() {

        StringBuilder sbud = new StringBuilder();

        for(int i=0;i<10;i++){
            sbud.append(contractRestService.getDataFromSpringCloudK8SProvider2())
                    .append("<br>");
        }

        return sbud.toString();
    }

    /**
     * 返回远程调用的结果
     * @return
     */
    @RequestMapping("/getservicedetail")
    public String getservicedetail(
            @RequestParam(value = "servicename", defaultValue = "") String servicename) {
        return "Service [" + servicename + "]'s instance list : " + JSON.toJSONString(discoveryClient.getInstances(servicename));
    }

    /**
     * 返回发现的所有服务
     * @return
     */
    @RequestMapping("/services")
    public String services() {
        return this.discoveryClient.getServices().toString()
                + ", "
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
