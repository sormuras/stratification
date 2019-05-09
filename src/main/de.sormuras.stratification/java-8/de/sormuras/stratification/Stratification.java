package de.sormuras.stratification;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Java class loader and module layer support.
 *
 * <pre><code>
 *     var loader = Stratification.isolated(List.of(...));
 *     var toolClass = loader.loadClass(toolClassName);
 *     if (toolClass.getClassLoader() != loader) {
 *       throw new IllegalStateException("Tool class not loaded in isolation! " + loader);
 *     }
 *     var toolInstance = (ToolProvider) toolClass.getConstructor().newInstance();
 *     return toolInstance.run(System.out, System.err, toolArguments);
 * </code></pre>
 */
public interface Stratification {

  ClassLoader newClassLoader(ClassLoader parent, List<Stratum> strata);

  static ClassLoader isolated(Stratum first, Stratum... more) {
    ClassLoader parent = UtilitiesSingleton.INSTANCE.getPlatformClassLoader();
    List<Stratum> strata = new ArrayList<>();
    strata.add(first);
    Collections.addAll(strata, more);
    return Default.ISOLATED.newClassLoader(parent, strata);
  }

  enum Default implements Stratification {
    ALL_IN_ONE {
      @Override
      public ClassLoader newClassLoader(ClassLoader parent, List<Stratum> strata) {
        List<URL> urls = new ArrayList<>();
        for (Stratum stratum : strata) {
          Collections.addAll(urls, stratum.getUrls());
        }
        URL[] array = new URL[urls.size()];
        urls.toArray(array);
        return utilities.newUrlClassLoader("mashed", parent, array);
      }
    },

    ISOLATED {
      @Override
      public ClassLoader newClassLoader(ClassLoader parent, List<Stratum> strata) {
        ClassLoader loader = parent;
        for (Stratum stratum : strata) {
          loader = utilities.newUrlClassLoader(stratum.getName(), loader, stratum.getUrls());
        }
        return loader;
      }
    };

    private static final Utilities utilities = UtilitiesSingleton.INSTANCE;
  }
}
