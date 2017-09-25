import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import org.w3c.dom.Document;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.text.*;

public class JavaNZB {

    public static int searchCount = 0;

    public static String hellaQueueDir = "", newzbinUsr = "", newzbinPass = "";

    public static void main(String[] args) {
        SeriesFile seriesFile = new SeriesFile();
        seriesFile.ReadSeriesFile();
        ReadConfig();
        Compare(seriesFile);
    }

    public static void Compare(SeriesFile series) {
        DateFormat dateformat = new SimpleDateFormat("MMM/dd/yyyy");
        Calendar c1 = Calendar.getInstance();
        Date now = new Date();
        for (XMLShowInfo xmldata : series.xmlSeriesInfo) {
            TVRageShowInfo tvrage = new TVRageShowInfo(xmldata.showName, xmldata.searchBy);
            System.out.println("Show: " + tvrage.showName);
            System.out.println("Episode on disk: " + xmldata.season + "x" + xmldata.episode);
            if (tvrage.latestEpisodeNum.equals("")) System.out.println("Latest episode: Not posted"); else System.out.println("Latest episode:  " + tvrage.latestSeasonNum + "x" + tvrage.latestEpisodeNum + " \"" + tvrage.latestTitle + "\" airs on " + tvrage.latestAirDate);
            if (tvrage.nextEpisodeNum.equals("")) {
                System.out.println("Next episode: Not posted");
                xmldata.next = "Not posted";
            } else {
                System.out.println("Next episode:    " + tvrage.nextSeasonNum + "x" + tvrage.nextEpisodeNum + " \"" + tvrage.nextTitle + "\" airs on " + tvrage.nextAirDate + " " + tvrage.airTime);
                xmldata.next = tvrage.nextSeasonNum + "x" + tvrage.nextEpisodeNum + " \"" + tvrage.nextTitle + "\" airs on " + tvrage.airTime + " " + tvrage.nextAirDate;
            }
            System.out.println("Status: " + tvrage.status);
            System.out.println();
            if (!tvrage.latestEpisodeNum.equals("")) {
                if ((Integer.parseInt(xmldata.season) == Integer.parseInt(tvrage.latestSeasonNum)) && (Integer.parseInt(xmldata.episode) < Integer.parseInt(tvrage.latestEpisodeNum)) || (Integer.parseInt(xmldata.season) < Integer.parseInt(tvrage.latestSeasonNum))) {
                    try {
                        DateFormat airDateFormat = new SimpleDateFormat("MMM/dd/yyyy h:mm a");
                        Date epdate = airDateFormat.parse(tvrage.latestAirDate + tvrage.airTimeHour);
                        c1.setTime(epdate);
                        Date latestAirTime = c1.getTime();
                        c1.add(Calendar.DAY_OF_YEAR, 14);
                        Date twoWeeks = c1.getTime();
                        if (now.compareTo(latestAirTime) == 1 && now.compareTo(twoWeeks) == -1) {
                            xmldata = NzbSearch(tvrage, xmldata, 0);
                            continue;
                        } else {
                            if (now.compareTo(twoWeeks) == 1) {
                                System.out.println("--The latest episode search hasn't found anything for two weeks--");
                                System.out.println();
                                continue;
                            }
                        }
                    } catch (ParseException e) {
                        continue;
                    }
                }
            }
            if (!tvrage.nextEpisodeNum.equals("")) {
                if ((Integer.parseInt(xmldata.season) == Integer.parseInt(tvrage.nextSeasonNum)) && (Integer.parseInt(xmldata.episode) < Integer.parseInt(tvrage.nextEpisodeNum)) || (Integer.parseInt(xmldata.season) < Integer.parseInt(tvrage.nextSeasonNum))) {
                    try {
                        DateFormat airDateFormat = new SimpleDateFormat("MMM/dd/yyyy h:mm a");
                        Date epdate = airDateFormat.parse(tvrage.nextAirDate + tvrage.airTimeHour);
                        c1.setTime(epdate);
                        c1.add(Calendar.HOUR, -3);
                        Date nextAirTime = c1.getTime();
                        c1.add(Calendar.DAY_OF_YEAR, 14);
                        Date twoWeeks = c1.getTime();
                        if (now.compareTo(nextAirTime) == 1 && now.compareTo(twoWeeks) == -1) {
                            xmldata = NzbSearch(tvrage, xmldata, 1);
                            continue;
                        } else {
                            if (now.compareTo(twoWeeks) == 1) {
                                System.out.println("--The next episode search hasn't found anything for two weeks--");
                                System.out.println();
                                continue;
                            }
                        }
                    } catch (ParseException e) {
                        continue;
                    }
                }
            }
            System.out.println("--Latest episode already downloaded--");
            System.out.println();
        }
        series.UpdateSeriesFile();
        System.out.println("Finished");
    }

