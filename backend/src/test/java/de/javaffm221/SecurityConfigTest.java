package de.javaffm221;

import de.javaffm221.appuser.AppUser;
import de.javaffm221.appuser.AppUserRole;
import de.javaffm221.appuser.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecurityConfigTest {

    private final AppUserService appUserService = mock(AppUserService.class);
    private final SecurityConfig securityConfig = new SecurityConfig(appUserService);

    @Test
    void findByUsernameReturnsUser() {
        //given
        String username = "tobias";
        String rawPassword = "pw";
        String encodedPassword = securityConfig.encoder().encode(rawPassword);
        when(appUserService.findByUsername(username)).thenReturn(new AppUser("user1",username,encodedPassword, AppUserRole.BASIC));
        //when
        String actual = securityConfig.userDetailsService()
                .loadUserByUsername(username)
                .getPassword();
        //then
        System.out.println(actual);
        assertTrue(securityConfig.encoder().matches(rawPassword, actual));
    }

    @Test
    void findByInvalidUsernameReturnsError() {
        //given
        String username = "test";
        String rawPassword = "pw";
        String encodedPassword = securityConfig.encoder().encode(rawPassword);
        when(appUserService.findByUsername(username)).thenReturn(null);
        //when
        try {
            securityConfig.userDetailsService()
                    .loadUserByUsername(username)
                    .getPassword();
            fail();
        } catch(UsernameNotFoundException e) {
            //then
            verify(appUserService).findByUsername(username);
            assertEquals("Username \"" + username + "\" not found!",e.getMessage());
        }
    }
}