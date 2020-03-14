package cloud.distkv.utils;

import cloud.distkv.common.utils.JwtUtil;
import org.junit.Test;

public class JWTTest {

  @Test
  public void testSign() {
    String tansen = JwtUtil.sign("tansen", "123456");
    System.out.println(tansen);
  }
}
