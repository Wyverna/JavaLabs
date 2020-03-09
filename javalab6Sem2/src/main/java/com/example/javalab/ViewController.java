package com.example.javalab;
import com.example.javalab.AOP.AuthorizeAnnotation;
import com.example.javalab.AOP.LogAnnotation;
import com.example.javalab.dto.ProjectForm;
import com.example.javalab.dto.WorkerForm;
import com.example.javalab.entity.Project;
import com.example.javalab.entity.User;
import com.example.javalab.entity.Worker;
import com.example.javalab.exceptions.ResourceNotFoundException;
import com.example.javalab.service.ProjectService;
import com.example.javalab.service.UserService;
import com.example.javalab.service.WorkerService;
import com.example.javalab.util.Mapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;

@RestController
@Getter
@Setter
public class ViewController {
    UserService userService;
    WorkerService workerService;
    ProjectService projectService;
    @Autowired
    public ViewController(UserService userService,WorkerService workerService,ProjectService projectService)
    {
        Mapper.workerService=workerService;
        this.projectService=projectService;
        this.userService=userService;
        this.workerService=workerService;
     }
    @Operation(summary = "Find Projects", description = "Returns a list projects", tags = { "project" })
    @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Project.class)))
    @GetMapping("/ListProjects")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<ProjectForm>> ListProjects() throws ResourceNotFoundException {
       CollectionModel<EntityModel<ProjectForm>> resource=CollectionModel.wrap(Mapper.mapAll(projectService.findProjects(),ProjectForm.class));
        for (final EntityModel<ProjectForm> res : resource)
        { Link selfLink = linkTo(methodOn(ViewController.class)
                .FindByIdProject(res.getContent().getId())).withSelfRel();
        res.add(selfLink);
        res.add(linkTo(methodOn(ViewController.class).FindByIdWorker(res.getContent().getWorker_id())).withRel("Worker"));
        }
        resource.add(linkTo(methodOn(ViewController.class)
                .ListProjects()).withRel("all"));
        return resource;
       }

    @Operation(summary = "Find Workers", description = "Returns a list workers", tags = { "worker" })
    @ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = Worker.class)))
    @GetMapping("/ListWorkers")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<WorkerForm>> ListWorkers() throws ResourceNotFoundException
    {

        CollectionModel<EntityModel<WorkerForm>> resource=CollectionModel.wrap( Mapper.mapAll(workerService.findWorkers(),WorkerForm.class));
        for (final EntityModel<WorkerForm> res : resource)
        { Link selfLink = linkTo(methodOn(ViewController.class)
                .FindByIdWorker(res.getContent().getId())).withSelfRel();
            res.add(selfLink);
            res.add(linkTo(methodOn(ViewController.class).FindProjectsByWorkerId(res.getContent().getId())).withRel("Projects"));
        }
        resource.add(linkTo(methodOn(ViewController.class)
                .ListWorkers()).withRel("all"));
        return resource;
    }
    @LogAnnotation
    @Operation(summary = "Create Project", description = "Create a single project", tags = { "project" })
    @ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = Project.class)))
    @PostMapping("/AddProject")
    @ResponseStatus(value=HttpStatus.CREATED,reason = "Успешное добавление")
    public void SaveProject(@Valid @RequestBody ProjectForm projectForm) {
            projectService.SaveProject(Mapper.map(projectForm,Project.class));
    }
    @LogAnnotation
    @Operation(summary = "Change worker by ID", description = "Change a single worker", tags = { "worker" })
    @ApiResponses(value =
            { @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Worker.class))),
                    @ApiResponse(responseCode = "404", description = "Worker not found") })
    @PutMapping("/ChangeWorker/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Успешное изменение")
    public void ChangeWorker(@PathVariable("id") Long id,@Valid @RequestBody WorkerForm workerForm) throws ResourceNotFoundException {
        workerService.getById(id);
        workerService.UpdateWorker(Mapper.map(workerForm,Worker.class),id);
    }
    @LogAnnotation
    @Operation(summary = "Change project by ID", description = "Change a single project", tags = { "project" })
    @ApiResponses(value =
            { @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Project.class))),
                    @ApiResponse(responseCode = "404", description = "Project not found") })
    @PutMapping("/ChangeProject/{id}")
    @ResponseStatus(value=HttpStatus.OK, reason = "Успешное изменение")
    public void ChangeProject(@PathVariable("id") Long id,@Valid @RequestBody ProjectForm projectForm) throws ResourceNotFoundException {
            projectService.getById(id);
            projectService.UpdateProject(Mapper.map(projectForm,Project.class),id);
        }
    @LogAnnotation
    @Operation(summary = "Create Worker", description = "Create a single worker", tags = { "worker" })
    @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Worker.class)))
    @PostMapping("/AddWorker")
    @ResponseStatus(value = HttpStatus.CREATED,reason = "Успешное добавление")
    public void SaveWorker(@Valid @RequestBody WorkerForm workerForm) {
            workerService.SaveWorker(Mapper.map(workerForm,Worker.class));
    }
    @LogAnnotation
    @Operation(summary = "Delete worker by ID", description = "Delete a single worker", tags = { "worker" })
    @ApiResponses(value =
            { @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Worker.class))),
                    @ApiResponse(responseCode = "404", description = "Worker not found") })
    @ResponseStatus(value = HttpStatus.OK, reason = "Успешное удаление")
    @DeleteMapping("/DeleteWorker/{id}")
    public void DeleteWorker(@PathVariable("id") long id) throws ResourceNotFoundException {
        workerService.DeleteWorker(workerService.getById(id));
    }
    @LogAnnotation
    @Operation(summary = "Delete project by ID", description = "Delete a single project", tags = { "project" })
    @ApiResponses(value =
            { @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Project.class))),
                    @ApiResponse(responseCode = "404", description = "Project not found") })
    @DeleteMapping("/DeleteProject/{id}")
    @ResponseStatus(value=HttpStatus.OK,reason = "Успешное удаление")
    public void DeleteProject(@PathVariable("id") long id) throws ResourceNotFoundException {
        projectService.DeleteProject(projectService.getById(id));
        }

    @Operation(summary = "Find worker by ID", description = "Returns a single worker", tags = { "worker" })
    @ApiResponses(value =
            { @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Worker.class))),
                    @ApiResponse(responseCode = "404", description = "Worker not found") })
    @GetMapping("/ListWorkers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<WorkerForm> FindByIdWorker(@PathVariable ("id") Long id) throws ResourceNotFoundException
    {
        WorkerForm workerForm= Mapper.map(workerService.getById(id),
                        WorkerForm.class);
        Link link = linkTo(methodOn(ViewController.class).FindByIdWorker(id)).withSelfRel();
        EntityModel<WorkerForm> workerFormEntityModel=new EntityModel<WorkerForm>(workerForm,link);
        workerFormEntityModel.add(linkTo(methodOn(ViewController.class).FindProjectsByWorkerId(id)).withRel("Projects"));
        return workerFormEntityModel;
    }
    @Operation(summary = "Find projects by Worker ID", description = "Returns a list projects", tags = { "project","worker" })
    @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Project.class)))
    @GetMapping("/ListProjectsByWorkerId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<ProjectForm>> FindProjectsByWorkerId(@PathVariable ("id") Long id) throws ResourceNotFoundException
    {
        CollectionModel<EntityModel<ProjectForm>> resource=CollectionModel.wrap(Mapper
                .mapAll(projectService.findProjectsByWorker(workerService.getById(id)),ProjectForm.class));
        for (final EntityModel<ProjectForm> res : resource)
        { Link selfLink = linkTo(methodOn(ViewController.class)
                .FindByIdProject(res.getContent().getId())).withSelfRel();
            res.add(selfLink);
            res.add(linkTo(methodOn(ViewController.class).FindByIdWorker(res.getContent().getWorker_id())).withRel("Worker"));
        }
        resource.add(linkTo(methodOn(ViewController.class)
                .ListProjects()).withRel("all"));
        return resource;
    }
    @Operation(summary = "Find project by ID", description = "Returns a single project", tags = { "project" })
    @ApiResponses(value =
            { @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Project.class))),
                    @ApiResponse(responseCode = "404", description = "Project not found") })
    @GetMapping("/ListProjects/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<ProjectForm> FindByIdProject(@PathVariable ("id") Long id) throws ResourceNotFoundException
    {
        ProjectForm projectForm = Mapper.map(projectService.getById(id),
                ProjectForm.class);
        Link link= linkTo(methodOn(ViewController.class).FindByIdProject(id)).withSelfRel();
        EntityModel<ProjectForm> projectFormEntityModel=new EntityModel<ProjectForm>(projectForm,link);
        projectFormEntityModel.add(linkTo(methodOn(ViewController.class).FindByIdWorker(projectForm.getWorker_id())).withRel("Worker"));
        return projectFormEntityModel;
    }
}
