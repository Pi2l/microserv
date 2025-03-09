package com.example.backend.service;

import com.example.backend.exception.InvalidInputException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.Like;
import com.example.backend.model.Post;
import com.example.backend.model.Status;
import com.example.backend.model.User;
import com.example.backend.repository.LikeRepository;
import com.example.backend.repository.PostRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;

  public PostService(PostRepository postRepository, UserRepository userRepository) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;

  }

  public List<Doctor> getAllPosts() {
    return postRepository.findAll();
  }

  public List<Doctor> getAllApprovedPosts() {
    return postRepository.findByStatus(Status.APPROVED);
  }

  public Optional<Doctor> getPostById(Long id) {
    return postRepository.findById(id);
  }

  public List<Doctor> getLikedPostsByUserId(Long userId) {
    return postRepository.findLikedPostsByUserId(userId);
  }

  public Doctor createPost(Doctor post) {
    Optional<User> userOptional = userRepository.findById(post.getUser().getId());

    if (!userOptional.isPresent()) {
      throw new InvalidInputException("User with id " + post.getUser().getId() + " not found.");
    }

    post.setUser(userOptional.get());
    post.setCreatedAt(new Date());
    post.setUpdatedAt(new Date());
    post.setStatus(Status.PENDING_APPROVAL);

    return postRepository.save(post);
  }

  public void deletePost(Long id) {
    postRepository.deleteById(id);
  }

  // public Post updatePost(Long id, Post updatedPost) throws
  // ResourceNotFoundException {
  // Optional<Post> existingPost = postRepository.findById(id);
  //
  // if (!existingPost.isPresent()) {
  // throw new ResourceNotFoundException("Post with id " + id + " not found.");
  // }
  //
  // Post postToUpdate = existingPost.get();
  //
  // postToUpdate.setTitle(updatedPost.getTitle());
  // postToUpdate.setContent(updatedPost.getContent());
  // postToUpdate.setImageUrl(updatedPost.getImageUrl());
  // postToUpdate.setUpdatedAt(new Date());
  //
  // return postRepository.save(postToUpdate);
  // }
  public List<Doctor> getPostsByUserId(Long userId) {
    return postRepository.findByUserId(userId);
  }

  public Doctor approvePost(Long postId) {
    Doctor post = postRepository.findById(postId)
        .orElseThrow(() -> new InvalidInputException("Post not found with id: " + postId));
    post.setStatus(Status.APPROVED);
    return postRepository.save(post);
  }

  public Doctor rejectPost(Long postId) {
    Doctor post = postRepository.findById(postId)
        .orElseThrow(() -> new InvalidInputException("Post not found with id: " + postId));
    post.setStatus(Status.REJECTED);
    return postRepository.save(post);
  }

  public List<Doctor> getPostsByStatus(String status) {
    Status postStatus;

    try {
      postStatus = Status.valueOf(status.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new InvalidInputException("Invalid status: " + status);
    }

    return postRepository.findByStatus(postStatus);
  }

  public String uploadFile(MultipartFile file) {
    try {
      // Define the path to the directory where you want to save the image
      String uploadDir = "D:/Навчання/3_course/PublicOrg/uploads/images/";// TODO: replacae with uploadDir

      // Create a new file in the specified directory with the original file name
      File upload = new File(uploadDir + file.getOriginalFilename());

      // Transfer the uploaded file to the new file
      file.transferTo(upload);

      // Return the path to the uploaded file
      return "/images/" + file.getOriginalFilename();
    } catch (Exception e) {
      throw new RuntimeException("Failed to upload file", e);
    }
  }
}
