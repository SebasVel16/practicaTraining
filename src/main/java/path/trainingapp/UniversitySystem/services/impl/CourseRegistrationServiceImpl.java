package path.trainingapp.UniversitySystem.services.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import path.trainingapp.UniversitySystem.dto.CourseRegistrationDTO;
import path.trainingapp.UniversitySystem.mapper.CourseRegistrationMapper;
import path.trainingapp.UniversitySystem.models.CourseRegistration;
import path.trainingapp.UniversitySystem.repositories.CourseRegistrationRepository;
import path.trainingapp.UniversitySystem.services.CourseRegistrationService;
import path.trainingapp.UniversitySystem.services.CourseService;
import path.trainingapp.UniversitySystem.services.StudentService;

import java.util.List;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

    CourseRegistrationRepository courseRegistrationRepository;
    CourseRegistrationMapper courseRegistrationMapper;
    CourseService courseService;
    StudentService studentService;

    public CourseRegistrationServiceImpl(CourseRegistrationRepository courseRegistrationRepository,
                                         CourseService courseService,
                                         StudentService studentService,
                                         CourseRegistrationMapper courseRegistrationMapper) {
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.courseService = courseService;
        this.studentService = studentService;
        this.courseRegistrationMapper = courseRegistrationMapper;
    }

    @Override
    public CourseRegistrationDTO registerStudents(CourseRegistrationDTO courseRegistrationDTO) {
        CourseRegistration courseRegistration = courseRegistrationMapper.dtoToCourseRegistration(courseRegistrationDTO);
        return courseRegistrationMapper.courseRegistrationToDTO(courseRegistrationRepository.save(courseRegistration));
    }

    @Override
    public List<CourseRegistration> getCourseBestGrades(int semester) {
        return courseRegistrationRepository.findTop5BySemesterOrderByGradeDesc(semester);

    }
}
