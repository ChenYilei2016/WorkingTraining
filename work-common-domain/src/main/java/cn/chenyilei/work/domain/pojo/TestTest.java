package cn.chenyilei.work.domain.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "test_test")
public class TestTest {
    @Id
    @Column(name = "test_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testId;

    @Column(name = "test_createtime")
    private Date testCreatetime;

    /**
     * @return test_id
     */
    public Integer getTestId() {
        return testId;
    }

    /**
     * @param testId
     */
    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    /**
     * @return test_createtime
     */
    public Date getTestCreatetime() {
        return testCreatetime;
    }

    /**
     * @param testCreatetime
     */
    public void setTestCreatetime(Date testCreatetime) {
        this.testCreatetime = testCreatetime;
    }
}