package database;

import dataTypes.DataBlob;

/**
 * Abstract class for DataReader
 * 
 * @author Brennan Cain
 */
public abstract class DataReader {
    /**
     * Intentionally empty constructor
     */
    public DataReader() {
    }

    /**
     * 
     * @return DataBlob what is read from the files
     */
    public abstract DataBlob read();
}
