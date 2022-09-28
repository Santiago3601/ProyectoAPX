package com.bbva.ccol.batch;

import org.springframework.batch.item.file.ResourceSuffixCreator;

public class CustomerOutputFileSuffixCreator implements ResourceSuffixCreator {
    @Override
    public String getSuffix(int i) {
        return i + ".csv";
    }
}
