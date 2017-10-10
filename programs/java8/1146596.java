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

    public static String formataTextoLista(String texto) {
	String resp = "";
	if (texto == null) {
	    return "";
	}
	try {
	    resp = texto.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
	} catch (Exception e) {
	    resp = "ERRO no Util.formataTextoLista: " + e.toString();
	}
	return resp;
    }

    public static String formataTexto(String texto) {
	if (texto == null) {
	    return "";
	}
	try {
	    if (texto.length() == 10 && texto.charAt(4) == '-' && texto.charAt(7) == '-') {
		texto = Util.formataData(texto);
		return texto;
	    }
	    texto = texto.replace('\'', '�');
	    return texto;
	} catch (Exception e) {
	    return "ERRO: " + e.toString();
	}
    }

    public static String criaPaginacao(String pagina, int numPag, int qtdeporpagina, int total) {
	String paginacao = "";
	int comeco, fim;
	int totalPaginas = total / qtdeporpagina;
	if (totalPaginas * qtdeporpagina < total) {
	    totalPaginas++;
	}
	if (numPag <= 5) {
	    comeco = 1;
	    fim = totalPaginas < 10 ? totalPaginas : 10;
	} else {
	    comeco = numPag - 5;
	    fim = totalPaginas < numPag + 5 ? totalPaginas : numPag + 5;
	}
	for (int i = comeco; i <= fim; i++) {
	    if (i == numPag) {
		paginacao += "<font color='#6F0000'><b>" + i + "</b></font> ";
	    } else {
		paginacao += "<a style='text-decoration:underline' title='P�g. " + i + "' href=\"Javascript:navegacao('"
			+ pagina + "'," + i + ")\">" + i + "</a> ";
	    }
	}
	if (total == 0) {
	    paginacao += " <b>(Nenhum registro encontrado)</b>";
	} else if (total == 1) {
	    paginacao += " <b>(" + total + " registro encontrado)</b>";
	} else {
	    paginacao += " <b>(" + total + " registros encontrados)</b>";
	}
	paginacao += "<input type='hidden' name='numPag' value='" + numPag + "'>";
	return paginacao;
    }

    public static boolean isNull(String dado) {
	if (dado == null) {
	    return true;
	}
	dado = dado.trim();
	if (dado.equals("")) {
	    return true;
	}
	if (dado.equalsIgnoreCase("null")) {
	    return true;
	}
	return false;
    }

    public static String RTF2HTML(String entrada) {
	StringReader stream;
	if (entrada == null) {
	    return "";
	}
	try {
	    entrada = replace(p);
	    System.out.println("hola");
	    stream = new StringReader(entrada);
	    RTFEditorKit kit = new RTFEditorKit();
	    javax.swing.text.Document doc = kit.createDefaultDocument();
	    kit.read(stream, doc, 0);
	    return doc.getText(0, doc.getLength());
	} catch (Exception e) {
	    return e.toString();
	}
    }

    public static void replace(int p) {
    }

    public static String converteDHWtoPNG(String caminhoupload, String caminhoperl, String caminhoj2sdk,
	    String nomearquivo) {
	try {
	    String programa1[] = { caminhoperl + "perl.exe", caminhoupload + "dhw2ps.pl", "--ps",
		    caminhoupload + nomearquivo };
	    String programa2[] = { caminhoj2sdk + "java", "-cp", caminhoupload + "digimemo.jar", "DigiPSToPng",
		    caminhoupload, nomearquivo };
	    Process p1 = Runtime.getRuntime().exec(programa1);
	    Process p2 = Runtime.getRuntime().exec(programa2);
	    return "OK";
	} catch (Exception e) {
	    return e.toString();
	}
    }

    public static String trim(String str) {
	try {
	    if (str == null) {
		return "";
	    } else {
		str = str.replace("'", "''");
		return str.trim();
	    }
	} catch (Exception e) {
	    return "";
	}
    }

}
