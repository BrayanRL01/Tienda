package com.Tienda.Tienda.Model;

import java.io.ByteArrayInputStream;

public class ReportePersonaDTO {

    private String FileName;
    private ByteArrayInputStream Stream;
    private int Length;

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public ByteArrayInputStream getStream() {
        return Stream;
    }

    public void setStream(ByteArrayInputStream Stream) {
        this.Stream = Stream;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int Length) {
        this.Length = Length;
    }

}
