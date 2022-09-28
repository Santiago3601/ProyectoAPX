package com.bbva.ccol.batch;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;


public class WriterCreate implements ItemWriter<List<StringBuilder>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriterCreate.class);

    @Override
    public void write(List<? extends List<StringBuilder>> list) throws Exception {
        int cont = 0;
        for (List<StringBuilder> lis : list) {
            for (StringBuilder str : lis) {
                cont++;
                try {
                    String[] listString = StringUtils.splitPreserveAllTokens(String.valueOf(str), ",");
                    String ruta = "/fichtemcomp/datsal/CAPS_D02_#{jobParameters['department']}_" + listString[1] + ".csv";
                    String contenido = str.toString();
                    LOGGER.info("STRINGBUILDER : {}", str.toString());
                    File file = new File(ruta);
                    // Si el archivo no existe es creado
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(contenido);
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
