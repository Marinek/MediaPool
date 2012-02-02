package de.mediapool.core.service.grab;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mediapool.core.domain.Movie;
import de.mediapool.core.domain.Participation;

public class DataGrabber {
	private final Logger logger = LoggerFactory.getLogger(DataGrabber.class);

	private String DEUTSCHERTITEL = "Deutscher Titel";
	private String ORIGINALTITEL = "Originaltitel";
	private String ALTERSFREIGABE = "Altersfreigabe";
	private String ERSCHEINUNGSJAHR = "Erscheinungsjahr";
	private String LAENGE = "Länge";
	private String ORIGINALSPRACHE = "Originalsprache";
	private String PRODUKTIONSLAND = "Produktionsland";

	private String SEARCHMOVIE = "";
	private boolean EXACTSEARCH = true;
	private boolean XMLOUTPUT = false;

	private String wikiurl1 = "http://de.wikipedia.org/w/index.php?title=Spezial%3ASuche&profile=advanced&search=";
	private String wikiurl2 = "&fulltext=Search&ns0=1&profile=advanced";
	private String buchurl1 = "http://www.buch.de/shop/home/suche/?sq=";
	private String buchurl2 = "&sswg=";
	private String buchurl3 = "&suchen=Daten+absenden";

	// private String PRODUKTION = "Produktion";
	// private String REGIE = "Regie";
	// private String KAMERA = "Kamera";
	// private String SCHNITT = "Schnitt";
	// private String MUSIK = "Musik";
	// private String DREHBUCH = "Drehbuch";

	public DataGrabber() {
		System.setProperty("java.net.useSystemProxies", "true");
	}

	public void initSearch() {
		for (String film : FilmList.allfilmes) {
			searchMovie(film, EXACTSEARCH);
		}

	}

	public List<Movie> searchMovie(String searchfilm, boolean exact) {
		List<TreeMap<String, String>> filmdaten = new ArrayList<TreeMap<String, String>>();
		List<String> pageurls = getLinksFromWiki(searchfilm);

		for (String url : pageurls) {
			filmdaten.add(getFilmDataFromPage(url));
		}

		return findeFilm(filmdaten, searchfilm, exact, pageurls);

	}

	private List<Movie> findeFilm(List<TreeMap<String, String>> filmdaten, String searchfilm, boolean exakt,
			List<String> pageurls) {
		List<TreeMap<String, String>> rightfilms = new ArrayList<TreeMap<String, String>>();
		List<Movie> movieList = new ArrayList<Movie>();
		StringBuffer toprint = new StringBuffer();
		StringBuffer notExact = new StringBuffer();
		// Überprüfe ob der Deutsche Titel exakt oder nicht exakt dem Suchtitel
		// entspricht
		for (TreeMap<String, String> film : filmdaten) {
			String dtitel = film.get(DEUTSCHERTITEL);
			if (dtitel != null) {
				// Entferne Untertitel aus dem eigentlichen Titel
				film.put(DEUTSCHERTITEL, dtitel.split("\\s+\\w+:")[0]);
				if (!exakt || film.get(DEUTSCHERTITEL).equalsIgnoreCase(searchfilm)) {
					rightfilms.add(film);
				} else {
					notExact.append(film.get(DEUTSCHERTITEL)).append(", ");
				}
			}
		}
		logger.info("------------------------------------------");
		logger.info("Gefundene Filme zum Titel: " + searchfilm + " " + rightfilms.size());

		// Markiere alle Filme mit identischem Titel mit einem #
		for (int i = 0; i < rightfilms.size(); i++) {
			TreeMap<String, String> film = rightfilms.get(i);
			if (exakt && rightfilms.size() > 1) {
				String dtitel = film.get(DEUTSCHERTITEL);
				int nummer = i + 1;
				film.put(DEUTSCHERTITEL, "#" + nummer + " " + dtitel);
			}
			Movie movie = putMovieWikiDataToObject(film);
			movieList.add(movie);
		}
		if (XMLOUTPUT) {
			writeToFile("Filme", moviesToXML(movieList), "xml");
		}

		String logfileString = "NOT EXAKT: " + searchfilm + " : " + notExact.toString() + " 0 ";
		if (rightfilms.size() > 0) {
			logfileString = searchfilm + " : " + rightfilms.size();
		}

		logger.info("Gefundene Filme ingesamt: " + searchfilm + " " + pageurls.size());
		String prefix = pageurls.size() > 0 ? "" : "NOT FOUND ";
		toprint.append(prefix);
		toprint.append(logfileString);
		toprint.append(" / " + pageurls.size());
		toprint.append(System.getProperty("line.separator"));
		writeToFile("Filmeinfo", toprint.toString(), "txt");

		return movieList;
	}

