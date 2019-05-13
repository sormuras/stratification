package modular.tests;

import static org.junit.jupiter.api.Assertions.assertSame;

import de.sormuras.stratification.UtilitiesSingleton;
import org.junit.jupiter.api.Test;

class Tests {

  @Test
  void testUtilities() {
    var utilities = UtilitiesSingleton.INSTANCE;
    if (Runtime.version().feature() >= 11) {
      assertSame(ClassLoader.getPlatformClassLoader(), utilities.getPlatformClassLoader());
    }
  }

}
