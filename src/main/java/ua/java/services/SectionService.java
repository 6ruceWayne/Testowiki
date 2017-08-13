package ua.java.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.java.models.Section;
import ua.java.repository.SectionRepository;

@Service
public class SectionService implements SectionInterfaceService {

	@Autowired
	private SectionRepository sectionRep;

	@Override
	public void addSection(String name) {
		// TODO Auto-generated method stub
		Section section = new Section();
		section.setName(name);
		sectionRep.save(section);
	}

	@Override
	public void addSection(String name, String description) {
		// TODO Auto-generated method stub
		Section section = new Section();
		section.setName(name);
		section.setDescription(description);
		sectionRep.save(section);
	}

	@Override
	public List<Section> getAllSections() {
		// TODO Auto-generated method stub
		return sectionRep.findAll();
	}

	@Override
	public List<String> getListSectionsLikeString() {
		// TODO Auto-generated method stub
		List<Section> list = sectionRep.findAll();
		List<String> sections = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			sections.add(list.get(i).getName());
		}
		return sections;
	}

	@Override
	public Section findByName(String name) {
		// TODO Auto-generated method stub
		return sectionRep.findByName(name);
	}

}
