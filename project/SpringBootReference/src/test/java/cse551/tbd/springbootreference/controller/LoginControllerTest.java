package cse551.tbd.springbootreference.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cse551.tbd.springbootreference.controller.dto.LoginRequest;
import cse551.tbd.springbootreference.controller.dto.LoginResponse;
import cse551.tbd.springbootreference.controller.dto.LogoutRequest;
import cse551.tbd.springbootreference.controller.dto.LogoutResponse;
import cse551.tbd.springbootreference.service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @InjectMocks
    LoginController controller = new LoginController();

    @Mock
    LoginService service = new LoginService();

    @Test
    public void canLogin() throws Exception {
        LoginRequest request = new LoginRequest("username", "password");
        LoginResponse expected = new LoginResponse("token");

        Mockito.when(this.service.login(request.getUsername(), request.getPassword())).thenReturn(expected.getToken());

        ResponseEntity<LoginResponse> actual = this.controller.login(request);

        assertThat(actual.getBody(), is(expected));
    }

    @Test
    public void canLogout() throws Exception {
        LogoutRequest request = new LogoutRequest("token");
        ResponseEntity<LogoutResponse> expected = new ResponseEntity<LogoutResponse>(HttpStatus.OK);

        ResponseEntity<?> actual = this.controller.logout(request);

        verify(this.service).logout(request.getToken());
        assertThat(actual, is(expected));
    }

}
