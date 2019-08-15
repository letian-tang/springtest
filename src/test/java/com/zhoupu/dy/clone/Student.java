package com.zhoupu.dy.clone;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student implements Serializable {
    private Long id;
    private String name;
}
