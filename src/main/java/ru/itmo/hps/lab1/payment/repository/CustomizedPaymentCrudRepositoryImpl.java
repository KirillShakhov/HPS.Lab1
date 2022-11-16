package ru.itmo.hps.lab1.payment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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


    public int save(Payment payment) {
        return jdbcTemplate.update("INSERT INTO payments (id, token, description, createDate) VALUES(?,?,?,?)",
                new Object[] { payment.getId(), payment.getToken(), payment.getDescription(), payment.getCreateDate() });
    }

    public int removeById(Long id) {
        return jdbcTemplate.update("DELETE FROM payments WHERE id=?", id);
    }


    public Payment getById(Long id) {
        try {
            Payment Payment = jdbcTemplate.queryForObject("SELECT * FROM payments WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Payment.class), id);

            return Payment;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public int deleteAll() {
        return jdbcTemplate.update("DELETE from payments");
    }
}
