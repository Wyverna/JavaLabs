package com.example.javalab.util;
import com.example.javalab.dto.ProjectForm;
import com.example.javalab.entity.Project;
import com.example.javalab.exceptions.ResourceNotFoundException;
import com.example.javalab.repository.WorkerRepository;
import com.example.javalab.service.WorkerService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
public class Mapper
{
    private static ModelMapper modelMapper;
    public static WorkerService workerService;
    static  {
    modelMapper = new ModelMapper();
    modelMapper
            .getConfiguration()
            .setFieldMatchingEnabled(true)
            .setSkipNullEnabled(false)
            .setMatchingStrategy(MatchingStrategies.STANDARD);
        modelMapper.createTypeMap(Project.class, ProjectForm.class)
                .addMappings(m->m.skip(ProjectForm::setWorker_id)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(ProjectForm.class, Project.class)
                .addMappings(m->m.skip(Project::setWorker)).setPostConverter(toEntityConverter());
    }
    public static <S, T> T map(S source, Class<T> targetClass)
    {
        return modelMapper.map(source, targetClass);
    }
    public static <S, T> List<T> mapAll(Collection<? extends S> sourceList, Class<T> targetClass)
    {
        return sourceList.stream()
                .map(e -> map(e, targetClass))
                .collect(Collectors.toList());
    }
    static Converter<Project,ProjectForm> toDtoConverter()
    {
        return mappingContext ->
        {
            Project project=mappingContext.getSource();
            ProjectForm projectForm=mappingContext.getDestination();
            mapSpecificFields(project,projectForm);
            return mappingContext.getDestination();
        };
    }

    static Converter<ProjectForm,Project> toEntityConverter()
    {
        return mappingContext ->
        {
            Project project=mappingContext.getDestination();
            ProjectForm projectForm=mappingContext.getSource();
            try {
            mapSpecificFields(projectForm,project);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            return mappingContext.getDestination();
        };
    }
    static void mapSpecificFields(Project source,ProjectForm destination)
    {
        destination.setWorker_id(source.getWorker().getId());
    }

    static void mapSpecificFields(ProjectForm source,Project destination) throws ResourceNotFoundException
    {
        destination.setWorker(workerService.getById(source.getWorker_id()));
    }
}