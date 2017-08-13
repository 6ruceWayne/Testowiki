package ua.java.models;

public enum LocaleLanguage {
	Russian, Ukrainian, English;

	public LocaleLanguage getLocale(String locale) {
		switch (locale) {
		case "ru":
			return LocaleLanguage.Russian;
		case "en":
			return LocaleLanguage.English;
		case "ua":
			return LocaleLanguage.Ukrainian;
		default:
			return LocaleLanguage.Russian;
		}
	}
}
