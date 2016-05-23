package resources;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Strings;

public class MockUserHeaderFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String user = request.getHeader("user");
		if (!Strings.isNullOrEmpty(user)) {
			final UserDetails userDetails = userDetailsService.loadUserByUsername(user);
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails,
					userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authToken);

		}
		filterChain.doFilter(request, response);
	}
}
