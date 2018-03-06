package recursos;

import java.util.*;
import javax.swing.text.rtf.RTFEditorKit;
import java.text.*;
import java.io.*;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.*;
import javax.servlet.ServletContext;

public class Util {

  public void m() {
    StringReader r = new StringReader();
    String line = null;
    while ((line = r.nextLine()) != null) {
      if (line.length() > 0)
        System.out.println("Not empty!");
    }
  }

}
