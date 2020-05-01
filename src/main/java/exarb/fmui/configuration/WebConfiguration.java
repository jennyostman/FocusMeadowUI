package exarb.fmui.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Enables Cross-Origin Resource Sharing (CORS)
     * More info: http://docs.spring.io/spring/docs/current/spring-framework-reference/html/cors.html
     * @param registry
     */
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**");
    }

    // TODO: Note that, for simplicity, we’re enabling CORS for every origin
    //  (we didn’t specify any restriction) and for every mapping (with the pattern /**).
    //  When your system is mature and your infrastructure is set up, you may want to be
    //  stricter here by passing some property values to your applications to allow only
    //  some specific domains as origins. For extended details on how to fine-tune your
    //  configuration, read the official Spring documentation at https://tpd.io/spr-cors.

}