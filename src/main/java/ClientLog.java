import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class ClientLog {
    protected List<Integer> productNumber = new ArrayList<>();
    protected List<Integer> productAmount = new ArrayList<>();

    public void log(int productNum, int amount) {
        productNumber.add(productNum + 1);
        productAmount.add(amount);

    }

    public void exportAsCSV(File csvFile) {
        try (FileWriter fileWriter = new FileWriter(csvFile)) {
            StringJoiner stringJoiner = new StringJoiner("");
            fileWriter.append("productNum" + "," + "amount"+"\n");
            for (int i = 0; i < productNumber.size(); i++) {
                stringJoiner
                        .add(productNumber.get(i) + "," + productAmount.get(i))
                        .add("\n");
            }
            fileWriter.append(stringJoiner.toString());
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

