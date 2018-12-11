package sourcedg.util;

import java.util.Set;

public class Utils {

  public static <T> T first(final Set<T> set) {
    if (set == null || set.isEmpty())
      return null;
    return set.iterator().next();
  }

}
