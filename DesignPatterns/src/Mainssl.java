package com.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.yaml.snakeyaml.Yaml;

public class Mainssl {

  private static String folder = "C:\\Users\\Abhishek\\Workspaces\\springboot_workspace\\IngestionFrameworkCommon\\IngestionCommon\\src\\test\\resources\\";
  private static String newYml = "application.yml";
  private static String newYmlCommon = "application-common.yml";

  private static String oldFile = "application-old.yml";

  public static void main(String[] args) throws FileNotFoundException {
    Yaml yml = new Yaml();
    InputStream cmnNewFile = new FileInputStream(new File(folder + newYmlCommon));
    InputStream newFile = new FileInputStream(new File(folder + newYml));
    InputStream newFileFull = new SequenceInputStream(cmnNewFile, newFile);

    Map<String, Object> properties = yml.load(newFileFull);
    System.out.println(properties);
    Map<String, Object> o1 = flattenMap(properties, "");

    for (Map.Entry<String, Object> entry : o1.entrySet()) {
      System.out.println(entry.getKey() + "  :  " + entry.getValue());
    }

    Yaml yml1 = new Yaml();
    InputStream oldYmlFile = new FileInputStream(new File(folder + oldFile));

    Map<String, Object> propertiesOld = yml1.load(oldYmlFile);
    Map<String, Object> o2 = flattenMap(propertiesOld, "");

    System.out.println("----------------------------------------");
    for (Map.Entry<String, Object> entry : o2.entrySet()) {
      System.out.println(entry.getKey() + "  :  " + entry.getValue());
    }
    System.out.println("----------------------------------------");
    for (Entry<String, Object> entry : o2.entrySet()) {
      if (o1.containsKey(entry.getKey())) {
        if (!o1.get(entry.getKey()).toString().equals(entry.getValue().toString())) {
          System.out.println("Property Value Changed : " + entry.getKey());
        }
      } else {
        System.out.println("Missing Property key : " + entry.getKey());
      }
    }

    Set<String> setNew = o1.keySet();
    Set<String> setOld = o2.keySet();

    setNew.removeAll(setOld);

    setNew.forEach(t -> System.out.println(" New Proeprty Introduced : " + t + " - " + o1.get(t)));
    System.out.println("All Done...................");
  }

  private static Map<String, Object> flattenMap(Map<String, Object> properties, String name) {
    Map<String, Object> newMap = new HashMap<>();
    Mapper mapper = new Mapper(new HashMap<String, Object>());
    mapper.getValue(properties, null);
    Map<String, Object> returnedMap = (Map<String, Object>) mapper.getMapper();
    for (Map.Entry<String, Object> entry1 : returnedMap.entrySet()) {
      newMap.put(entry1.getKey(), entry1.getValue());
    }

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

class Mapper {

  Map<String, Object> mapper;

  public Mapper(Map<String, Object> mapper) {
    this.mapper = mapper;
  }

  public Object getValue(Object entry,
      String name) {
    Object val = null;

    if (entry.getClass().getName().contains("Entry")) {
      val = getValue(((Entry<String, Object>) entry).getValue(),
          ((Map.Entry<String, Object>) entry).getKey());
    } else if (entry.getClass().getName().contains("LinkedHashMap")) {
      LinkedHashMap<String, Object> lnMap = ((LinkedHashMap<String, Object>) entry);
      Set<String> keySet = lnMap.keySet();
      for (String string : keySet) {
        String key = name == null ? string : name + "." + string;
        Object value = getValue(lnMap.get(string), key);
        if (!value.getClass().getName().contains("List") && !value.getClass().getName()
            .contains("Map")) {
          mapper
              .put(key,
                  getValue(lnMap.get(string), key));
        }
      }
    } else if (entry.getClass().getName().contains("List")) {
      for (int i = 0; i < ((List) entry).size(); i++) {
        val = getValue(((List) entry).get(i), name + ".[" + i + "]");
      }
    }
    return entry;
  }

  public Map<String, Object> getMapper() {
    return this.mapper;
  }


}
