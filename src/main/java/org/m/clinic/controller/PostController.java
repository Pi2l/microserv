package com.example.backend.controller;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.Post;
import com.example.backend.model.User;
import com.example.backend.service.PostLikeService;
import com.example.backend.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

  private final PostService postService;
  private final PostLikeService postLikeService;

  public PostController(PostService postService, PostLikeService postLikeService) {
    this.postService = postService;
    this.postLikeService = postLikeService;
  }

  @GetMapping
  public ResponseEntity<List<Doctor>> getAllPosts() {
    return ResponseEntity.ok(postService.getAllPosts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Doctor> getPostById(@PathVariable Long id) throws ResourceNotFoundException {
    Doctor post = postService.getPostById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found."));
    return ResponseEntity.ok(post);
  }

  @GetMapping("/{id}/image")
  public ResponseEntity<byte[]> getPostImage(@PathVariable Long id) throws ResourceNotFoundException {
    Doctor post = postService.getPostById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found."));
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(post.getImage());
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Doctor>> getPostsByUserId(@PathVariable Long userId) {
    List<Doctor> posts = postService.getPostsByUserId(userId);
    return ResponseEntity.ok(posts);
  }

  @PostMapping
  public ResponseEntity<Doctor> createPost(
      @RequestParam("post") String postJson,
      @RequestParam("imageFile") MultipartFile imageFile) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Doctor post = mapper.readValue(postJson, Doctor.class);

    try {
      // Додаємо зображення як масив байтів у пост
      post.setImage(imageFile.getBytes());
    } catch (IOException e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    Doctor createdPost = postService.createPost(post);
    return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
  }

  // @PutMapping("/{id}")
  // public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody
  // Post updatedPost) throws ResourceNotFoundException {
  // Post updated = postService.updatePost(id, updatedPost);
  // return ResponseEntity.ok(updated);
  // }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePost(@PathVariable Long id) {
    postService.deletePost(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{postId}/approve")
  public ResponseEntity<Doctor> approvePost(@PathVariable Long postId) {
    Doctor post = postService.approvePost(postId);
    return ResponseEntity.ok(post);
  }

  @PutMapping("/{postId}/reject")
  public ResponseEntity<Doctor> rejectPost(@PathVariable Long postId) {
    Doctor post = postService.rejectPost(postId);
    return ResponseEntity.ok(post);
  }

  @GetMapping("/user/{userId}/likes")
  public ResponseEntity<List<Doctor>> getLikedPostsByUserId(@PathVariable Long userId) {
    List<Doctor> posts = postService.getLikedPostsByUserId(userId);
    return ResponseEntity.ok(posts);
  }

  @PutMapping("/{postId}/toggle-like/{userId}")
  public ResponseEntity<Doctor> toggleLike(@PathVariable Long postId, @PathVariable Long userId)
      throws ResourceNotFoundException {
    Doctor post = postLikeService.toggleLike(postId, userId);
    return ResponseEntity.ok(post);
  }

  @GetMapping("/status/{status}")
  public ResponseEntity<List<Doctor>> getPostsByStatus(@PathVariable String status) {
    List<Doctor> posts = postService.getPostsByStatus(status);
    return ResponseEntity.ok(posts);
  }

}
