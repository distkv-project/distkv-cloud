package cloud.distkv.service;

import cloud.distkv.dto.User;
import com.alibaba.fastjson.JSONArray;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

@Service("userService")
@Slf4j
public class UserService {

  @Value("classpath:users.json")
  private Resource localJsonFile;


  /**
   * Check if user exists by username.
   *
   * @param username username.
   * @return User info from local json file.
   */
  public User getUserByUsername(String username) {
    List<User> users = readUserFile();
    for (User user : users) {
      if (username.equals(user.getUsername())) {
        return user;
      }
    }
    return null;
  }


  /**
   * Check the validity of user information.
   *
   * @param user user info.
   * @return user info
   */
  public boolean authc(User user) {
    String pwd = user.getPassword();
    String uname = user.getUsername();
    List<User> users = readUserFile();
    for (User u : users) {
      if (pwd.equals(u.getPassword()) && uname.equals(u.getUsername())) {
        return true;
      }
    }
    return false;
  }


  public List<User> readUserFile() {
    List<User> users = null;
    try {
      String readContent = this.jsonRead(localJsonFile.getFile());
      users = JSONArray.parseArray(readContent, User.class);
    } catch (IOException e) {
      log.error("Failed to parse users.json");
    }
    return users;
  }

  private String jsonRead(File file) {
    Scanner scanner = null;
    StringBuilder buffer = new StringBuilder();
    try {
      scanner = new Scanner(file, "utf-8");
      while (scanner.hasNextLine()) {
        buffer.append(scanner.nextLine());
      }
    } catch (Exception e) {
      log.error("Failed to read users.json");
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
    return buffer.toString();
  }


}
