package com.be.web.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.be.web.service.UserService;
import com.be.web.common.util.Constants;
import com.be.web.dto.request.UserData;
import com.be.web.dto.response.CommonResponse;
import com.be.web.entities.User;

@Controller
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private TokenStore tokenStore;
    
    @Autowired
    private UserService userService;

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/users/extra")
    @ResponseBody
    public Map<String, Object> getExtraInfo(OAuth2Authentication auth) {
        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        final OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        System.out.println(accessToken);
        return accessToken.getAdditionalInformation();
    }
    
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal, HttpServletRequest request) {
    	//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	//String user=request.getUserPrincipal().getName();
        return principal.getName();
    }
    
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    @ResponseBody
    public Collection<? extends GrantedAuthority> currentUserName(Authentication authentication) {
    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    	return userDetails.getAuthorities();
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<User> createUser(@RequestBody UserData user) {
    	CommonResponse<User> reponse = new CommonResponse<User>();
    	
    	User createdUser;
    	try {
    		createdUser = userService.createUser(user);
			reponse.setValue(createdUser);
		} catch (Exception e) {
			reponse.setResultCode(Constants.FAIL_CODE);
			reponse.setMessage(e.getMessage());
		}
    	
    	return reponse;
        
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<User> updateUser(@RequestBody UserData user) {
    	CommonResponse<User> reponse = new CommonResponse<User>();
    	
    	User updated;
    	try {
    		updated = userService.updateUser(user);
    		
    		if(updated == null) {
    			reponse.setResultCode(Constants.FAIL_CODE);
    			reponse.setMessage("User not found");
    			return reponse;
    		}
    		
			reponse.setValue(updated);
		} catch (Exception e) {
			reponse.setResultCode(Constants.FAIL_CODE);
			reponse.setMessage(e.getMessage());
		}
    	
    	return reponse;
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_CUSTOMER')")
    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<User> getUser(@PathVariable Long id) {
    	CommonResponse<User> reponse = new CommonResponse<User>();
    	
    	try {
    		User user = userService.getUser(id);
    		
    		if(user == null) {
    			reponse.setResultCode(Constants.FAIL_CODE);
    			reponse.setMessage("User not found");
    			return reponse;
    		}
    		
			reponse.setValue(user);
		} catch (Exception e) {
			reponse.setResultCode(Constants.FAIL_CODE);
			reponse.setMessage(e.getMessage());
		}
    	
    	return reponse;
        
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_CUSTOMER')")
    @RequestMapping(method = RequestMethod.GET, value = "/user/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<User> getUserByUsername(@PathVariable String username) {
    	CommonResponse<User> reponse = new CommonResponse<User>();
    	
    	try {
    		User user = userService.getUserByUserName(username);
    		
    		if(user == null) {
    			reponse.setResultCode(Constants.FAIL_CODE);
    			reponse.setMessage("User not found");
    			return reponse;
    		}
    		
			reponse.setValue(user);
		} catch (Exception e) {
			reponse.setResultCode(Constants.FAIL_CODE);
			reponse.setMessage(e.getMessage());
		}
    	
    	return reponse;
        
    }
    
    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<List<User>> getListUsers(HttpServletRequest request, Principal principal) {
    	CommonResponse<List<User>> reponse = new CommonResponse<List<User>>();
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if(request.getUserPrincipal() != null) {
    		String user = request.getUserPrincipal().getName();
        	logger.info("Current usename: {}", user);
        	logger.info("currrent user: {}", principal);
        	logger.info("current roles: {}", auth.getAuthorities());
    	} else {
    		logger.info("Unknow user");
    	}
    	
    	try {
    		List<User> users = userService.getListUsers();
    		
    		if(users == null || users.isEmpty()) {
    			reponse.setResultCode(Constants.FAIL_CODE);
    			reponse.setMessage("No user found");
    			return reponse;
    		}
    		
			reponse.setValue(users);
		} catch (Exception e) {
			reponse.setResultCode(Constants.FAIL_CODE);
			reponse.setMessage(e.getMessage());
		}
    	
    	return reponse;
        
    }
}
