package cloud.distkv.service;

import java.util.List;
import java.util.Map;

/**
 * Distkv service.
 */
public interface DistkvService {

  /**
   * Execute a command.
   *
   * @param command command information.
   * @return Execute result.
   */
  String exec(String command);

  /**
   * Put a value with `str`.
   *
   * @param key The key to put.
   * @param value The value need put.
   */
  void strsPut(String key, String value);

  /**
   * Get a result with `str` by key.
   *
   * @param key The `str structure` key.
   * @return Result of  get operation.
   */
  String strsGet(String key);

  /**
   * Put values with `list`.
   *
   * @param key   The key to put.
   * @param values The list values.
   */
  void listsPut(String key, List<String> values);

  /**
   * This method will query a list value  with `list` by the key
   *
   * @param key Obtain a list value based on the key.
   * @return The list value.
   */
  List<String> listsGet(String key);

  /**
   * Get the value of the given index with `list` by the key.
   *
   * @param key The key of the list.
   * @param index The index that we want get the value.
   * @return The value at the given index from the list.
   */
  List<String> listsGet(String key, Integer index);

  /**
   * Get the values of the given range with `list` by the key.
   *
   * @param key The key of the list.
   * @param from The left index of the range.
   * @param end The right index of the range.
   * @return The values of the given range.
   */
  List<String> listsGet(String key, Integer from, Integer end);

  /**
   * Get a result with `dict` by key.
   *
   * @param key The `dict structure` key.
   * @return Result of  get operation.
   */
  Map<String, String> dictsGet(String key);

  /**
   * Get the item in the dict corresponding to the key.
   *
   * @param key The `dict structure` key.
   * @param itemKey The `str structure` itemKey.
   * @return Result of  getItem operation.
   */
  String dictsGetItem(String key, String itemKey);

  /**
   * Pop the item in the dict corresponding to the key.
   *
   * @param key The `dict structure` key.
   * @param itemKey ItemKey.
   * @return Result of  popItem operation.
   */
  String dictsPopItem(String key, String itemKey);


  /**
   * Drop a value by key.
   * @param key The key to drop.
   * @return Is drop success.
   */
  boolean drop(String key);


}
