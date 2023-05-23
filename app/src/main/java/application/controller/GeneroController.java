package application.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Genero;
import application.model.GeneroRepository;

@Controller
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepo;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("generos", generoRepo.findAll());
        return "WEB-INF-GENERO/list";
    }

    @RequestMapping("/insert")
    public String insert() {
        return "WEB-INF-GENERO/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome) {
        Genero genero = new Genero();
        genero.setNome(nome);

        generoRepo.save(genero);

        return "redirect:/WEB-INF-GENERO/list";
    }

    @RequestMapping("/update")
    public String update(Model model, @RequestParam("id") int id) {
        Optional<Genero> genero = generoRepo.findById(id);

        if(genero.isPresent()) {
            model.addAttribute("genero", genero.get());
            return "redirect:/WEB-INF-GENERO/list";
        }
        return "WEB-INF-GENERO/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
        @RequestParam("nome") String nome,
        @RequestParam("id") int id) {
        Optional<Genero> genero = generoRepo.findById(id);

        if(genero.isPresent()) {
            genero.get().setNome(nome);
        
            generoRepo.save(genero.get());
        }

        generoRepo.save(genero.get());
        return "redirect:/WEB-INFO-GENERO/list";
    }

    @RequestMapping("/delete")
    public String delete(Model model, @RequestParam("id") int id) {
        Optional<Genero> genero = generoRepo.findById(id);

        if(!genero.isPresent()) {
            model.addAttribute("genero", genero.get());
            return "redirect:/WEB-INFO-GENERO/delete";
        }
        
  

        return "WEB-INF-GENERO/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        generoRepo.deleteById(id);
        return "redirect:/WEB-INFO-GENERO/list";
    }
}
