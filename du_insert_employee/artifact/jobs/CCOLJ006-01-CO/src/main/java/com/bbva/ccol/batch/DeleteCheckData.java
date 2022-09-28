package com.bbva.ccol.batch;

import com.bbva.ccol.lib.r006.CCOLR006;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DeleteCheckData implements Tasklet {

    private CCOLR006 ccolR006;

    public void setCcolR006(CCOLR006 ccolR006) {
        this.ccolR006 = ccolR006;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCheckData.class);


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        int rowsDeleted = ccolR006.executeDeleteCheckData();
        LOGGER.info("Rows Deleted: {}", rowsDeleted);
        return RepeatStatus.FINISHED;
    }
}
