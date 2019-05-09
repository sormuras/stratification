package de.sormuras.stratification;

import java.net.URL;
import java.net.URLClassLoader;

/** Version-aware utilities. */
public interface Utilities {
  /**
   * Get the {@link ClassLoader} providing access to just the classes of the runtime Java platform.
   */
  ClassLoader getPlatformClassLoader();

  /** Create new {@link URLClassLoader} instance. */
  URLClassLoader newUrlClassLoader(String name, ClassLoader parent, URL... urls);
}