	private Movie putMovieWikiDataToObject(TreeMap<String, String> film) {
		Movie movie = new Movie();
		Set<Participation> parts = new HashSet<Participation>();

		for (String name : film.keySet()) {

			if (name.equals(DEUTSCHERTITEL)) {
				movie.setTitle(film.get(name));
			} else if (name.equals(ORIGINALTITEL)) {
				movie.setOriginaltitle(film.get(name));
			} else if (name.equals(ALTERSFREIGABE)) {
				movie.setApprovedage(getAge(film.get(name)));
			} else if (name.equals(ORIGINALSPRACHE)) {
				movie.setMlanguage(film.get(name));
			} else if (name.equals(PRODUKTIONSLAND)) {
				movie.setProductionland(film.get(name));
			} else if (name.equals(ERSCHEINUNGSJAHR)) {
				movie.setLaunchyear(toInt(film.get(name)));
			} else if (name.equals(LAENGE)) {
				movie.setMlenght(toInt(film.get(name)));
			} else {
				String[] values = film.get(name).split(", ");
				for (int k = 0; k < values.length; k++) {
					parts.add(checkPart(name, values[k]));
				}
			}

		}
		movie.setParticipation(parts);

		logger.debug("putMovieWikiDataToObject " + movie.toString());
		return movie;
	}

	private void printMap(TreeMap<String, String> map) {
		for (String name : map.keySet()) {
			logger.info(name + ": " + map.get(name));
		}
	}

	private Participation checkPart(String part, String name) {
		if (part.startsWith("Rolle")) {
			part = "Actor";
		}
		return new Participation(part, name);
	}

	private int toInt(String svalue) {
		String[] cuttime = svalue.split(" ");
		String newvalue = cuttime[0].trim();
		int ivalue = 0;
		try {
			ivalue = Integer.parseInt(newvalue);
		} catch (NumberFormatException e) {
			logger.error(newvalue + " (toInt) " + e.getMessage());
		}
		return ivalue;
	}

	private int getAge(String value) {
		if (value.contains("0")) {
			return 0;
		}
		if (value.contains("16")) {
			return 16;
		}
		if (value.contains("6")) {
			return 6;
		}
		if (value.contains("12")) {
			return 12;
		}
		if (value.contains("18")) {
			return 18;
		}
		return 99;

	}

	private String getValueFromElement(Elements element) {
		return element.first().getElementsByClass("value").first().text();
	}

