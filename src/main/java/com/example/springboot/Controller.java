package com.example.springboot;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {

	private static final String CLICKED = "smallBtnClicked";
	private static final String NOT_CLICKED = "smallBtn";

	private final int[] answers = {2, 4, 3, 1, 4, 2, 3, 1, 4};

	private int round = 1;
	private int score = 0;
	private int clicked = 0;

	@GetMapping("/")
	public String start() {
		return "start";
	}

	@GetMapping("/choice")
	public String choice(Model model) {
		model.addAttribute("smallBtn1", round + "1.png");
		model.addAttribute("smallBtn2", round + "2.png");
		model.addAttribute("smallBtn3", round + "3.png");
		model.addAttribute("smallBtn4", round + "4.png");
		model.addAttribute("clicked1", clicked == 1 ? CLICKED : NOT_CLICKED);
		model.addAttribute("clicked2", clicked == 2 ? CLICKED : NOT_CLICKED);
		model.addAttribute("clicked3", clicked == 3 ? CLICKED : NOT_CLICKED);
		model.addAttribute("clicked4", clicked == 4 ? CLICKED : NOT_CLICKED);
		model.addAttribute("selectDisabled", clicked == 0);
		model.addAttribute("bottomState", clicked == 0 ? "bottomDisabled" : "bottomEnabled");
		return "choice";
	}

	@PostMapping("/choice1")
	public String choice1() {
		clicked = 1;
		return "redirect:/choice";
	}

	@PostMapping("/choice2")
	public String choice2() {
		clicked = 2;
		return "redirect:/choice";
	}

	@PostMapping("/choice3")
	public String choice3() {
		clicked = 3;
		return "redirect:/choice";
	}

	@PostMapping("/choice4")
	public String choice4() {
		clicked = 4;
		return "redirect:/choice";
	}

	@PostMapping("/choiceSubmit")
	public String choiceSubmit() {
		if (answers[round - 1] == clicked) {
			score++;
		}
		clicked = 0;
		if (round == 9) {
			return "redirect:/end";
		}
		round++;
		return "redirect:/choice";
	}

	@GetMapping("/end")
	public String end(Model model) {
		String resultPic;
		if (score >= 6) {
			resultPic = "success.png";
		} else if (score >= 3) {
			resultPic = "norm.png";
		} else {
			resultPic = "fail.png";
		}
		model.addAttribute("bigImg", resultPic);
		model.addAttribute("result", "" + score + "/9! Играть ещё?");
		round = 1;
		score = 0;
		return "end";
	}

}
