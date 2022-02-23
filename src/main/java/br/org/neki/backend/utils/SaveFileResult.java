package br.org.neki.backend.utils;

public class SaveFileResult
{
    private String filename;
    private boolean error;

    public String getFilename() 
    {
        return filename;
    }

    public SaveFileResult setFilename(String filename) 
    {
        this.filename = filename;
        return this;
    }

    public boolean isError() 
    {
        return error;
    }

    public SaveFileResult setError(boolean error) 
    {
        this.error = error;
        return this;
    }
}
