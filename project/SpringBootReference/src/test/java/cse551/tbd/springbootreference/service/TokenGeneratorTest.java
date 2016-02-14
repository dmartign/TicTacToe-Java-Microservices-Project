package cse551.tbd.springbootreference.service;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

public class TokenGeneratorTest {

    TokenGenerator generator = new TokenGenerator();

    @Test
    public void generatesTokens() throws Exception {
        String token = this.generator.generate();
        assertThat(token.matches("[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}"), Matchers.is(true));
    }

}
