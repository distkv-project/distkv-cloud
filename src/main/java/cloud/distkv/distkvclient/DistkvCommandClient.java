package cloud.distkv.distkvclient;

import cloud.distkv.common.response.DistkvInitException;
import com.distkv.client.DefaultDistkvClient;
import com.distkv.client.commandlinetool.DistkvCommandExecutor;
import com.distkv.common.exception.DictKeyNotFoundException;
import com.distkv.common.exception.DistkvException;
import com.distkv.common.exception.DistkvListIndexOutOfBoundsException;
import com.distkv.common.exception.KeyNotFoundException;
import com.distkv.common.exception.SortedListMemberNotFoundException;
import com.distkv.common.exception.SortedListTopNumIsNonNegativeException;
import com.distkv.parser.DistkvParser;
import com.distkv.parser.po.DistkvParsedResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Distkv Client for command line.
 */
@Component(value = "distkvCommandClient")
public class DistkvCommandClient {

  private static String SERVER_ADDRESS;

  @Value(value = "${distkv.server-address}")
  public void setServerAddress(String serverAddress) {
    SERVER_ADDRESS = serverAddress;
  }

  private DistkvCommandExecutor distkvCommandExecutor;
  private DistkvParser distkvParser;

  public DistkvCommandClient() {
    distkvParser = new DistkvParser();

  }

  /**
   * Execute a command.
   *
   * @param command command information.
   * @return Execute result.
   */
  public String exec(String command) {
    initDistkvClient();
    //TODO (senyer) improve this.
    String result = "";
    try {
      DistkvParsedResult parsedResult = distkvParser.parse(command);
      result = distkvCommandExecutor.execute(parsedResult);
    } catch (DictKeyNotFoundException e) {
      result = ("errorCode: " + e.getErrorCode() + ";\n Detail: " + e.getMessage());
    } catch (DistkvListIndexOutOfBoundsException e) {
      result = ("errorCode: " + e.getErrorCode() + ";\n Detail: " + e.getMessage());
    } catch (KeyNotFoundException e) {
      result = ("errorCode: " + e.getErrorCode() + ";\n Detail: " + e.getMessage());
    } catch (SortedListMemberNotFoundException e) {
      result = ("errorCode: " + e.getErrorCode() + ";\n Detail: " + e.getMessage());
    } catch (SortedListTopNumIsNonNegativeException e) {
      result = ("errorCode: " + e.getErrorCode() + ";\n Detail: " + e.getMessage());
    } catch (DistkvException e) {
      result = ("errorCode: " + e.getErrorCode() + ";\n Detail: " + e.getMessage());
    }
    return result;
  }

  private void initDistkvClient() {
    try {
      // Init distkv related component.
      distkvCommandExecutor = new DistkvCommandExecutor(new DefaultDistkvClient(SERVER_ADDRESS));
    } catch (Exception e) {
      e.printStackTrace();
      throw new DistkvInitException("Distkv Init Failed", e);
    }
  }
}
