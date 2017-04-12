package univ.lecture.riotapi.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.log4j.Log4j;

/**
 * Created by tchi on 2017. 4. 1..
 */
@RestController
@RequestMapping("/api/v1/")
@Log4j
public class RiotApiController {
   @Autowired
   private RestTemplate restTemplate;

   @Value("${riot.api.endpoint}")
   private String riotApiEndpoint;

   @Value("${riot.api.key}")
   private String riotApiKey;

   @RequestMapping(value = "/calc/{name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   public ObjectNode querySummoner2(@PathVariable("name") String exp) throws IOException {
      CalcApp calc = new CalcApp();
      String args[] = exp.split(" ");

      double result = calc.calc(args);

      ObjectNode reData = JsonNodeFactory.instance.objectNode(); 		// return data 생성
      reData.put("teamid", 2);
      reData.put("now", System.currentTimeMillis());
      reData.put("result", Double.toString(result));

      return reData;
   }
}
