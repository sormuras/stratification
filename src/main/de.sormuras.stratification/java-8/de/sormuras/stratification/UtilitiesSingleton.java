package de.sormuras.stratification;

import java.net.URL;
import java.net.URLClassLoader;

public enum UtilitiesSingleton implements Utilities {
  INSTANCE;

  private final URLClassLoader platformClassLoader;

  UtilitiesSingleton() {
    this.platformClassLoader = new URLClassLoader(new URL[0], null);
    try {
      platformClassLoader.close();
    } catch (Exception e) {
      throw new AssertionError("Closing an empty URLClassLoader failed?!", e);
    }
  }

  @Override
  public ClassLoader getPlatformClassLoader() {
    return platformClassLoader;
  }

  @Override
  public URLClassLoader newUrlClassLoader(String name, ClassLoader parent, URL... urls) {
    return new URLClassLoader(urls, parent);
  }
}
