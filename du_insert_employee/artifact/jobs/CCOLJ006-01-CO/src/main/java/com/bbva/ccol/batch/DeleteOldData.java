package com.bbva.ccol.batch;

import com.bbva.ccol.lib.r006.CCOLR006;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DeleteOldData implements Tasklet {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteOldData.class);
    private CCOLR006 ccolR006;

    public void setCcolR006(CCOLR006 ccolR006) {
        this.ccolR006 = ccolR006;
    }
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        int rowsDeleted = ccolR006.executeDeleteOldData();
        LOGGER.info("Rows Deleted: {}", rowsDeleted);
        return RepeatStatus.FINISHED;
    }
}
