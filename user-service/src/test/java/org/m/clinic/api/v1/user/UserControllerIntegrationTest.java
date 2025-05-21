package org.m.clinic.api.v1.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.m.clinic.api.v1.shared.PageResponse;
import org.m.clinic.model.Role;
import org.m.clinic.model.User;
import org.m.clinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@Rollback
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private UserRepository userRepository;
  @Autowired private ObjectMapper objectMapper;

  private static final Random random = new Random();

  @BeforeEach
  void setUp() {
  }

  @Test void getAllUsersTest() {
    userRepository.save( getUserEntity() );
    userRepository.save( getUserEntity() );
    var expected = userRepository.findAll();
    PageResponse<User> expectedResponse = new PageResponse<>( expected, expected.size(), 0, 30 );

    assertDoesNotThrow(() -> mockMvc.perform( get(UserController.API_URL) )
            .andExpect( content().json( getJson( expectedResponse ) ) )
            .andExpect( status().isOk() ));

    assertEquals(2, expected.size());
  }

  @Test void getUserByIdTest() {
    var user = userRepository.save( getUserEntity() );

    assertDoesNotThrow(() -> mockMvc.perform( get(UserController.API_URL + "/" + user.getId()) )
            .andExpect( content().json( getJson( user ) ) )
            .andExpect( status().isOk() ));
  }

  @Test void getUserByIdNotFoundTest() {
    var user = getUserEntity();
    user.setId( 1L );
    assertTrue( userRepository.findById( user.getId() ).isEmpty() );

    assertDoesNotThrow( () -> mockMvc.perform( get(UserController.API_URL + "/" + user.getId()) )
            .andDo(print())
            .andExpect( status().isNotFound() ));
  }

  @Test void createUserTest() {
    var user = getUserEntity();
    assertEquals( 0, userRepository.findAll().size() );

    assertDoesNotThrow( () -> mockMvc.perform( post(UserController.API_URL)
                              .content( getJson( user ) )
                              .contentType( MediaType.APPLICATION_JSON )
                              .accept( MediaType.APPLICATION_JSON ) )
            .andExpect( content().json( getJson( user ) ) )
            .andExpect( status().isCreated() ));

    assertEquals( 1, userRepository.findAll().size() );
  }

  @Test void updateUserTest() {
    var userToUpdate = userRepository.save( getUserEntity() );
    var user = getUserEntity();
    user.setId( userToUpdate.getId() );
    assertEquals( 1, userRepository.findAll().size() );

    assertDoesNotThrow( () -> mockMvc.perform( put(UserController.API_URL + "/" + userToUpdate.getId())
                    .content( getJson( user ) )
                    .contentType( MediaType.APPLICATION_JSON )
                    .accept( MediaType.APPLICATION_JSON ) )
            .andExpect( content().json( getJson( user ) ) )
            .andExpect( status().isNoContent() ));

    assertEquals( 1, userRepository.findAll().size() );
  }

  @Test void updateUserNotFoundTest() {
    var userToUpdate = userRepository.save( getUserEntity() );
    var user = getUserEntity();
    user.setId( userToUpdate.getId() );
    assertEquals( 1, userRepository.findAll().size() );

    assertDoesNotThrow( () -> mockMvc.perform( put(UserController.API_URL + "/" + userToUpdate.getId() + 1L)
                    .content( getJson( user ) )
                    .contentType( MediaType.APPLICATION_JSON )
                    .accept( MediaType.APPLICATION_JSON ) )
            .andExpect( status().isNotFound() ));
  }

  @Test void deleteUserByIdTest() {
    var user = userRepository.save( getUserEntity() );
    assertEquals( 1, userRepository.findAll().size() );

    assertDoesNotThrow( () -> mockMvc.perform( delete(UserController.API_URL + "/" + user.getId()) )
            .andExpect( status().isNoContent() ));

    assertEquals( 0, userRepository.findAll().size() );
  }

  private User getUserEntity() {
    long value = random.nextLong() % 1000;
    return User.builder()
            .email( "email_" + value + "@e.e" )
            .firstName( "firstName_" + value )
            .lastName( "lastName_" + value )
            .password( "password_" + value )
            .role( Role.PATIENT )
            .build();
  }

  private String getJson(Object obj) throws JsonProcessingException {
    return objectMapper.setSerializationInclusion( JsonInclude.Include.NON_NULL ).writeValueAsString( obj );
  }

}
