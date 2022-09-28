package com.bbva.ccol.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

public class VerifyFile implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(VerifyFile.class);
    Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        LOGGER.info("CONTENT LENGTH: {}",resource.contentLength());
        int con =0;
        if(resource.contentLength()>0)
            con++;
            LOGGER.info("Archivo existe: {}",resource.exists());
        if(resource.exists())
            con++;
        return RepeatStatus.FINISHED;
    }
}
