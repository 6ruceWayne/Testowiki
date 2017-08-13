package ua.java.services;

import java.util.List;

import ua.java.models.Section;

public interface SectionInterfaceService {
	void addSection(String name);

	void addSection(String name, String description);

	List<Section> getAllSections();

	List<String> getListSectionsLikeString();

	Section findByName(String name);

}
