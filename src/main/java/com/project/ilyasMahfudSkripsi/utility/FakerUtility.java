package com.project.ilyasMahfudSkripsi.utility;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class FakerUtility {
    public Faker initialize() {
        return new Faker();
    }
}
