package com.relationship.jpa.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/** Smoke test: proves Postman -> Spring -> MySQL works before any lab starts. */
@RestController
public class PingController {
    private final DataSource dataSource;
    public PingController(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @GetMapping("/api/ping")
    public Map<String, String> ping() throws SQLException {
        try (Connection c = dataSource.getConnection()) {
            return Map.of(
                    "status", "ok",
                    "database", c.getMetaData().getDatabaseProductName(),
                    "schema", String.valueOf(c.getCatalog()));
        }
    }
}