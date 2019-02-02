package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class GreetingController {

    private SimpMessagingTemplate template;

      @Autowired
        public GreetingController(SimpMessagingTemplate template) {
            this.template = template;
        }


    @RequestMapping(value="/hello", method=RequestMethod.POST)  
    public String greeting(@RequestBody String message) throws Exception {

        this.template.convertAndSend("/topic/greetings", message);  
        return "hello";
    }    
}
