package path.trainingapp.UniversitySystem.mapper.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import path.trainingapp.UniversitySystem.dto.CourseRegistrationDTO;
import path.trainingapp.UniversitySystem.mapper.CourseRegistrationMapper;
import path.trainingapp.UniversitySystem.models.CourseRegistration;
import path.trainingapp.UniversitySystem.models.compositeKey.CourseRegistrationKey;
import path.trainingapp.UniversitySystem.services.CourseService;
import path.trainingapp.UniversitySystem.services.StudentService;


@Component
public class CourseRegistrationMapperImpl implements CourseRegistrationMapper {

    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;


    @Override
    public CourseRegistration dtoToCourseRegistration(CourseRegistrationDTO courseRegistrationDTO) {
        if(courseRegistrationDTO == null){
            return null;
        }
        CourseRegistration courseRegistration = new CourseRegistration();
        CourseRegistrationKey courseRegistrationKey = new CourseRegistrationKey(
                courseRegistrationDTO.getCourseId(),
                courseRegistrationDTO.getStudentId()
        );
        courseRegistration.setId(courseRegistrationKey);
        courseRegistration.setGrade(courseRegistrationDTO.getGrade());
        courseRegistration.setSemester(courseRegistrationDTO.getSemester());

        return courseRegistration;

    }

    @Override
    public CourseRegistrationDTO courseRegistrationToDTO(CourseRegistration courseRegistration) {
        CourseRegistrationDTO courseRegistrationDTO = new CourseRegistrationDTO();

        courseRegistrationDTO.setCourseId(courseRegistration.getId().getCourseId());
        courseRegistrationDTO.setStudentId(courseRegistration.getId().getStudentId());
        courseRegistrationDTO.setGrade(courseRegistration.getGrade());
        courseRegistrationDTO.setSemester(courseRegistration.getSemester());

        return courseRegistrationDTO;
    }
}
