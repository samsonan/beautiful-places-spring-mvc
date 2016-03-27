package com.samsonan.bplaces.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.SQLException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.samsonan.bplaces.config.WebConfig;
import com.samsonan.bplaces.model.User;
import com.samsonan.bplaces.test.TestUtils;
import com.samsonan.bplaces.test.config.TestContext;
import com.samsonan.bplaces.web.BaseController;
import com.samsonan.bplaces.web.UserController;

//http://docs.spring.io/spring-framework/docs/current/spring-framework-reference/html/unit-testing.html
//http://docs.spring.io/spring-framework/docs/current/spring-framework-reference/html/integration-testing.html

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
//  DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
@WebAppConfiguration
@DatabaseSetup("/bplace_user_init.xml")
@ActiveProfiles("junit_test")
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Before
    public void setUp() throws SQLException {
    	
    	//DbTestUtils.resetAutoIncrementColumns(webApplicationContext, "bplace_user");
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        //Mockito.reset(userServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    /**
     *
     //Unit Test --> Useless
    @Test
    public void findById_UserNotFound_ShouldRender404View() throws Exception {
        when(userServiceMock.findById(1)).thenReturn(null);
 
        mockMvc.perform(get("/users/{id}/update", 1))
                .andExpect(status().isOk())//not 404
                .andExpect(view().name("error/404"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/error/404.jsp"));
 
        verify(userServiceMock, times(1)).findById(ID);
        verifyZeroInteractions(userServiceMock);
    }    
    */
    
    @Test
    @ExpectedDatabase(value = "/bplace_user_init.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void findAll() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name(UserController.VIEW_USER_LIST))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/users/user_list.jsp"))
                .andExpect(model().attribute("userList", hasSize(3)))
				.andExpect(model().attribute("userList",
						hasItem(allOf(hasProperty("name", is("admin")),
								hasProperty("email", is("admin@email.com")), hasProperty("password", is("p@ss"))))))
				.andExpect(model().attribute("userList",
						hasItem(allOf(hasProperty("name", is("user_active")),
								hasProperty("email", is("user_active@email.com")), hasProperty("password", is("pass111"))))))
				.andExpect(model().attribute("userList",
						hasItem(allOf(hasProperty("name", is("user_pending")),
								hasProperty("email", is("user_pending@email.com")), hasProperty("password", is("pass222"))))));
    }

    @Test
    @ExpectedDatabase(value="/bplace_user_add_expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void addUser_byAdmin_noErrors() throws Exception {
		String expectedRedirectViewPath = TestUtils.createRedirectViewPath(UserController.REQUEST_USER_LIST);

		mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", "new user name")
				.param("email", "new_user@email.com")
				.param("firstName", "new user first")
				.param("lastName", "new user last")
				.param("role", User.ROLE_USER)
				.param("password", "P@ss111")
				.param("confirmPassword", "P@ss111")
				.sessionAttr("userForm", new User()))
		
				.andExpect(status().isFound())
				.andExpect(view().name(expectedRedirectViewPath))
				.andExpect(flash()
						.attribute(BaseController.FLASH_CSS_ATTR, is(BaseController.FLASH_CSS_VALUE_OK)));
	}

    @Test
    @ExpectedDatabase(value="/bplace_user_reg_expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void userRegistration_noErrors() throws Exception {
		String expectedRedirectViewPath = TestUtils.createRedirectViewPath(UserController.REQUEST_MAP);

		mockMvc.perform(post("/register")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", "reg user name")
				.param("email", "reg_user@email.com")
				.param("firstName", "reg user first")
				.param("lastName", "reg user last")
				.param("password", "P@ss222")
				.param("confirmPassword", "P@ss222")
				.sessionAttr("user", new User()))
		
				.andExpect(status().isFound())
				.andExpect(view().name(expectedRedirectViewPath));
	}
    
    
    // http://www.petrikainulainen.net/programming/spring-framework/integration-testing-of-spring-mvc-applications-forms/
    // TODO: submit empty form
    // TODO: submit form with wrong values / validation errors
    
	
}
