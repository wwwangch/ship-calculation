package com.iscas.common.aspose.tools;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

class CadUtilsTest {
    @Test
    public void cadToPdf() throws IOException {
        InputStream is = Files.newInputStream(Paths.get(new File("D:\\ideaProjects\\file-preview\\server\\src\\main\\resources\\static\\商场设计全套图.dwg").toURI()));
        try (OutputStream os = Files.newOutputStream(Paths.get(new File("d:/tmp/test.pdf").toURI()))) {
            CadUtils.cadToPdf(is, os);
        }
    }
}