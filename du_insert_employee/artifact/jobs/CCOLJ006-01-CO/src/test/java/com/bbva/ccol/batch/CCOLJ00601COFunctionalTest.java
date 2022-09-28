package com.bbva.ccol.batch;

import java.io.File;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test for batch process CCOLJ006-01-CO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring/batch/beans/CCOLJ006-01-CO-beans.xml","classpath:/META-INF/spring/batch/jobs/jobs-CCOLJ006-01-CO-context.xml","classpath:/META-INF/spring/jobs-CCOLJ006-01-CO-runner-context.xml"})
public class CCOLJ00601COFunctionalTest{

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

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;


	@Test
	public void testLaunchJob() throws Exception {
		//TODO implements job launch test
		//Without parameters (use this implementation if job not need parameters)
//		final JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		//With parameters (use this implementation if job needs parameters comment first implementation) 
		/*********************** Parameters Definition ***********************/
		//First parameter
		final JobParameter jobParameter = new JobParameter("20220802");
		//Add parameters to job
		final HashMap<String, JobParameter> parameters = new HashMap<String, JobParameter>();
		parameters.put("oDate", jobParameter);
		final JobParameters jobParameters = new JobParameters(parameters);
//		Mockito.when(jobLauncherTestUtils.launchJob(jobParameters)).thenReturn();
		final JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

		//TODO implements job launch test Assert's
	Assert.assertTrue(jobExecution.getExitStatus().equals(ExitStatus.COMPLETED));
	}
}
