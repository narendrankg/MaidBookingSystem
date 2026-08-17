package org.example.maidbookingsystem.config;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfig {

    @Bean
    public Clock clock() {
        // Option A: Use the host machine's local time zone
        return Clock.systemDefaultZone(); 
        
        // Option B: Recommended for servers to avoid time zone conflicts
        // return Clock.systemUTC(); 
    }
}