package de.neuefische.backend.appuser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    private AppUserRepo mockAppUserRepo;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @InjectMocks
    private AppUserService appUserService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void findByUsername_userExists() {
        AppUser expectedUser = new AppUser("123", "testuser", "encodedpassword");
        when(mockAppUserRepo.findAppUserByUsername("testuser")).thenReturn(Optional.of(expectedUser));

        AppUser result = appUserService.findByUsername("testuser");

        verify(mockAppUserRepo).findAppUserByUsername("testuser");
        assertEquals(expectedUser, result);
    }

    @Test
    void findByUsername_userDoesNotExist() {
        when(mockAppUserRepo.findAppUserByUsername("testuser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> appUserService.findByUsername("testuser"));

        verify(mockAppUserRepo).findAppUserByUsername("testuser");
    }

    @Test
    void getLoggedInUser() {
        User principal = new User("testuser", "password", new ArrayList<>());
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(principal);

        AppUser appUser = new AppUser();
        appUser.setId("123");
        appUser.setUsername("testuser");

        when(mockAppUserRepo.findAppUserByUsername("testuser")).thenReturn(Optional.of(appUser));

        AppUserResponse response = appUserService.getLoggedInUser();

        assertEquals("123", response.id());
        assertEquals("testuser", response.username());
    }

    @Test
    void register() {
        AppUserRegister appUserRegister = new AppUserRegister("testuser", "password");
        String encodedPassword = "encodedpassword";
        when(mockPasswordEncoder.encode("password")).thenReturn(encodedPassword);

        AppUser savedUser = new AppUser("123", "testuser", encodedPassword);
        when(mockAppUserRepo.save(any(AppUser.class))).thenReturn(savedUser);

        AppUserResponse result = appUserService.register(appUserRegister);

        verify(mockPasswordEncoder).encode("password");
        verify(mockAppUserRepo).save(any(AppUser.class));
        assertEquals(new AppUserResponse("123", "testuser"), result);
    }
}
