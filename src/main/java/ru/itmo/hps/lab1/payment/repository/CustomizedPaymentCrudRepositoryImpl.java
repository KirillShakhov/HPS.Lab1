package ru.itmo.hps.lab1.payment.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itmo.hps.lab1.payment.entity.Payment;

import javax.sql.DataSource;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class CustomizedPaymentCrudRepositoryImpl implements PaymentRepository {
    private final JdbcTemplate jdbcTemplate;


    public List<Payment> findAll() {
        return jdbcTemplate.query("select * from payments", ROW_MAPPER);
    }


    public int save(Payment payment) {
        return jdbcTemplate.update("INSERT INTO payments (token, description, create_date) VALUES(?,?,?)",
                new Object[] { payment.getToken(), payment.getDescription(), payment.getCreateDate() });
    }

    @Override
    public int update(Payment payment) {
        return jdbcTemplate.update("UPDATE payments SET token=?, description=?, create_date=? WHERE id=?",
                new Object[] { payment.getToken(), payment.getDescription(), payment.getCreateDate(), payment.getId() });
    }

    public int removeById(Long id) {
        return jdbcTemplate.update("DELETE FROM payments WHERE id=?", id);
    }


    public Payment getById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM payments WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Payment.class), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public int deleteAll() {
        return jdbcTemplate.update("DELETE from payments");
    }
}
