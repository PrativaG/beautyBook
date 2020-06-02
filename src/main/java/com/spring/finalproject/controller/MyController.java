package com.spring.finalproject.controller;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
//import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.finalproject.models.BeautyPost;
import com.spring.finalproject.models.Comment;
import com.spring.finalproject.models.User;
import com.spring.finalproject.models.UserImage;
import com.spring.finalproject.repository.UserImageRepo;
import com.spring.finalproject.service.MainService;
import com.spring.finalproject.validator.UserValidator;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.*;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.lang.String;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.ui.Model;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class MyController {
	
//    private static Logger LOGGER = Logger.getLogger(MyController.class.getName());
	
	@Value("${uploadDir}")
	private String uploadFolder;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final MainService service;
	private final UserValidator userValidator;
	
	public MyController(MainService mService, UserValidator uValid) {
		this.service = mService;
		this.userValidator = uValid;
	}
	
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerUser(
    		@Valid @ModelAttribute("user") User user,
    		BindingResult result, 
    		HttpSession session
    		) {
        userValidator.validate(user, result);
        if(result.hasErrors()) {
        	return "register.jsp";
        }
        User u = service.registerUser(user);
        session.setAttribute("userid", u.getId());
        return "redirect:/home";
    }
	
	//for displaying login page
	@RequestMapping("/login")
	public String login() {
		return "login.jsp";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(
    		@RequestParam("email") String email, 
    		@RequestParam("password") String password, 
    		Model model,
    		HttpSession session,
    		RedirectAttributes redirectAttributes
    		) {

        if(service.authenticateUser(email, password) == true) {
           User u = service.getUserByEmail(email);
           session.setAttribute("userid", u.getId());
    	   return "redirect:/home";
        }else {
        	if(email == "") {
        		System.out.println("emailnull");
        	}
	         redirectAttributes.addFlashAttribute("error", "Email or password incorect!");
	         return "redirect:/login";
       }
    }
	
	@RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userid");
        return "redirect:/";
    }
	
	@RequestMapping("/home")
	public String home(
			Model model,
			HttpSession session,
			@ModelAttribute("newPost") BeautyPost bpost,
			@ModelAttribute("newComment") Comment comment
			) {
		Date date = new Date();
		DateFormat timeDateFormat = new SimpleDateFormat("HHmm");
		Long time = Long.parseLong(timeDateFormat.format(date)) ;
		String message = "";
		if(time < 1200) {
			message = "Good Morning"; 
		}
		if(time > 1200 && time < 1700) {
			message = "Good Afternoon"; 
		}	
		else {
			message = "Good Evening"; 
		}
		session.setAttribute("message", message);
		model.addAttribute("message", message);
		
		User user = service.getUserById((Long)session.getAttribute("userid"));
		model.addAttribute("user", user);
		
		List<BeautyPost> posts = service.getAllPosts();
		model.addAttribute("allPosts", posts);
		
		List<Comment> comments = service.getAllComments();
		model.addAttribute("allComments", comments);
				
		List<UserImage> allUserImages = service.getAllUserImages(); 
		model.addAttribute("userImages", allUserImages);
		return "home.jsp";
	}
	
	@GetMapping("/image/{userImageId}")
	@ResponseBody
	void showImage(@PathVariable("id") Long userImageId, HttpServletResponse response, UserImage ui)
			throws ServletException, IOException {

		ui = service.getImageById(userImageId);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(ui.getImage());
		response.getOutputStream().close();
	}
	
	@PostMapping("/home")
	public String createTips(
		@Valid @ModelAttribute("newPost") BeautyPost bpost,
		BindingResult result,
		Model model,
		HttpSession session
		
			) {
		
		if(result.hasErrors()) {
			User user = service.getUserById((Long)session.getAttribute("userid"));
			model.addAttribute("user", user);
			model.addAttribute("message", session.getAttribute("message"));
			return "home.jsp";
		}
		User user = service.getUserById((Long)session.getAttribute("userid"));
		model.addAttribute("user", user);
		service.saveBeautyPost(bpost);
		return "redirect:/home";
	}
	
	@RequestMapping("/face/{id}")
	public String faceShape(
		@PathVariable("id") Long userid,
		Model model,
		HttpSession session
			) {
		User user = service.getUserById((Long)session.getAttribute("userid"));
		model.addAttribute("user", user);
		return "faceShape.jsp";
	}
	
	@PostMapping("/face/{id}")
	public String saveFace(
		@PathVariable("id") Long userid,
		@RequestParam("faceShape") String faceShape,
		Model model,
		HttpSession session
			) {
		User user = service.getUserById((Long)session.getAttribute("userid"));
		model.addAttribute("user", user);
		user.setFaceShape(faceShape);
		service.saveUser(user);
		return "redirect:/face/" +userid;
	}
	
	@RequestMapping("/register")
	public String register(    		
		@ModelAttribute("user") User user
			) {
		return "register.jsp";
	}
	
	@PostMapping("/comment")
	public String createComment(
		@Valid @ModelAttribute("newComment") Comment comment,
		BindingResult resultC,
		Model model,
		HttpSession session
			){
		User user = service.getUserById((Long)session.getAttribute("userid"));
		if(resultC.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("message", session.getAttribute("message"));
			return "home.jsp";
		}
		service.saveComment(comment);
		return "redirect:/home";
	}
	
	@GetMapping("/like/{pid}")
	public String likePosts(
		@PathVariable("pid") Long postId,
		HttpSession session
			) {
		User user = service.getUserById((Long)session.getAttribute("userid"));
		BeautyPost bPost  = service.getBeautyPostById(postId);
		if(bPost.getLikes() != null ) {
			bPost.setLikes(bPost.getLikes() + 1);
			service.saveBeautyPost(bPost);
		}else {
			bPost.setLikes(1);
			service.saveBeautyPost(bPost);
		}	
		return "redirect:/home";
	}
	
	
	@PostMapping("/uploadImage")
	public String uploadImage(Model model, HttpServletRequest request,  HttpSession session,
			final @RequestParam("image") MultipartFile file) {
		try {
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
//				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
				return "Invalid path";
			}
			
			
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			User user = service.getUserById((Long)session.getAttribute("userid"));
			UserImage uImage = new UserImage();
			uImage.setImage(imageData);
			uImage.setUser(user);
			service.saveUserImage(uImage);
//			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
//			return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
			return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return "BAd request";
			}
		}
	
}
