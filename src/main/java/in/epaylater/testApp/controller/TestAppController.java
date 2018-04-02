package in.epaylater.testApp.controller;

import in.epaylater.testApp.model.LoginRequest;
import in.epaylater.testApp.model.ResponseEntity;
import in.epaylater.testApp.model.TransactionDetails;
import in.epaylater.testApp.service.TestAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/api/testApp")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestAppController {

    private static final Logger log = LoggerFactory.getLogger("testApp");

    @Context
    private SecurityContext security;

    @Context
    private HttpServletRequest request;

    private TestAppService testAppService;

    @Inject
    public TestAppController(final TestAppService testAppService) {
        this.testAppService = testAppService;
    }

    @Path(value = "/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity login(@Valid final LoginRequest loginRequest) {
        log.info("login request received for phone: " + loginRequest.getPhone());
        return testAppService.loginToken(loginRequest.getPhone());
    }

    @Path(value = "/spend")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity payment(@Valid final TransactionDetails transactionDetails) {
        final String authTokenReceived = request.getHeader("Authorization");

        log.info("spend request received : " + transactionDetails.toString());
        return testAppService.spend(transactionDetails,authTokenReceived);
    }

}
