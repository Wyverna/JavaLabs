package com.example.javalab;
import com.example.javalab.AOP.AuthorizeAnnotation;
import com.example.javalab.AOP.LogAnnotation;
import com.example.javalab.dto.ProjectForm;
import com.example.javalab.dto.WorkerForm;
import com.example.javalab.entity.Project;
import com.example.javalab.entity.User;
import com.example.javalab.entity.Worker;
import com.example.javalab.service.ProjectService;
import com.example.javalab.service.UserService;
import com.example.javalab.service.WorkerService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

@Controller
@Getter
@Setter
public class ViewController {
    List<Worker> workers;
    List<Project> projects;
    WorkerForm workerForm;
    WorkerForm workerForm2;
    ProjectForm projectForm;
    UserService userService;
    WorkerService workerService;
    ProjectService projectService;
    @Autowired
    public ViewController(UserService userService,WorkerService workerService,ProjectService projectService)
    {
        this.projectService=projectService;
        this.userService=userService;
        this.workerService=workerService;
        workers=new LinkedList<>();
        workerForm=new WorkerForm();
        workerForm2=new WorkerForm();
        projectForm=new ProjectForm();
    }
    @GetMapping("/index")
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", "ПОИТ 3 курс");
        return modelAndView;
    }
    @GetMapping("/")
    public ModelAndView loginpage(Model model) {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
    @PostMapping("/")
    public ModelAndView login(Model model, HttpSession session, @RequestParam("Username") String login, @RequestParam("Password") String password) {
        ModelAndView modelAndView = new ModelAndView("login");
        String hashpass=md5Custom(password);
        List<User> users=userService.getUser(login,hashpass);
        if(users.size()>0)
        {
            System.out.println(users.get(0).getRole());
            session.setAttribute("role",users.get(0).getRole());
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }
    @GetMapping("/ListProjects")
    public ModelAndView ListProjects(Model model) {
        ModelAndView modelAndView = new ModelAndView("ListProjects");
        projects = projectService.findProjects();
        model.addAttribute("projects", projects);
        return modelAndView;
    }
    @GetMapping("/ListWorkers")
    public ModelAndView ListWorkers(Model model) {
        ModelAndView modelAndView = new ModelAndView("ListWorkers");
        workers = workerService.findWorkers();
        model.addAttribute("workers", workers);
        return modelAndView;
    }
    @GetMapping("/AddWorker")
    @AuthorizeAnnotation
    public ModelAndView AddWorker(Model model) {
        ModelAndView modelAndView = new ModelAndView("addWorker");
        workers = workerService.findWorkers();
        model.addAttribute("workers", workers);
        model.addAttribute("WorkerForm",workerForm);
        return modelAndView;
    }
    @GetMapping("/AddProject")
    @AuthorizeAnnotation
    public ModelAndView AddProject(Model model) {
        ModelAndView modelAndView = new ModelAndView("addProject");
        projects = projectService.findProjects();
        model.addAttribute("projects", projects);
        model.addAttribute("ProjectForm",projectForm);
        return modelAndView;
    }
    @LogAnnotation
    @PostMapping("/AddProject")
    public ModelAndView SaveProject(Model model,
                                   @Valid @ModelAttribute("ProjectForm") ProjectForm projectForm,
                                   Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("addProject");
        }
        else {
            modelAndView.setViewName("ListProjects");
            String firstName = projectForm.getFirstName();
            String nameProject = projectForm.getNameProject();
            Long id=projectForm.getId();
            Project newProject = new Project(id,firstName, nameProject);
            newProject.setWorker(workerService.getById(projectForm.getWorker_Id()).get());
            Worker worker=newProject.getWorker();
            worker.setProjects(projectService.findProjectsByWorker(worker.getId()));
            workerService.UpdateWorker(worker);
            projectService.SaveProject(newProject);
            projects=projectService.findProjects();
            model.addAttribute("projects", projects);
        }
        return modelAndView;
    }
    @GetMapping("/ChangeWorker")
    @AuthorizeAnnotation
    public ModelAndView pageChangeWorker(Model model,@RequestParam("id") long id) {
        ModelAndView modelAndView = new ModelAndView("changeWorker");
        workers = workerService.findWorkers();
        Worker worker=workerService.getById(id).get();
        workerForm2=new WorkerForm(worker.getId(),worker.getFirstName(),worker.getLastName(),worker.getExperienceWork(),
                worker.getTechnologies(),worker.getBirthday(),worker.getPosition(),worker.getAge(),
                worker.getTelephone());
        model.addAttribute("workers", workers);
        model.addAttribute("WorkerForm2",workerForm2);
        return modelAndView;
    }
    @GetMapping("/ChangeProject")
    @AuthorizeAnnotation
    public ModelAndView pageChangeProject(Model model,@RequestParam("id") long id) {
        ModelAndView modelAndView = new ModelAndView("changeProject");
        projects = projectService.findProjects();
        Project project=projectService.getById(id).get();
        projectForm=new ProjectForm(project.getId(),project.getFirstName(),project.getNameProject(),project.getWorker().getId());
        model.addAttribute("projects", projects);
        model.addAttribute("ProjectForm",projectForm);
        return modelAndView;
    }
    @LogAnnotation
    @PostMapping("/ChangeWorker")
    public ModelAndView ChangeWorker(Model model,
                                     @Valid @ModelAttribute("WorkerForm2") WorkerForm workerForm,
                                     Errors errors) {

        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("changeWorker");
        }
        else {
            modelAndView.setViewName("ListWorkers");
            String firstName = workerForm.getFirstName();
            String lastName = workerForm.getLastName();
            Integer experienceWork = workerForm.getExperienceWork();
            String technologies = workerForm.getTechnologies();
            Date birthday = workerForm.getBirthday();
            String position = workerForm.getPosition();
            Integer age = workerForm.getAge();
            String telephone = workerForm.getTelephone();
            Long id=workerForm.getId();
            Worker changeWorker = new Worker(id,firstName, lastName, experienceWork, technologies,
                    birthday, position, age, telephone);
            workerService.UpdateWorker(changeWorker);
            workers=workerService.findWorkers();
            model.addAttribute("workers", workers);
        }
        return modelAndView;
    }
    @LogAnnotation
    @PostMapping("/ChangeProject")
    public ModelAndView ChangeProject(Model model,
                                     @Valid @ModelAttribute("ProjectForm") ProjectForm projectForm,
                                     Errors errors) {

        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("changeProject");
        }
        else {
            modelAndView.setViewName("ListProjects");
            String firstName = projectForm.getFirstName();
            String nameProject = projectForm.getNameProject();
            Long id=projectForm.getId();
            Project changeProject = new Project(id,firstName, nameProject);
            changeProject.setWorker(workerService.getById(projectForm.getWorker_Id()).get());
            Worker worker=changeProject.getWorker();
            worker.setProjects(projectService.findProjectsByWorker(worker.getId()));
            workerService.UpdateWorker(worker);
            projectService.UpdateProject(changeProject);
            projects=projectService.findProjects();
            model.addAttribute("projects", projects);
        }
        return modelAndView;
    }
    @LogAnnotation
    @PostMapping("/AddWorker")
    public ModelAndView SaveWorker(Model model,
                                   @Valid @ModelAttribute("WorkerForm") WorkerForm workerForm,
                                   Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("addWorker");
        }
        else {
            modelAndView.setViewName("ListWorkers");
            String firstName = workerForm.getFirstName();
            String lastName = workerForm.getLastName();
            Integer experienceWork = workerForm.getExperienceWork();
            String technologies = workerForm.getTechnologies();
            Date birthday = workerForm.getBirthday();
            String position = workerForm.getPosition();
            Integer age = workerForm.getAge();
            String telephone = workerForm.getTelephone();
            Long id=workerForm.getId();
            Worker newPerson = new Worker(id,firstName, lastName, experienceWork, technologies,
                    birthday, position, age, telephone);
            workerService.SaveWorker(newPerson);
            workers=workerService.findWorkers();
            model.addAttribute("workers", workers);
        }
        return modelAndView;
    }
    @LogAnnotation
    @AuthorizeAnnotation
    @GetMapping("/DeleteWorker")
    public ModelAndView DeleteWorker(Model model,@RequestParam("id") long id) {
        ModelAndView modelAndView = new ModelAndView("ListWorkers");
        workerService.DeleteWorker(id);
        workers=workerService.findWorkers();
        model.addAttribute("workers", workers);
        return modelAndView;
    }
    @LogAnnotation
    @AuthorizeAnnotation
    @GetMapping("/DeleteProject")
    public ModelAndView DeleteProject(Model model,@RequestParam("id") long id) {
        ModelAndView modelAndView = new ModelAndView("ListProjects");
        projectService.DeleteProject(id);
        projects=projectService.findProjects();
        model.addAttribute("projects", projects);
        return modelAndView;
    }
    public static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }
}
