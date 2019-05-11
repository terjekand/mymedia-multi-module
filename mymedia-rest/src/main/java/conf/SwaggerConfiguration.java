package conf;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class SwaggerConfiguration extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setHost("localhost:8080");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath("/docs");
        beanConfig.setTitle("Mym Swagger Documentation");
        beanConfig.setResourcePackage("conf");
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);
    }
}
