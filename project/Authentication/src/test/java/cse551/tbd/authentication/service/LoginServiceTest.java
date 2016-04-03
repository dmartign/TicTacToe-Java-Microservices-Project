package cse551.tbd.authentication.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import cse551.tbd.authentication.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @InjectMocks
    LoginService service;

    @Mock
    LoginRepository loginRepository;

    @Mock
    TokenGenerator tokenGenerator;

    @Mock
    UserNameExtractor userNameExtractor;

    @Test
    public void canLogin() throws Exception {
        String username = "username";
        String password = "password";
        String expected = "token";
        User user = User.builder().username(username).password(password).build();
        User toSave = User.builder().username(username).password(password).token(expected).build();

        when(this.loginRepository.findByUsername(username)).thenReturn(user);
        when(this.loginRepository.save(toSave)).thenReturn(user);
        when(this.tokenGenerator.generate()).thenReturn(expected);

        String actual = this.service.login(username, password);

        verify(this.loginRepository).save(toSave);
        assertThat(actual, is(expected));
    }

    @Test
    public void unknownUserGetsNoToken() throws Exception {
        String username = "username";
        String password = "password";

        when(this.loginRepository.findByUsername(username)).thenReturn(null);

        String actual = this.service.login(username, password);

        assertThat(actual, nullValue());
    }

    @Test
    public void passwordsMustMatch() throws Exception {
        String username = "username";
        String password = "password";
        String wrongPassword = "wrongPassword";
        User user = User.builder().username(username).password(password).build();

        when(this.loginRepository.findByUsername(username)).thenReturn(user);

        String actual = this.service.login(username, wrongPassword);

        assertThat(actual, nullValue());
    }

    @Test
    public void canLogout() throws Exception {
        String username = "username";
        String password = "password";
        String token = "token";
        User user = User.builder().username(username).password(password).token(token).build();
        User loggedOutUser = User.builder().username(username).password(password).build();
        when(this.loginRepository.findByToken(token)).thenReturn(user);

        this.service.logout(token);

        verify(this.loginRepository).save(loggedOutUser);
    }

    @Test
    public void canGetUser() throws Exception {
        String token = "token";
        User expected = Mockito.mock(User.class);
        when(this.loginRepository.findByToken(token)).thenReturn(expected);

        User actual = this.service.getUser(token);

        assertThat(actual, is(expected));
    }

    @Test
    public void canGetAllLoggedInUsers() throws Exception {
        List<User> users = new ArrayList<User>();
        users.add(User.builder().username("username").build());
        List<String> expected = new ArrayList<String>();
        expected.add("username");

        when(this.loginRepository.findByTokenIsNotNull()).thenReturn(users);
        when(this.userNameExtractor.extract(users)).thenReturn(expected);

        List<String> actual = this.service.getLoggedInUsers();

        assertThat(actual, is(expected));
    }
}
