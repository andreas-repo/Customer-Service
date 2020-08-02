package com.example.util;

import com.example.model.CustomerData;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class CustomerDataSerializer {

    private static final long serialVersionUID = 1L;

    @Inject
    Logger logger;

    public  CustomerDataSerializer() {
        super();
    }

    public void doSerialization(CustomerData customerData) throws IOException {
        OutputStream fileWriter = new FileOutputStream("C:\\Users\\andre\\Development\\Projekte\\customer-service\\CustomerData.data");
        ObjectOutputStream objectWriter = new ObjectOutputStream(fileWriter);
        objectWriter.writeObject(customerData);
        objectWriter.flush();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
