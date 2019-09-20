package cn.chenyilei.work.domain.pojo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "test_test")
public class TestTest {
    @Id
    @Column(name = "test_id")
    private Integer testId;

    @Column(name = "test_createtime")
    private Date testCreatetime;

    @Column(name = "test_is")
    private Boolean testIs;

    @Column(name = "test_double")
    private Double testDouble;

    @Column(name = "test_tinyi")
    private Byte testTinyi;

    @Column(name = "test_number")
    private Long testNumber;

    @Column(name = "test_deci")
    private BigDecimal testDeci;

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

    /**
     * @return test_is
     */
    public Boolean getTestIs() {
        return testIs;
    }

    /**
     * @param testIs
     */
    public void setTestIs(Boolean testIs) {
        this.testIs = testIs;
    }

    /**
     * @return test_double
     */
    public Double getTestDouble() {
        return testDouble;
    }

    /**
     * @param testDouble
     */
    public void setTestDouble(Double testDouble) {
        this.testDouble = testDouble;
    }

    /**
     * @return test_tinyi
     */
    public Byte getTestTinyi() {
        return testTinyi;
    }

    /**
     * @param testTinyi
     */
    public void setTestTinyi(Byte testTinyi) {
        this.testTinyi = testTinyi;
    }

    /**
     * @return test_number
     */
    public Long getTestNumber() {
        return testNumber;
    }

    /**
     * @param testNumber
     */
    public void setTestNumber(Long testNumber) {
        this.testNumber = testNumber;
    }

    /**
     * @return test_deci
     */
    public BigDecimal getTestDeci() {
        return testDeci;
    }

    /**
     * @param testDeci
     */
    public void setTestDeci(BigDecimal testDeci) {
        this.testDeci = testDeci;
    }
}