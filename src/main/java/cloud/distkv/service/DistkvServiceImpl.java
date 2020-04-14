package cloud.distkv.service;

import cloud.distkv.common.response.DistkvInitException;
import com.distkv.client.DefaultDistkvClient;
import com.distkv.client.DistkvClient;
import com.distkv.client.commandlinetool.DistkvCommandExecutor;
import com.distkv.common.exception.DictKeyNotFoundException;
import com.distkv.common.exception.DistkvException;
import com.distkv.common.exception.DistkvListIndexOutOfBoundsException;
import com.distkv.common.exception.KeyNotFoundException;
import com.distkv.common.exception.SortedListMemberNotFoundException;
import com.distkv.common.exception.SortedListTopNumIsNonNegativeException;
import com.distkv.parser.DistkvParser;
import com.distkv.parser.po.DistkvParsedResult;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Distkv Client for command line.
 */
@Component(value = "distkvCommandClient")
@Slf4j
public class DistkvServiceImpl implements DistkvService {

  private DistkvCommandExecutor distkvCommandExecutor;

  private DistkvParser distkvParser;

  private DistkvClient distkvClient;

  public DistkvServiceImpl(@Value(value = "${distkv.server-address}") String serverAddress) {
    distkvParser = new DistkvParser();
    try {
      // Init distkv related component.
      distkvClient = new DefaultDistkvClient(serverAddress);
      distkvCommandExecutor = new DistkvCommandExecutor(distkvClient);
    } catch (Exception e) {
      e.printStackTrace();
      throw new DistkvInitException("Distkv Init Failed", e);
    }
  }

  public String exec(String command) {
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

  @Override
  public void strsPut(String key, String value) {
    distkvClient.strs().put(key, value);
  }

  @Override
  public String strsGet(String key) {
    try {
      return distkvClient.strs().get(key);
    } catch (InvalidProtocolBufferException e) {
      log.error("Failed to get a  string value.", e);
      throw new DistkvException("Failed to get a  string value.", e);
    }
  }

  @Override
  public void listsPut(String key, List<String> values) {
    distkvClient.lists().put(key, values);
  }

  @Override
  public List<String> listsGet(String key) {
    return distkvClient.lists().get(key);
  }

  @Override
  public List<String> listsGet(String key, Integer index) {
    //TODO (senyer) finish it.
    return null;
  }

  @Override
  public List<String> listsGet(String key, Integer from, Integer end) {
    //TODO (senyer) finish it.
    return null;
  }

  @Override
  public Map<String, String> dictsGet(String key) {
    //TODO (senyer) finish it.
    return null;
  }

  @Override
  public String dictsGetItem(String key, String itemKey) {
    //TODO (senyer) finish it.
    return null;
  }

  @Override
  public String dictsPopItem(String key, String itemKey) {
    //TODO (senyer) finish it.
    return null;
  }

  @Override
  public boolean drop(String key) {
    //TODO (senyer) finish it.
    return false;
  }


}
