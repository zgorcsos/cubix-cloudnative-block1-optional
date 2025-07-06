package hu.cubix.cloud.api;

import hu.cubix.cloud.model.DemoResponse;
import hu.cubix.cloud.service.DemoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private final DemoService service;

    private final String defaultMessage;

    public DemoController(DemoService service, @Value("${default-message}") String defaultMessage) {
        this.service = service;
        this.defaultMessage = defaultMessage;
    }

    @GetMapping("/message")
    public DemoResponse demoMessage(@RequestParam(required = false, name = "message") String message) {
        if (!StringUtils.hasText(message)) {
            message = defaultMessage;
        }
        return service.createDemoResponse(message);
    }

    @GetMapping("/cubix")
    public DemoResponse rootMessage(@RequestParam(name = "message") String message) {
        return service.createDemoResponse(message + " " + defaultMessage);
    }

}
