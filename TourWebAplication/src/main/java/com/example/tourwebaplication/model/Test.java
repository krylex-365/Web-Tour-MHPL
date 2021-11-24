package com.example.tourwebaplication.model;

import lombok.Data;

@Data
public class Test {
    int index;
    int x;

    public Test() {
        this.index = 2;
        this.x = 4;
    }
}
