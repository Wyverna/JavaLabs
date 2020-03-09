package com.example.javalab;
import com.example.javalab.form.WorkerForm;
import com.example.javalab.model.Worker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
@Controller
@Slf4j
@Getter
@Setter
public class ViewController {
    List<Worker> workers;
    WorkerForm workerForm;
    WorkerForm workerForm2;
    int numberDelete;
    public ViewController()
    {
        workers=new LinkedList<>();
        workerForm=new WorkerForm();
        workerForm2=new WorkerForm();
    }
    @GetMapping("/")
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", "ПОИТ 3 курс");
        return modelAndView;
    }
    @GetMapping("/ListWorkers")
    public ModelAndView ListWorkers(Model model) {
        ModelAndView modelAndView = new ModelAndView("ListWorkers");
        ReadJSON();
        model.addAttribute("workers", workers);
        return modelAndView;
    }
    @GetMapping("/AddWorker")
    public ModelAndView AddWorker(Model model) {
        ModelAndView modelAndView = new ModelAndView("addWorker");
        ReadJSON();
        model.addAttribute("workers", workers);
        model.addAttribute("WorkerForm",workerForm);
        return modelAndView;
    }
    @GetMapping("/ChangeWorker")
    public ModelAndView pageChangeWorker(Model model) {
        ModelAndView modelAndView = new ModelAndView("changeWorker");
        ReadJSON();
        model.addAttribute("workers", workers);
        model.addAttribute("WorkerForm2",workerForm2);
        return modelAndView;
    }
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
            Worker changeWorker = new Worker(firstName, lastName, experienceWork, technologies,
                    birthday, position, age, telephone);
            for (int i = 0; i < workers.size(); i++) {
                if (workers.get(i).getFirstName().equals(changeWorker.getFirstName())) {
                    workers.remove(i);
                    workers.add(i,changeWorker);
                    break;
                }
            }
            model.addAttribute("workers", workers);
            log.info("Change Worker");
            WriteJSON();
        }
        return modelAndView;
    }
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
            Worker newPerson = new Worker(firstName, lastName, experienceWork, technologies,
                    birthday, position, age, telephone);
            workers.add(newPerson);
            log.info("Add new Worker");
            model.addAttribute("workers", workers);
            WriteJSON();
        }
        return modelAndView;
    }

    @GetMapping("/DeleteWorker")
    public ModelAndView pageDeleteWorker(Model model) {
        ModelAndView modelAndView = new ModelAndView("DeleteWorker");
        model.addAttribute("ViewController2",this);
        return modelAndView;
    }
    @PostMapping("/DeleteWorker")
    public ModelAndView DeleteWorker(Model model,@ModelAttribute("ViewController2") ViewController viewController) {
        ModelAndView modelAndView = new ModelAndView("ListWorkers");
        ReadJSON();
        model.addAttribute("workers", workers);
        try
        {
            System.out.println(viewController.getNumberDelete());
            workers.remove(viewController.getNumberDelete());
            log.info("Delete Worker");
        }
        catch(Exception e)
        {
            modelAndView.setViewName("DeleteWorker");
            System.out.println(e.getMessage());
            model.addAttribute("ErrorMessage",e.getMessage());
        }
        model.addAttribute("workers", workers);
        WriteJSON();
        return modelAndView;
    }
    public void WriteJSON()
    {
        Gson json = new Gson();
        String result = json.toJson(workers);
        File file = new File("E:\\Program Files\\JavaLab\\javalab3Sem2\\src\\main\\resources\\static\\data1.json");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            if (fos != null) {
                System.out.println(result);
                fos.write(result.getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ReadJSON()
    {
        Gson gson = new Gson();
        try {
            BufferedReader bufread = null;
            bufread = new BufferedReader(new FileReader("E:\\Program Files\\JavaLab\\javalab3Sem2\\src\\main\\resources\\static\\data1.json"));
            Type listType = new TypeToken<List<Worker>>() {
            }.getType();
            workers = (List<Worker>) gson.fromJson(bufread, listType);
        }
        catch(FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
