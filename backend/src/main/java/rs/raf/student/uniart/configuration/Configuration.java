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

            private static final String GET_ALL = "/all";
            private static final String CREATE  = "/create";
            private static final String UPDATE  = "/update";

            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            public static class Admin {

                public static final String ROOT   = User.ROOT + "/admin";
                public static final String CREATE = Mapping.CREATE;
                public static final String UPDATE = Mapping.UPDATE;

            }

            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            public static class Character {

                public static final String ROOT               = "/characters";
                public static final String GET_ALL_BY_PROJECT = "/character/{organization}/{project}";
                public static final String GET_ONE            = "/character/{organization}/{project}/{name}";
                public static final String CREATE             = Mapping.CREATE;
                public static final String UPDATE             = Mapping.UPDATE;

            }

            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            public static class Editor {

                public static final String ROOT   = User.ROOT + "/editor";
                public static final String UPDATE = Mapping.UPDATE;

            }

            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            public static class Manager {

                public static final String ROOT   = User.ROOT + "/manager";
                public static final String CREATE = Mapping.CREATE;
                public static final String UPDATE = Mapping.UPDATE;

            }

            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            public static class Organization {

                public static final String ROOT        = "/organizations";
                public static final String GET_ALL     = Mapping.GET_ALL;
                public static final String GET_BY_NAME = "/organization/{name}";
                public static final String CREATE      = Mapping.CREATE;
                public static final String UPDATE      = Mapping.UPDATE;

            }

            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            public static class Project {

                public static final String ROOT                    = "/projects";
                public static final String GET_ALL                 = Mapping.GET_ALL;
                public static final String GET_ALL_BY_ORGANIZATION = "/organization/{organization}";
                public static final String GET_BY_NAME             = "/project/{organization}/{name}";
                public static final String CREATE                  = Mapping.CREATE;
                public static final String UPDATE                  = Mapping.UPDATE;

            }

            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            public static class User {

                public static final String ROOT            = "/users";
                public static final String GET_ALL         = Mapping.GET_ALL;
                public static final String GET_BY_USERNAME = "/user/{username}";
                public static final String LOGIN           = "/login";

            }

        }

    }

    //endregion Controller

}
