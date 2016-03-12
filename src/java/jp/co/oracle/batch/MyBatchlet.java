/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.oracle.batch;

import java.io.File;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
/**
 *
 * @author tnobe
 */
@Dependent
@Named("MyBatchlet")
public class MyBatchlet implements javax.batch.api.Batchlet{

    @Inject
    private JobContext jobCtx;
    
    @Override
    public String process() throws Exception {
         String fileName = jobCtx.getProperties()
                .getProperty("output_file");
        System.out.println("FILE LENGTH:" + (new File(fileName)).length());
        return "COMPLETED";
    }

    @Override
    public void stop() throws Exception {
        // Nothing
    }
    
}
