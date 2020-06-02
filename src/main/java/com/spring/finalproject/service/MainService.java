package com.spring.finalproject.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.finalproject.models.BeautyPost;
import com.spring.finalproject.models.Comment;
import com.spring.finalproject.models.User;
import com.spring.finalproject.models.UserImage;
import com.spring.finalproject.repository.BeautyPostRepository;
import com.spring.finalproject.repository.CommentRepository;
import com.spring.finalproject.repository.UserImageRepo;
import com.spring.finalproject.repository.UserRepository;

@Service
public class MainService {
	private final UserRepository uRepository;
	private final CommentRepository cRepository;
	private final BeautyPostRepository bpRepository;
	private final UserImageRepo uiRepo;
	
	public MainService(UserRepository uRepo, UserImageRepo uiRepo, BeautyPostRepository bpRepo, CommentRepository cRepo) {
		this.uRepository = uRepo;
		this.bpRepository = bpRepo;
		this.cRepository = cRepo;
		this.uiRepo = uiRepo;
	}
	
	public User registerUser(User u) {
		String hashed = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
		u.setPassword(hashed);
		return uRepository.save(u);
	}
	
	// find user by email
    public User getUserByEmail(String email) {
        return uRepository.findByEmail(email);
    }
    
    // find user by id
    public User getUserById(Long id) {
    	Optional<User> u = uRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    //authenticate user
    public boolean authenticateUser(String email, String passsword) {
    	//first find user by email
    	User user = uRepository.findByEmail(email);
    	//if user is not available then return false
    	if(user == null) {
    		return false;
    	}else {
    		//check password whether it matches with stored password or not
    		if(BCrypt.checkpw(passsword, user.getPassword())) {
    			return true;
    		}
    		return false;
    	}
    }
    
    public User saveUser(User u) {
    	return uRepository.save(u);
    }
    
    public Comment saveComment(Comment c) {
    	return cRepository.save(c);
    }
    
    public BeautyPost saveBeautyPost(BeautyPost bp) {
    	return bpRepository.save(bp);
    }
    
    public List<BeautyPost> getAllPosts(){
    	return bpRepository.findAll();
    }
    
    public List<Comment> getAllComments(){
    	return cRepository.findAll();
    }
    
    public List<User> getAllUsers(){
    	return uRepository.findAll();
    }
    public List<UserImage> getAllUserImages(){
    	return uiRepo.findAll();
    }
    public BeautyPost getBeautyPostById(Long id) {
    	Optional <BeautyPost> postOptional = bpRepository.findById(id);
    	if(postOptional.isPresent()) {
    		return postOptional.get();
    	}
    	return null;
    }
    
    public UserImage getImageById(Long id) {
    	Optional<UserImage> uiOptional = uiRepo.findById(id);
    	if(uiOptional.isPresent()) {
    		return uiOptional.get();
    	}
    	return null;
    }

	public static void saveImage(MultipartFile imageFile) throws Exception{
		String folder = "/javaproject/photos/";
		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(folder + imageFile.getOriginalFilename());
		Files.write(path, bytes);
	}
	
	public UserImage saveUserImage(UserImage ui) {
		return uiRepo.save(ui);
	}
	
//	public MultipartFile getImages() {
//		return "";
//	}
}
