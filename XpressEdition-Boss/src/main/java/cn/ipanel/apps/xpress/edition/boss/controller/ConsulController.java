package cn.ipanel.apps.xpress.edition.boss.controller;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.Check;
import com.ecwid.consul.v1.health.model.HealthService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author luzh
 * Create: 上午9:48 2018/1/25
 * Modified By:
 * Description:
 */
@ApiIgnore
@RestController
public class ConsulController {

    @Value("${spring.cloud.consul.host}")
    private String consulHost;

    @Value("${spring.cloud.consul.port}")
    private Integer consulPort;

    @Value("${spring.application.name}")
    private String serviceId;

    private static Logger log = LoggerFactory.getLogger(ConsulController.class);

    private ConsulClient consulClient;

    @Autowired
    public ConsulController(ConsulClient consulClient) {
        this.consulClient = consulClient;
    }

    @ApiOperation(value = "清除无效实例", tags = "微服务相关")
    @RequestMapping(value = "/service/clean", method = RequestMethod.GET)
    public Boolean clean() {
        List<HealthService> response = consulClient.getHealthServices(serviceId, false, null).getValue();
        for(HealthService service : response) {
            // 创建一个用来剔除无效实例的ConsulClient，连接到无效实例注册的agent
            ConsulClient clearClient = new ConsulClient(consulHost, consulPort);
            service.getChecks().forEach(check -> {
                if(check.getStatus() != Check.CheckStatus.PASSING) {
                    log.info("unRegister : {}", check.getServiceId());
                    clearClient.agentServiceDeregister(check.getServiceId());
                }
            });
        }
        return true;
    }
}
