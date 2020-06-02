package com.spring.finalproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.finalproject.models.BeautyPost;

@Repository
public interface BeautyPostRepository extends CrudRepository<BeautyPost, Long>{
	List<BeautyPost> findAll();
}
