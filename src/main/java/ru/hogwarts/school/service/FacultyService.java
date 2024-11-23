package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private static final Logger logger = LoggerFactory.getLogger(AvatarService.class);


    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Метод создания факультета");
        logger.debug("Создание факультета с именем: " + faculty.getName());
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty getFaculty(long id) {
        logger.info("Метод создания факультета");
        return facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
    }

    public Faculty updateFaculty(Faculty faculty, Long id) {
        logger.info("Метод создания факультета");
        if (!facultyRepository.existsById(id)) {
            throw new FacultyNotFoundException(id);
        }
        faculty.setId(id);
        return facultyRepository.save(faculty);
    }

    public Faculty deleteFaculty(Long id) {
        logger.info("Метод создания факультета");

        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
        facultyRepository.delete(faculty);
        return faculty;
    }

    public List<Faculty> getFacultiesByColor(String color) {
        logger.info("Метод создания факультета");
        return facultyRepository.findAll().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public List<Faculty> findByColorIgnoreCaseOrNameIgnoreCase(String color, String name) {
        logger.info("Метод создания факультета");
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }
}