package database;
import dataTypes.DataBlob;

public abstract class DataReader {
    public DataReader() {}

    public abstract DataBlob read();
}
