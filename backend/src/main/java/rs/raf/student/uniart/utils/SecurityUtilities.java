package rs.raf.student.uniart.utils;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import rs.raf.student.uniart.configuration.Configuration;
import rs.raf.student.uniart.type.UserRoleType;

public class SecurityUtilities {

    private static final String MATCH_EXACT              = "";
    private static final String MATCH_DIRECTORY          = "/";
    private static final String MATCH_SUBDIRECTORY       = "/*";
    private static final String MATCH_ALL_SUBDIRECTORIES = "/**";

    public static class APIv1 {

        public static void authorizeHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizeHttp) {
            authorizeHttp.requestMatchers(Configuration.Controller.Mapping.User.ROOT + MATCH_ALL_SUBDIRECTORIES)
                         .permitAll();

            authorizeHttp.requestMatchers(Configuration.Controller.Mapping.Editor.ROOT + MATCH_ALL_SUBDIRECTORIES)
                         .hasRole(UserRoleType.EDITOR.getName());

            authorizeHttp.requestMatchers(Configuration.Controller.Mapping.Manager.ROOT + MATCH_ALL_SUBDIRECTORIES)
                         .hasRole(UserRoleType.MANAGER.getName());

            authorizeHttp.requestMatchers(Configuration.Controller.Mapping.Admin.ROOT + MATCH_ALL_SUBDIRECTORIES)
                         .hasRole(UserRoleType.ADMIN.getName());

            authorizeHttp.anyRequest().denyAll();
        }

    }

}
