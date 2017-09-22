package com.alexbt.jpa;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@Controller
public class JpaController {

	@Autowired
	private ModelJpaRepository modelJpaRepository;

	@RequestMapping("/index")
	public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
		//model.addAttribute("name", name);
		return "index";
	}

	@RequestMapping(path="/jpa/repo", method = RequestMethod.GET)
	public Iterable<Model> findByRepo() throws IOException {
		return modelJpaRepository.findAll();
	}
	
	@RequestMapping(value = "/jpa/repo/{value}", method = RequestMethod.GET)
	public void saveByRepo(@PathVariable String value) {
		Model model = new Model();
		model.setId(System.currentTimeMillis());
		model.setValue(value);
		modelJpaRepository.save(model);
	}
}
