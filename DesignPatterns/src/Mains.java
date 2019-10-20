package com.sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.FileSystemResource;

public class mains {

  private static String folder = "C:\\Users\\Abhishek\\Workspaces\\springboot_workspace\\IngestionFrameworkCommon\\IngestionCommon\\src\\test\\resources\\";
  private static String newYml = "application.yml";
  private static String newYmlCommon = "application-common.yml";

  private static String oldFile = "application-old.yml";

  public static void main(String[] args) throws FileNotFoundException {
    YamlPropertiesFactoryBean beanNew = new YamlPropertiesFactoryBean();
    beanNew.setResources(new FileSystemResource(new File(folder + newYmlCommon)),
        new FileSystemResource(new File(folder + newYml)));
    Properties propertiesNew = beanNew.getObject();
    Enumeration<String> enumer = (Enumeration<String>) propertiesNew.propertyNames();
    Map<String, Object> newMap = new HashMap<>();

    while (enumer.hasMoreElements()) {
      String key = enumer.nextElement();
      newMap.put(key, propertiesNew.getProperty(key));
    }
    newMap = resolve(newMap);

    YamlPropertiesFactoryBean beanOld = new YamlPropertiesFactoryBean();
    beanOld.setResources(new FileSystemResource(new File(folder + oldFile)));
    Properties propertiesOld = beanOld.getObject();
    Enumeration<String> enumerOld = (Enumeration<String>) propertiesOld.propertyNames();
    Map<String, Object> oldMap = new HashMap<>();

    while (enumerOld.hasMoreElements()) {
      String key = enumerOld.nextElement();
      oldMap.put(key, propertiesOld.getProperty(key));
    }

    for (Entry<String, Object> entry : oldMap.entrySet()) {
      if (newMap.containsKey(entry.getKey())) {
        if (!newMap.get(entry.getKey()).toString().equals(entry.getValue().toString())) {
          System.out.println("Property Value Changed : " + entry.getKey());
        }
      } else {
        System.out.println("Missing Property key : " + entry.getKey());
      }
    }

    Set<String> setNew = newMap.keySet();
    Set<String> setOld = oldMap.keySet();

    setNew.removeAll(setOld);

    Map<String, Object> finalNewMap = newMap;
    setNew.forEach(
        t -> System.out.println(" New Proeprty Introduced : " + t + " - " + finalNewMap.get(t)));
    System.out.println("All Done...................");

  }

  private static Map<String, Object> resolve(Map<String, Object> newMap) {
    List<String> redundant = new ArrayList<>();
    for (Map.Entry<String, Object> entry : newMap.entrySet()) {
      if (entry.getValue().toString().trim().startsWith("$") && entry.getValue().toString().trim()
          .endsWith("}")) {
        String key = entry.getValue().toString().replace("${", "");
        key = key
            .substring(0, key.length() - 1);
        boolean isDefault = key.endsWith(":");

        key = isDefault ? key.substring(0, key.length() - 1) : key;

        if (newMap.containsKey(key)) {
          newMap.put(entry.getKey(), newMap.get(key));
          redundant.add(key);
        } else if (isDefault) {
          newMap.put(entry.getKey(), "");
        } else {
          System.out.println("############### Issue with " + entry.getKey());
        }
      }
    }
    redundant.forEach(t -> newMap.remove(t));
    return newMap;
  }
}
