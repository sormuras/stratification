package de.sormuras.stratification;

import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;

public class Stratum {

  public static Stratum of(String name, Path... paths) {
    URL[] urls = Arrays.stream(paths).map(Stratum::url).toArray(URL[]::new);
    return new Stratum(name, urls);
  }

  private final String name;
  private final URL[] urls;

  public Stratum(String name, URL... urls) {
    this.name = name;
    this.urls = urls;
  }

  public String getName() {
    return name;
  }

  public URL[] getUrls() {
    return urls;
  }

  private static URL url(Path path) {
    try {
      return path.toUri().toURL();
    } catch (MalformedURLException e) {
      throw new UncheckedIOException("Path inconvertible to URL: " + path, e);
    }
  }
}