	private TreeMap<String, String> getBookDataFromPage(String url) {
		TreeMap<String, String> hm = new TreeMap<String, String>();
		try {
			Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);

			Elements titleClass = doc.getElementsByClass("pm_titel");
			Elements utitleClass = doc.getElementsByClass("pm_untertitel");
			Elements authorClass = doc.getElementsByClass("b9_author");

			Elements imageCon = doc.getElementsByClass("pm_detailImage");
			String imageUrl = imageCon.first().attr("src");

			Elements priceClass = doc.select("span[class=pm_preis b9Value]");
			Elements descriptioncon = doc.select("div[id=pm_beschreibung]");
			Elements descriptionClass = descriptioncon.first().select("div[class=redaktion raw]");

			Elements isbnCon = doc.getElementsByClass("isbn");
			Elements eanCon = doc.getElementsByClass("ean");
			Elements verlagCon = doc.getElementsByClass("verlag");
			Elements einbandCon = doc.getElementsByClass("einband");
			Elements seitenCon = doc.getElementsByClass("seiten");
			Elements gewichtCon = doc.getElementsByClass("gewicht");
			Elements reiheCon = doc.getElementsByClass("reihe");
			Elements spracheCon = doc.getElementsByClass("produktsprache");
			Elements datumCon = doc.getElementsByClass("veroeffentlichungsdatum");

			hm.put("title", titleClass.first().text());
			hm.put("utitle", utitleClass.first().text());
			hm.put("author", authorClass.first().text());
			hm.put("price", priceClass.first().text());
			hm.put("isbn", getValueFromElement(isbnCon));
			hm.put("ean", getValueFromElement(eanCon));
			hm.put("verlag", getValueFromElement(verlagCon));
			hm.put("einband", getValueFromElement(einbandCon));
			hm.put("seiten", getValueFromElement(seitenCon));
			hm.put("gewicht", getValueFromElement(gewichtCon));
			hm.put("reihe", getValueFromElement(reiheCon));
			hm.put("datum", getValueFromElement(datumCon));
			hm.put("sprache", getValueFromElement(spracheCon));
			hm.put("description", descriptionClass.first().text());
			hm.put("imageurl", imageUrl);

			printMap(hm);

		} catch (Exception ex) {
			logger.error("getBookDataFromPage " + ex.getMessage());

		}
		return hm;

	}

	private TreeMap<String, String> getFilmDataFromPage(String url) {
		TreeMap<String, String> hm = new TreeMap<String, String>();
		try {

			// Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8",
			// url);

			Document doc = Jsoup.connect(url).userAgent("Mozilla").timeout(10 * 1000).get();
			Elements tables = doc.getElementsByTag("table");

			String value = "";
			String key = "";
			boolean isvalue = true;

			for (int j = 0; j < tables.size(); j++) {
				Elements tableHeaders = tables.get(j).getElementsByTag("th");
				if (tableHeaders.size() > 0) {
					logger.debug("tableheaders: " + tableHeaders.size());

					Elements fd = tableHeaders.first().getElementsContainingText("Filmdaten");

					logger.debug("Filmdaten: " + fd.size());
					if (fd.size() > 0) {
						Elements tds = tables.get(j).getElementsByTag("td");
						logger.debug("tdtags: " + tds.size());
						for (int i = 0; i < tds.size(); i++) {
							if (!tds.get(i).text().equals("")) {
								isvalue = !isvalue;
								if (!isvalue) {
									key = tds.get(i).text();
								} else {
									value = tds.get(i).text();
									hm.put(key, value);
								}
							}

						}
						Elements lis = tables.first().getElementsByTag("li");
						logger.debug("listtags: " + lis.size());
						for (int i = 0; i < lis.size(); i++) {

							String parsed = Jsoup.parse(lis.get(i).text()).text();
							String[] keyvalue = parsed.split(": ");
							if (keyvalue.length == 2) {
								keyvalue[1].split(", ");

								hm.put("Rolle(" + keyvalue[1] + ")", keyvalue[0]);
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error("getFilmDataFromPage " + ex.getMessage());

		}
		return hm;

	}

	private List<String> getLinksFromWiki(String searchfilm) {

		String url = getSearchUrl("wiki", searchfilm, "film");

		List<String> linkstoget = new ArrayList<String>();
		Document doc;

		try {
			doc = Jsoup.connect(url).userAgent("Mozilla").timeout(10 * 1000).get();
			Elements links = doc.select("a[href]");

			for (Element link : links) {
				if (link.attr("abs:href").startsWith("http://de.wikipedia.org/wiki/")) {
					doc = Jsoup.connect(link.attr("abs:href")).userAgent("Mozilla").get();
					Elements fd = doc.getElementsContainingText("Filmdaten");

					if (fd.size() > 0) {
						linkstoget.add(link.attr("abs:href"));
						print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
					}
				}
			}
		} catch (IOException e) {
			logger.error("getLinksFromWiki " + e.getMessage());
		}

		return linkstoget;
	}

	private List<String> getLinksFromBuch(String search) {

		String url = getSearchUrl("buch.de", search, "Buch");

		List<String> linkstoget = new ArrayList<String>();
		Document doc = null;

		try {
			doc = Jsoup.connect(url).get();

		} catch (IOException e) {
			logger.error("getLinksFromBuch " + e.getMessage());
		}
		if (doc != null) {
			Elements links = doc.getElementsByClass("pm_titelDetailsAction");

			for (Element link : links) {
				if (link.text().equalsIgnoreCase(search) || !EXACTSEARCH) {
					linkstoget.add(link.attr("abs:href"));
					print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
				}
			}
		}
		return linkstoget;
	}

	private void print(String msg, Object... args) {
		logger.info(String.format(msg, args));
	}

	private String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width - 1) + ".";
		else
			return s;
	}

	private void writeToFile(String filename, String toprint, String type) {

		String path = "c:\\" + filename + "." + type;

		try {

			FileOutputStream stream = new FileOutputStream(path);
			OutputStreamWriter out = new OutputStreamWriter(stream, "UTF-8");

			out.write(toprint);

			// Schreibt den Stream in die Datei
			out.flush();

			// Schließt den Stream
			out.close();

		} catch (IOException e) {
			logger.error("writetofile " + e.getMessage());
		}
	}

	private TreeMap<String, String> getMovieProductDataFromPage(String url) {
		TreeMap<String, String> hm = new TreeMap<String, String>();
		// url =
		// "http://www.buch.de/shop/bde_dvd_hg_startseite/empfehlungsartikel/american_gangster_scarface_2_movie_set/steven_zaillian/EAN5050582581003/ID15690984.html";
		url = "http://www.buch.de/shop/bde_dvd_hg_startseite/suchartikel/al_pacino_box/richard_price/EAN5050582703320/ID17631676.html?jumpId=6796933";
		try {
			Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);

			Elements titleClass = doc.getElementsByClass("pm_titel");
			Elements utitleClass = doc.getElementsByClass("pm_untertitel");
			Elements authorClass = doc.getElementsByClass("b9_author");

			Elements imageCon = doc.getElementsByClass("pm_detailImage");
			String imageUrl = imageCon.first().attr("src");

			Elements priceClass = doc.select("span[class=pm_preis b9Value]");
			Elements descriptioncon = doc.select("div[id=pm_kurzbeschreibung]");
			Elements descriptionClass = descriptioncon.first().select("div[class=redaktion raw]");

			Elements eanCon = doc.getElementsByClass("ean");
			Elements mediumCon = doc.getElementsByClass("mediumKurzbezeichnung");
			Elements anzahlCon = doc.getElementsByClass("anzahl");
			Elements fskCon = doc.getElementsByClass("fsk");
			Elements datumCon = doc.getElementsByClass("veroeffentlichungsdatum");
			Elements studioCon = doc.getElementsByClass("verlag");
			Elements genreCon = doc.getElementsByClass("stilrichtung");
			Elements dauerCon = doc.getElementsByClass("spieldauer");
			Elements spracheCon = doc.getElementsByClass("produktsprache");

			hm.put("title", titleClass.first().text());
			hm.put("utitle", utitleClass.first().text());
			hm.put("author", authorClass.first().text());
			hm.put("price", priceClass.first().text());

			hm.put("ean", getValueFromElement(eanCon));
			hm.put("medium", getValueFromElement(mediumCon));
			hm.put("anzahl", getValueFromElement(anzahlCon));
			hm.put("fsk", getValueFromElement(fskCon));
			hm.put("datum", getValueFromElement(datumCon));
			hm.put("studio", getValueFromElement(studioCon));
			hm.put("genre", getValueFromElement(genreCon));
			hm.put("dauer", getValueFromElement(dauerCon));
			hm.put("sprache", getValueFromElement(spracheCon));

			hm.put("description", descriptionClass.first().text());
			hm.put("imageurl", imageUrl);

			saveImage(imageUrl, titleClass.first().text());
			printMap(hm);

		} catch (Exception ex) {
			ex.printStackTrace();
			// logger.error("getMovieDataFromPage " + ex.getMessage());

		}
		return hm;

	}

	private String getSearchUrl(String portal, String suche, String medium) {

		String suche_en = suche.replaceAll("\\s", "+");
		String searchUrl = "";
		if (portal.equals("buch.de")) {
			searchUrl = buchurl1 + suche_en + buchurl2 + medium + buchurl3;
		}
		if (portal.equals("wiki")) {
			searchUrl = wikiurl1 + suche_en + "+" + medium + wikiurl2;
		}

		return searchUrl;
	}

	private void saveImage(String imageUrl, String file) throws IOException {
		String destinationFile = "c:\\" + file + ".jpg";
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}

	private void searchBook(String searchBook, boolean exact) {
		List<TreeMap<String, String>> buchdaten = new ArrayList<TreeMap<String, String>>();
		List<String> pageurls = getLinksFromBuch(searchBook);

		for (String url : pageurls) {
			buchdaten.add(getBookDataFromPage(url));
		}

		for (TreeMap<String, String> buch : buchdaten) {
			printMap(buch);
		}
		logger.info("Gefundene Bücher ingesamt: " + searchBook + " " + pageurls.size());
	}

	private String moviesToXML(List<Movie> movieList) {
		StringBuffer str = new StringBuffer();
		String next = System.getProperty("line.separator");
		str.append("<movies>").append(next);
		for (Movie movie : movieList) {
			str.append("\t<movie");
			str.append(" title=");
			str.append("\"" + movie.getTitle() + "\"");
			str.append(" otitle=");
			str.append("\"" + movie.getOriginaltitle() + "\"");
			str.append(" year=");
			str.append("\"" + movie.getLaunchyear() + "\"");
			str.append(" length=");
			str.append("\"" + movie.getMlenght() + "\"");
			str.append(" age=");
			str.append("\"" + movie.getApprovedage() + "\"");
			str.append(" land=");
			str.append("\"" + movie.getProductionland() + "\"");
			str.append(">").append(next);
			str.append("\t\t<cooperations>").append(next);
			for (Participation part : movie.getParticipation()) {
				str.append("\t\t\t<coop part=\"").append(part.getMpart() + "\"");
				str.append(" name=\"").append(part.getName() + "\"");
				str.append("/>").append(next);
			}
			str.append("\t\t</cooperations>").append(next);
			str.append("\t</movie>").append(next);
		}
		str.append("</movies>");
		return str.toString();
	}

}
