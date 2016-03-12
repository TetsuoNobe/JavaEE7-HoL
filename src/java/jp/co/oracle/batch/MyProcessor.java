/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.oracle.batch;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author tnobe
 */
@Dependent
@Named("MyProcessor")
public class MyProcessor implements javax.batch.api.chunk.ItemProcessor {
    
    public MyProcessor() {
    }

    @Override
    public Object processItem(Object item) throws Exception {
          String line = (String) item;
          return line.toUpperCase();
    }

}
