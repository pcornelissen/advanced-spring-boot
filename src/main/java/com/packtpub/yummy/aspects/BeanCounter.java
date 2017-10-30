package com.packtpub.yummy.aspects;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class BeanCounter implements Serializable {
    int counter=0;
}
