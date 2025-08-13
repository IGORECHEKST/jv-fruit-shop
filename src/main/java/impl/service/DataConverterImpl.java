package impl.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import model.FruitTransaction;
import service.DataConverter;

public class DataConverterImpl implements DataConverter {
    @Override
    public List<FruitTransaction> convert(List<String> lines) {
        if (lines == null) {
            throw new RuntimeException("Input lines cannot be null");
        }
        return lines.stream()
                .filter(Objects::nonNull)
                .map(this::parseLine)
                .collect(Collectors.toList());
    }

    private FruitTransaction parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            throw new RuntimeException("Invalid CSV line format: " + line);
        }
        try {
            FruitTransaction.Operation operation = FruitTransaction.Operation.getByCode(parts[0]);
            String fruit = parts[1];
            int quantity = Integer.parseInt(parts[2]);
            if (quantity < 0) {
                throw new RuntimeException("Quantity cannot be negative: " + quantity);
            }
            return new FruitTransaction(operation, fruit, quantity);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid quantity format in line: " + line, e);
        }
    }
}
