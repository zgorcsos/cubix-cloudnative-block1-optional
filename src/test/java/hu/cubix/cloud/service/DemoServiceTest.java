package hu.cubix.cloud.service;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class DemoServiceTest {

    @Test
    void createDemoResponse() {
        DemoService service = new DemoService();
        var message = "Hello";
        var response = service.createDemoResponse(message);
        assertThat(response.date(), is(notNullValue()));
        assertThat(response.time(), is(notNullValue()));
        assertThat(response.message(), is(message));
    }
}