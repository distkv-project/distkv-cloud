package cloud.distkv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistkvCloudApplication {

  public static void main(String[] args) {
    try {
      SpringApplication.run(DistkvCloudApplication.class, args);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
