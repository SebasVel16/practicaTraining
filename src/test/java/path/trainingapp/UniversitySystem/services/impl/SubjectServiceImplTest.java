package path.trainingapp.UniversitySystem.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import path.trainingapp.UniversitySystem.dto.SubjectDTO;
import path.trainingapp.UniversitySystem.mapper.SubjectMapper;
import path.trainingapp.UniversitySystem.models.Subject;
import path.trainingapp.UniversitySystem.repositories.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private SubjectMapper subjectMapper;

    @InjectMocks
    private SubjectServiceImpl subjectService;

    private Subject subject;
    private SubjectDTO subjectDTO;


    @BeforeEach
    void setUp() {
        subject = new Subject(1L,"matematicas 1", 4);
        subjectDTO  = new SubjectDTO(1L,"matematicas 1", 4);
    }

    @Test
    void getSubject() {
        Optional<Subject> expected = Optional.of(subject);
        Mockito.when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        final Optional<Subject> result = subjectService.getSubject(1L);
        Assertions.assertEquals(expected,result);
        Mockito.verify(subjectRepository).findById(1L);
    }

    @Test
    void saveSubject() {
        SubjectDTO expected = new SubjectDTO(1L,"matematicas 1",4);
        Mockito.when(subjectRepository.save(subject)).thenReturn(subject);
        Mockito.when(subjectMapper.subjectDTOToSubject(subjectDTO)).thenReturn(subject);
        Mockito.when(subjectMapper.subjectToSubjectDTO(subject)).thenReturn(subjectDTO);
        final SubjectDTO result = subjectService.saveSubject(subjectDTO);
        Assertions.assertEquals(expected,result);
        Mockito.verify(subjectRepository).save(subject);
    }
}