package felipe.proj.ecombackend.seguranca.config;

import felipe.proj.ecombackend.seguranca.jwt.JwtAuth;
import felipe.proj.ecombackend.seguranca.user.EcomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.List;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class EcomConfig {
    private final EcomUserDetailsService userDetailsService;
    private final JwtAuth authEntrada;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
