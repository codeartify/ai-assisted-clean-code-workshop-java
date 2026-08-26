package com.workshop.architecture.architecture;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

class VerticalSliceBoundaryTest {

    private static final Path RECORD_PAYMENT_SLICE = Path.of(
            "src/main/java/com/workshop/architecture/fitness/payment/record"
    );

    @Test
    void recordPaymentSliceDoesNotDependOnAnotherPaymentSlice() throws IOException {
        List<String> forbiddenImports;
        try (var files = Files.walk(RECORD_PAYMENT_SLICE)) {
            forbiddenImports = files
                    .filter(path -> path.toString().endsWith(".java"))
                    .flatMap(this::lines)
                    .map(String::trim)
                    .filter(line -> line.startsWith("import com.workshop.architecture.fitness.payment."))
                    .filter(line -> !line.startsWith(
                            "import com.workshop.architecture.fitness.payment.record."
                    ))
                    .toList();
        }

        assertThat(forbiddenImports)
                .as("vertical slices may use explicit shared code but not another slice")
                .isEmpty();
    }

    private java.util.stream.Stream<String> lines(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException exception) {
            throw new IllegalStateException("Could not inspect " + path, exception);
        }
    }
}
