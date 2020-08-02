package com.example.util;

import com.example.model.CustomerData;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.io.*;

public class CustomerDataDeserializer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    Logger logger;

    public CustomerDataDeserializer() {
        super();
    }

    public CustomerData doDeserialization(CustomerData customerData) throws IOException, ClassNotFoundException {

        InputStream fileReader = new FileInputStream("C:\\Users\\andre\\Development\\Projekte\\customer-service\\CustomerData.data");
        ObjectInputStream objectReader = new ObjectInputStream(fileReader);
        customerData = (CustomerData) objectReader.readObject();
        return customerData;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
