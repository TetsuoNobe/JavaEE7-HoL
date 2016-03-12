/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.oracle.batch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author tnobe
 */
@Dependent
@Named("MyReader")
public class MyReader  implements javax.batch.api.chunk.ItemReader {
    
    private MyCheckpoint mycheckpoint;
    private BufferedReader breader;
    @Inject
    JobContext jobCtx;

    public MyReader() {
    }
    
    @Override
    public void open(Serializable checkpoint) throws Exception {
        if (checkpoint == null) {
            mycheckpoint = new MyCheckpoint();
        } else {
            mycheckpoint = (MyCheckpoint) checkpoint;
        }
        String fileName = jobCtx.getProperties()
                .getProperty("input_file");
        breader = new BufferedReader(new FileReader(fileName));
        for (long i = 0; i < mycheckpoint.getLineNum(); i++) {
            breader.readLine();
        }
    }

    @Override
    public void close() throws Exception {
         breader.close();
    }

    @Override
    public Object readItem() throws Exception {
        String line = breader.readLine();
        return line;
        
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return mycheckpoint;
    }
    
}
