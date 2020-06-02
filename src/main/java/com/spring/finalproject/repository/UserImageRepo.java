package com.spring.finalproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.finalproject.models.UserImage;

@Repository
public interface UserImageRepo extends CrudRepository<UserImage, Long>{
	List<UserImage> findAll();
}
