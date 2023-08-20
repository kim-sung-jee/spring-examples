package sentinel;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@Slf4j
public class RedisSentinelTest {

    @Test()
    void 테스트(){
        RestTemplate restTemplete = new RestTemplate();
        restTemplete.getForObject("http://localhost:8080/hello",Object.class);
        while (true){
            try {
                Thread.sleep(1000); // 1초 마다 호출


              ResponseEntity<String> response = restTemplete.getForEntity("http://localhost:8080/name", String.class);
                log.info("### 테스트 결과 => status : {} | response : {}", response.getStatusCode() , response.getBody());

            }catch (Exception e){
                log.error("### 테스트 에러 발생 => {}", e.getMessage());
            }
        }
    }

}
