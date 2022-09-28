package com.bbva.ccol.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.File;

public class DeleteFile implements Tasklet, InitializingBean {


    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteFile.class);

    Resource directory;


    File path;

    public Resource getDirectory() {
        return directory;
    }

    public void setDirectory(Resource directory) {
        this.directory = directory;
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        File dir = directory.getFile();
        Assert.state(dir.isDirectory());
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getPath().equals(path.getPath())) {
                LOGGER.info("Igual: {}", files[i].getPath());
                try {
                    files[i].delete();
                } catch (UnexpectedJobExecutionException u) {
                    LOGGER.info("Excepcion: {}", u);
                    return RepeatStatus.FINISHED;

                }

            }
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(directory,"directory nust be set");

    }
}
