package de.sormuras.stratification;

import java.net.URL;
import java.net.URLClassLoader;

/** Version-aware utilities. */
public interface Utilities {
  /**
   * Get the {@link ClassLoader} providing access to just the classes of the runtime Java platform.
   *
   * @return the platform class loader
   */
  ClassLoader getPlatformClassLoader();

  /**
   * Create new {@link URLClassLoader} instance.
   *
   * @param name class loader name; or {@code null} if not named
   * @param parent the parent class loader for delegation
   * @param urls the URLs from which to load classes and resources
   * @return new instance of {@link URLClassLoader}
   */
  URLClassLoader newUrlClassLoader(String name, ClassLoader parent, URL... urls);
}
