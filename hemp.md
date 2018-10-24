	<sec:csrfInput /> in jsp for post needed

	Custom login page
	
<mvc:view-controller path="/login" view-name="login"/>

	//jsp page should be login.jsp only
		http.formLogin()
				.loginPage("/login").usernameParameter("staticEmail2")
				.passwordParameter("inputPassword2")
				.permitAll();
				
The line loginPage("/login") instructs Spring Security
when authentication is required, redirect the browser to /login
we are in charge of rendering the login page when /login is requested
when authentication attempt fails, redirect the browser to /login?error (since we have not specified otherwise)
we are in charge of rendering a failure page when /login?error is requested
when we successfully logout, redirect the browser to /login?logout (since we have not specified otherwise)
we are in charge of rendering a logout confirmation page when /login?logout is requested
The method formLogin().permitAll() statement instructs Spring Security to allow any access to any URL (i.e. /login and /login?error) associated to formLogin().

For jsp:

The URL we submit our username and password to is the same URL as our login form (i.e. /login), but a POST instead of a GET.
When authentication fails, the browser is redirected to /login?error so we can display an error message by detecting if the parameter error is non-null.
When we are successfully logged out, the browser is redirected to /login?logout so we can display an logout success message by detecting if the parameter logout is non-null.
The username should be present on the HTTP parameter username
The password should be present on the HTTP parameter password


http
            .authorizeRequests()
                .antMatchers("/resources/**").permitAll() 
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()                                    
                .permitAll();
    }

    // ...
}
This allows anyone to access a URL that begins with /resources/. Since this is where our css, javascript, and images are stored all our static resources are viewable by anyone.
As you might expect, logout().permitAll() allows any user to request logout and view logout success URL.

If login successful, redirects to the page which we were trying to access in url


csrf: cross site request forgery
If i am logged in, my session is active, some malicious program may do some transaction in my session. So we pass a hidden variable with each request and validated at server level. So malicious request cannot send this token which is getting validated.

To enable csrf:
1) Bydefault is enabled in spring security
2) http.csrf.disable(): to disable it
3) If using spring framework tag: and using @EnableWebSecurity, automatically generates csrf token
4) Else in jsp need to provide it as: If not using spring form
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
5)<sec:csrfinput> : if not using spring form or hidden field 
