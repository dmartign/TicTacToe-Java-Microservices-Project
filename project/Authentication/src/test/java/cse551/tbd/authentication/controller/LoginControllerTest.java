package cse551.tbd.authentication.controller;

import static org.hamcrest.Matchers.is;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cse551.tbd.authentication.controller.dto.LoginRequest;
import cse551.tbd.authentication.controller.dto.LogoutResponse;
import cse551.tbd.authentication.service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @InjectMocks
    LoginController controller = new LoginController();

    @Mock
    LoginService service = new LoginService();

    @Test
    public void canLogin() throws Exception {
        LoginRequest request = new LoginRequest("username", "password");
        String expected = "token";

        Mockito.when(this.service.login(request.getUsername(), request.getPassword())).thenReturn(expected);

        ResponseEntity<String> actual = this.controller.login(request);

        assertThat(actual.getBody(), is(expected));
    }

    @Test
    public void canLogout() throws Exception {
        String token = "token";
        ResponseEntity<LogoutResponse> expected = new ResponseEntity<LogoutResponse>(HttpStatus.OK);

        ResponseEntity<?> actual = this.controller.logout(token);

        verify(this.service).logout(token);
        assertThat(actual, is(expected));
    }

    @Test
    public void canGetAllLoggedInUsers() throws Exception {
        List<String> expected = new ArrayList<String>();
        expected.add("user");

        when(this.service.getLoggedInUsers()).thenReturn(expected);

        ResponseEntity<List<String>> actual = this.controller.getLoggedInUsers();

        assertThat(actual.getStatusCode(), is(HttpStatus.OK));
        assertThat(actual.getBody(), is(expected));
    }

}
