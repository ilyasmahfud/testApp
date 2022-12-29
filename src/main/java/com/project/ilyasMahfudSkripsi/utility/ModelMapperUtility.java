package com.project.ilyasMahfudSkripsi.utility;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtility {
    public ModelMapper modelMapperUtility() {
        return new ModelMapper();
    }
}
