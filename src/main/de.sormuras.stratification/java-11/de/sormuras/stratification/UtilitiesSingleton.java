package de.sormuras.stratification;

import java.net.URL;
import java.net.URLClassLoader;

public enum UtilitiesSingleton implements Utilities {
  INSTANCE;

  @Override
  public ClassLoader getPlatformClassLoader() {
    return ClassLoader.getPlatformClassLoader();
  }

  @Override
  public URLClassLoader newUrlClassLoader(String name, ClassLoader parent, URL... urls) {
    return new URLClassLoader(name, urls, parent);
  }
}