    public static XMLShowInfo NzbSearch(TVRageShowInfo tvrage, XMLShowInfo xmldata, int latestOrNext) {
        String newzbin_query = "", csvData = "", hellaQueueDir = "", newzbinUsr = "", newzbinPass = "";
        String[] tmp;
        DateFormat tvRageDateFormat = new SimpleDateFormat("MMM/dd/yyyy");
        DateFormat tvRageDateFormatFix = new SimpleDateFormat("yyyy-MM-dd");
        newzbin_query = "?q=" + xmldata.showName + "+";
        if (latestOrNext == 0) {
            if (xmldata.searchBy.equals("ShowName Season x Episode")) newzbin_query += tvrage.latestSeasonNum + "x" + tvrage.latestEpisodeNum; else if (xmldata.searchBy.equals("Showname SeriesNum")) newzbin_query += tvrage.latestSeriesNum; else if (xmldata.searchBy.equals("Showname YYYY-MM-DD")) {
                try {
                    Date airTime = tvRageDateFormat.parse(tvrage.latestAirDate);
                    newzbin_query += tvRageDateFormatFix.format(airTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if (xmldata.searchBy.equals("Showname EpisodeTitle")) newzbin_query += tvrage.latestTitle;
        } else {
            if (xmldata.searchBy.equals("ShowName Season x Episode")) newzbin_query += tvrage.nextSeasonNum + "x" + tvrage.nextEpisodeNum; else if (xmldata.searchBy.equals("Showname SeriesNum")) newzbin_query += tvrage.nextSeriesNum; else if (xmldata.searchBy.equals("Showname YYYY-MM-DD")) {
                try {
                    Date airTime = tvRageDateFormat.parse(tvrage.nextAirDate);
                    newzbin_query += tvRageDateFormatFix.format(airTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if (xmldata.searchBy.equals("Showname EpisodeTitle")) newzbin_query += tvrage.nextTitle;
        }
        newzbin_query += "&searchaction=Search";
        newzbin_query += "&fpn=p";
        newzbin_query += "&category=8category=11";
        newzbin_query += "&area=-1";
        newzbin_query += "&u_nfo_posts_only=0";
        newzbin_query += "&u_url_posts_only=0";
        newzbin_query += "&u_comment_posts_only=0";
        newzbin_query += "&u_v3_retention=1209600";
        newzbin_query += "&ps_rb_language=" + xmldata.language;
        newzbin_query += "&sort=ps_edit_date";
        newzbin_query += "&order=desc";
        newzbin_query += "&areadone=-1";
        newzbin_query += "&feed=csv";
        newzbin_query += "&ps_rb_video_format=" + xmldata.format;
        newzbin_query = newzbin_query.replaceAll(" ", "%20");
        System.out.println("http://v3.newzbin.com/search/" + newzbin_query);
        try {
            URL url = new URL("http://v3.newzbin.com/search/" + newzbin_query);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            csvData = in.readLine();
            if (csvData != null) {
                JavaNZB.searchCount++;
                if (searchCount == 6) {
                    searchCount = 0;
                    System.out.println("Sleeping for 60 seconds");
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                tmp = csvData.split(",");
                tmp[2] = tmp[2].substring(1, tmp[2].length() - 1);
                tmp[3] = tmp[3].substring(1, tmp[3].length() - 1);
                Pattern p = Pattern.compile("[\\\\</:>?\\[|\\]\"]");
                Matcher matcher = p.matcher(tmp[3]);
                tmp[3] = matcher.replaceAll(" ");
                tmp[3] = tmp[3].replaceAll("&", "and");
                URLConnection urlConn;
                DataOutputStream printout;
                url = new URL("http://v3.newzbin.com/api/dnzb/");
                urlConn = url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setUseCaches(false);
                urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                printout = new DataOutputStream(urlConn.getOutputStream());
                String content = "username=" + JavaNZB.newzbinUsr + "&password=" + JavaNZB.newzbinPass + "&reportid=" + tmp[2];
                printout.writeBytes(content);
                printout.flush();
                printout.close();
                BufferedReader nzbInput = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                String format = "";
                if (xmldata.format.equals("17")) format = " Xvid";
                if (xmldata.format.equals("131072")) format = " x264";
                if (xmldata.format.equals("2")) format = " DVD";
                if (xmldata.format.equals("4")) format = " SVCD";
                if (xmldata.format.equals("8")) format = " VCD";
                if (xmldata.format.equals("32")) format = " HDts";
                if (xmldata.format.equals("64")) format = " WMV";
                if (xmldata.format.equals("128")) format = " Other";
                if (xmldata.format.equals("256")) format = " ratDVD";
                if (xmldata.format.equals("512")) format = " iPod";
                if (xmldata.format.equals("1024")) format = " PSP";
                File f = new File(JavaNZB.hellaQueueDir, tmp[3] + format + ".nzb");
                BufferedWriter out = new BufferedWriter(new FileWriter(f));
                String str;
                System.out.println("--Downloading " + tmp[3] + format + ".nzb" + " to queue directory--");
                while (null != ((str = nzbInput.readLine()))) out.write(str);
                nzbInput.close();
                out.close();
                if (latestOrNext == 0) {
                    xmldata.episode = tvrage.latestEpisodeNum;
                    xmldata.season = tvrage.latestSeasonNum;
                } else {
                    xmldata.episode = tvrage.nextEpisodeNum;
                    xmldata.season = tvrage.nextSeasonNum;
                }
            } else System.out.println("No new episode posted");
            System.out.println();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
            System.out.println("IO Exception from NzbSearch");
        }
        return xmldata;
    }

    public static void ReadConfig() {
        try {
            String tmp;
            BufferedReader config = new BufferedReader(new FileReader("JavaNZB.config"));
            tmp = config.readLine();
            JavaNZB.hellaQueueDir = tmp.substring(9, tmp.length());
            tmp = config.readLine();
            JavaNZB.newzbinUsr = tmp.substring(16, tmp.length());
            tmp = config.readLine();
            JavaNZB.newzbinPass = tmp.substring(16, tmp.length());
            config.close();
        } catch (IOException e) {
            System.out.println("IO Exception from config file");
        }
    }
}

class TVRageShowInfo {

    String usrShowName = "", showName = "", showURL = "", latestTitle = "", latestAirDate = "", latestSeriesNum = "";

    String nextTitle = "", nextAirDate = "", nextSeriesNum = "", status = "", airTime = "";

    String nextSeasonNum = "", nextEpisodeNum = "", latestSeasonNum = "", latestEpisodeNum = "", airTimeHour = "";

    public TVRageShowInfo(String xmlShowName, String xmlSearchBy) {
        String[] tmp, tmp2;
        String line = "";
        this.usrShowName = xmlShowName;
        try {
            URL url = new URL("http://www.tvrage.com/quickinfo.php?show=" + xmlShowName.replaceAll(" ", "%20"));
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            while ((line = in.readLine()) != null) {
                tmp = line.split("@");
                if (tmp[0].equals("Show Name")) showName = tmp[1];
                if (tmp[0].equals("Show URL")) showURL = tmp[1];
                if (tmp[0].equals("Latest Episode")) {
                    StringTokenizer st = new StringTokenizer(tmp[1], "^");
                    for (int i = 0; st.hasMoreTokens(); i++) {
                        if (i == 0) {
                            tmp2 = st.nextToken().split("x");
                            latestSeasonNum = tmp2[0];
                            latestEpisodeNum = tmp2[1];
                            if (latestSeasonNum.charAt(0) == '0') latestSeasonNum = latestSeasonNum.substring(1);
                        } else if (i == 1) latestTitle = st.nextToken().replaceAll("&", "and"); else latestAirDate = st.nextToken();
                    }
                }
                if (tmp[0].equals("Next Episode")) {
                    StringTokenizer st = new StringTokenizer(tmp[1], "^");
                    for (int i = 0; st.hasMoreTokens(); i++) {
                        if (i == 0) {
                            tmp2 = st.nextToken().split("x");
                            nextSeasonNum = tmp2[0];
                            nextEpisodeNum = tmp2[1];
                            if (nextSeasonNum.charAt(0) == '0') nextSeasonNum = nextSeasonNum.substring(1);
                        } else if (i == 1) nextTitle = st.nextToken().replaceAll("&", "and"); else nextAirDate = st.nextToken();
                    }
                }
                if (tmp[0].equals("Status")) status = tmp[1];
                if (tmp[0].equals("Airtime") && tmp.length > 1) {
                    airTime = tmp[1];
                }
            }
            if (airTime.length() > 10) {
                tmp = airTime.split("at");
                airTimeHour = tmp[1];
            }
            in.close();
            if (xmlSearchBy.equals("Showname SeriesNum")) {
                url = new URL(showURL);
                in = new BufferedReader(new InputStreamReader(url.openStream()));
                while ((line = in.readLine()) != null) {
                    if (line.indexOf("<b>Latest Episode: </b>") > -1) {
                        tmp = line.split("'>");
                        if (tmp[5].indexOf(':') > -1) {
                            tmp = tmp[5].split(":");
                            latestSeriesNum = tmp[0];
                        }
                    } else if (line.indexOf("<b>Next Episode: </b>") > -1) {
                        tmp = line.split("'>");
                        if (tmp[3].indexOf(':') > -1) {
                            tmp = tmp[3].split(":");
                            nextSeriesNum = tmp[0];
                        }
                    }
                }
                in.close();
            }
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
    }
}

class SeriesFile {

    ArrayList<XMLShowInfo> xmlSeriesInfo;

    public void ReadSeriesFile() {
        xmlSeriesInfo = new ArrayList<XMLShowInfo>();
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("JavaNZB.series"));
            doc.getDocumentElement().normalize();
            NodeList listOfShowInfo = doc.getElementsByTagName("Show");
            ArrayList<String> elementNames = new ArrayList<String>();
            elementNames.add("Name");
            elementNames.add("SearchBy");
            elementNames.add("Episode");
            elementNames.add("Format");
            elementNames.add("Language");
            elementNames.add("Next");
            elementNames.add("Season");
            for (int s = 0; s < listOfShowInfo.getLength(); s++) {
                Node showNode = listOfShowInfo.item(s);
                XMLShowInfo xmlShowInfo = new XMLShowInfo();
                if (showNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element firstPersonElement = (Element) showNode;
                    for (String CurElementName : elementNames) {
                        NodeList NameList = firstPersonElement.getElementsByTagName(CurElementName);
                        Element NameElement = (Element) NameList.item(0);
                        NodeList textFNList = NameElement.getChildNodes();
                        if (textFNList.getLength() > 0) {
                            if (CurElementName.equals("Name")) xmlShowInfo.showName = ((Node) textFNList.item(0)).getNodeValue().trim();
                            if (CurElementName.equals("SearchBy")) xmlShowInfo.searchBy = ((Node) textFNList.item(0)).getNodeValue().trim();
                            if (CurElementName.equals("Episode")) xmlShowInfo.episode = ((Node) textFNList.item(0)).getNodeValue().trim();
                            if (CurElementName.equals("Format")) xmlShowInfo.format = ((Node) textFNList.item(0)).getNodeValue().trim();
                            if (CurElementName.equals("Language")) xmlShowInfo.language = ((Node) textFNList.item(0)).getNodeValue().trim();
                            if (CurElementName.equals("Next")) xmlShowInfo.next = ((Node) textFNList.item(0)).getNodeValue().trim();
                            if (CurElementName.equals("Season")) xmlShowInfo.season = ((Node) textFNList.item(0)).getNodeValue().trim();
                        }
                    }
                    xmlSeriesInfo.add(xmlShowInfo);
                }
            }
        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());
        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void UpdateSeriesFile() {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("JavaNZB.series"), "UTF8"));
            out.write("<TVSeries>");
            out.newLine();
            for (XMLShowInfo xmldata : xmlSeriesInfo) {
                out.write("  <Show>");
                out.newLine();
                out.write("    <Name>" + xmldata.showName + "</Name>");
                out.newLine();
                out.write("    <Season>" + xmldata.season + "</Season>");
                out.newLine();
                out.write("    <Episode>" + xmldata.episode + "</Episode>");
                out.newLine();
                out.write("    <Next>" + xmldata.next + "</Next>");
                out.newLine();
                out.write("    <Format>" + xmldata.format + "</Format>");
                out.newLine();
                out.write("    <Language>" + xmldata.language + "</Language>");
                out.newLine();
                out.write("    <SearchBy>" + xmldata.searchBy + "</SearchBy>");
                out.newLine();
                out.write("  </Show>");
                out.newLine();
            }
            out.write("</TVSeries>");
            out.close();
        } catch (Exception e) {
            System.out.println("IO Exception");
        }
    }
}

class XMLShowInfo {

    String showName = "", searchBy = "", episode = "", season = "", format = "", language = "", next = "";
}
