package rs.raf.student.uniart.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Configuration {

    //region Properties

    @Component
    @Accessors(fluent = true)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Application {

        @Getter
        private static String name;
        @Getter
        private static String contextPath;
        @Getter
        private static Integer port;

        @Value("${spring.application.name}")
        private void setName(String value) {
            name = value;
        }

        @Value("${server.port}")
        private void setPort(Integer value) {
            port = value;
        }

        @Value("${server.servlet.context-path}")
        private void setContextPath(String value) {
            contextPath = value;
        }

    }

    @Component
    @Accessors(fluent = true)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Jwt {

        @Getter
        private static String secret;
        @Getter
        private static Integer expirationInHours;

        @Value("${uniart.jwt.secret}")
        private void setSecret(String value) {
            secret = value;
        }

        @Value("${uniart.jwt.expiration_time_hours}")
        private void setExpiration(Integer value) {
            expirationInHours = value;
        }

    }

    //endregion Properties

    //region Controller

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Controller {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Mapping {

            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            public static class Admin {

                public static final String ROOT     = "/admin";
                public static final String REGISTER = "/register";
                public static final String UPDATE   = "/update";
            }

            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            public static class Editor {

                public static final String ROOT   = "/editor";
                public static final String UPDATE = "/update";

            }

            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            public static class Manager {

                public static final String ROOT     = "/manager";
                public static final String REGISTER = "/register";
                public static final String UPDATE   = "/update";

            }

            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            public static class User {

                public static final String ROOT            = "/user";
                public static final String GET_ALL         = "/all";
                public static final String GET_BY_USERNAME = "/user_{username}";
                public static final String LOGIN           = "/login";

            }

        }

    }

    //endregion Controller

}
