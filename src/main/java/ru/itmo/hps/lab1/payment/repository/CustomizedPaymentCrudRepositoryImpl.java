package ru.itmo.hps.lab1.payment.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itmo.hps.lab1.payment.entity.Payment;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class CustomizedPaymentCrudRepositoryImpl implements PaymentRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Payment> findAll() {
        return jdbcTemplate.query("select * from payment", ROW_MAPPER);
    }


    public Payment save(Payment payment) {
        return null;
    }

    public int removeById(Long id) {

        return 0;
    }

    public Payment getById(Long id) {

        return null;
    }
}
