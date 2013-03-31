package de.mediapool.core.services.install;

import java.util.ArrayList;
import java.util.List;

public class InstallData {

	// Attributeorder = "Name,Mandatory,Order,Type,Size,Visible,Field"

	private static final String movieAttribute8[] = { "title", "RECOMMENDED", "1", "STRING", "350", "TRUE", "Titel" };
	private static final String movieAttribute5[] = { "genre", "RECOMMENDED", "2", "STRING", "75", "TRUE", "Genre" };

	private static final String movieAttribute1[] = { "duration", "RECOMMENDED", "3", "STRING", "75", "FALSE", "Dauer" };
	private static final String movieAttribute2[] = { "contenttype", "RECOMMENDED", "4", "STRING", "75", "FALSE", "Inhaltstyp" };
	private static final String movieAttribute3[] = { "image", "RECOMMENDED", "5", "STRING", "75", "FALSE", "Bild" };
	private static final String movieAttribute4[] = { "description", "RECOMMENDED", "6", "STRING", "75", "FALSE", "Beschreibung" };
	private static final String movieAttribute6[] = { "mediatype", "RECOMMENDED", "7", "STRING", "75", "FALSE", "Medientyp" };
	private static final String movieAttribute7[] = { "mlanguage", "RECOMMENDED", "8", "STRING", "75", "FALSE", "Sprache" };
	private static final String movieAttribute9[] = { "orginaltitle", "RECOMMENDED", "9", "STRING", "75", "FALSE", "Orginal Titel" };
	private static final String movieAttribute10[] = { "dtype", "RECOMMENDED", "10", "DATE", "75", "FALSE", "Typ" };
	private static final String movieAttribute11[] = { "approvedage", "RECOMMENDED", "11", "STRING", "75", "FALSE", "FSK" };
	private static final String movieAttribute12[] = { "launchyear", "RECOMMENDED", "12", "STRING", "75", "FALSE", "Veröffentlichungsdatum" };

	private static final String productAttribute1[] = { "ean", "RECOMMENDED", "3", "STRING", "75", "FALSE", "EAN" };
	private static final String productAttribute2[] = { "carrier", "RECOMMENDED", "1", "STRING", "125", "TRUE", "Träger" };
	private static final String productAttribute3[] = { "cover", "RECOMMENDED", "4", "STRING", "75", "FALSE", "Umschlag" };
	private static final String productAttribute4[] = { "mlanguage", "RECOMMENDED", "5", "STRING", "75", "FALSE", "Sprache" };
	private static final String productAttribute5[] = { "quality", "RECOMMENDED", "6", "STRING", "75", "FALSE", "Qualität" };
	private static final String productAttribute6[] = { "launchdate", "RECOMMENDED", "7", "DATE", "75", "FALSE", "Veröffentlichungsdatum" };
	private static final String productAttribute7[] = { "price", "RECOMMENDED", "2", "STRING", "75", "TRUE", "Preis" };
	private static final String productAttribute8[] = { "numberdiscs", "RECOMMENDED", "8", "STRING", "75", "FALSE", "Anzahl Medien" };
	private static final String productAttribute9[] = { "duration", "RECOMMENDED", "9", "STRING", "75", "FALSE", "Spieldauer" };
	private static final String productAttribute10[] = { "approvedage", "RECOMMENDED", "10", "STRING", "75", "FALSE", "FSK" };
	private static final String productAttribute11[] = { "special", "RECOMMENDED", "11", "STRING", "75", "FALSE", "Spezial" };

	public static List<String[]> getProductAttributes() {
		List<String[]> productAttributes = new ArrayList<String[]>();
		productAttributes.add(productAttribute1);
		productAttributes.add(productAttribute2);
		productAttributes.add(productAttribute3);
		productAttributes.add(productAttribute4);
		productAttributes.add(productAttribute5);
		productAttributes.add(productAttribute6);
		productAttributes.add(productAttribute7);
		productAttributes.add(productAttribute8);
		productAttributes.add(productAttribute9);
		productAttributes.add(productAttribute10);
		productAttributes.add(productAttribute11);
		return productAttributes;
	}

	public static List<String[]> getMovieAttributes() {
		List<String[]> movieAttributes = new ArrayList<String[]>();
		movieAttributes.add(movieAttribute1);
		movieAttributes.add(movieAttribute2);
		movieAttributes.add(movieAttribute3);
		movieAttributes.add(movieAttribute4);
		movieAttributes.add(movieAttribute5);
		movieAttributes.add(movieAttribute6);
		movieAttributes.add(movieAttribute7);
		movieAttributes.add(movieAttribute8);
		movieAttributes.add(movieAttribute9);
		movieAttributes.add(movieAttribute10);
		movieAttributes.add(movieAttribute11);
		movieAttributes.add(movieAttribute12);
		return movieAttributes;
	}

}
