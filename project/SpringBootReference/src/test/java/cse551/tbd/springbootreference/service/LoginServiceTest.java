package cse551.tbd.springbootreference.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import cse551.tbd.springbootreference.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @InjectMocks
    LoginService service;

    @Mock
    LoginRepository loginRepository;

    @Mock
    TokenGenerator tokenGenerator;

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
}
