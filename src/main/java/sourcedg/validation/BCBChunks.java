package sourcedg.validation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class BCBChunks {

  public static final int CHUNKS = 40;
  private static int i = 1;

  public static void main(final String[] args) throws IOException {

    final List<String> filenames = new ArrayList<>();

    final String filename = "era_bcb_sample";
    final ZipFile zip = new ZipFile(filename + ".zip");

    final Enumeration<? extends ZipEntry> it = zip.entries();
    while (it.hasMoreElements()) {
      final ZipEntry e = it.nextElement();
      final String name = e.getName();
      if (!name.startsWith("__MACOSX") && name.endsWith(".java"))
        filenames.add(name);
    }

    zip.close();

    final List<List<String>> chunks =
        chopped(filenames, (int) Math.ceil(filenames.size() / CHUNKS));

    chunks.forEach(c -> {
      final String joined = String.join("\n", c);
      try {
        Files.write(Paths.get("validation_chunks/bcb-chunk" + i++ + ".txt"), joined.getBytes());
      } catch (final IOException e) {
        e.printStackTrace();
      }
      System.out.println(c.size());
    });

  }

  static <T> List<List<T>> chopped(final List<T> list, final int L) {
    final List<List<T>> parts = new ArrayList<List<T>>();
    final int N = list.size();
    for (int i = 0; i < N; i += L) {
      parts.add(new ArrayList<T>(list.subList(i, Math.min(N, i + L))));
    }
    return parts;
  }

}
