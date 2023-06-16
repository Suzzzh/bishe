package com.ruoyi.bishe.domain;

import java.math.BigDecimal;

public class Zhiliang {
    private String term;
    private String courseCode;
    private String date;
    private String teacherName;
    private String courseName;
    private Integer courseId;
    private Integer totalNum;
    private Integer realityNum;
    private Integer delayNum;
    private Integer missNum;
    private Integer cancelNum;
    private BigDecimal midtermSco;
    private BigDecimal homeworkSco;
    private BigDecimal quizSco;
    private BigDecimal largeSco;
    private BigDecimal experimentSco;
    private BigDecimal replySco;
    private BigDecimal thesisSco;
    private BigDecimal finalSco;
    private BigDecimal other;
    private BigDecimal highSco;
    private BigDecimal lowSco;
    private BigDecimal passRatio;

    private Integer ninNum1;
    private Integer eigNum1;
    private Integer sevNum1;
    private Integer sixNum1;
    private Integer lowNum1;
    private Integer ninNum2;
    private Integer eigNum2;
    private Integer sevNum2;
    private Integer sixNum2;
    private Integer lowNum2;
    private BigDecimal ninRatio1;
    private BigDecimal eigRatio1;
    private BigDecimal sevRatio1;
    private BigDecimal sixRatio1;
    private BigDecimal lowRatio1;
    private BigDecimal ninRatio2;
    private BigDecimal eigRatio2;
    private BigDecimal sevRatio2;
    private BigDecimal sixRatio2;
    private BigDecimal lowRatio2;

    @Override
    public String toString() {
        return "Zhiliang{" +
                "term='" + term + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", date='" + date + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseId=" + courseId +
                ", totalNum=" + totalNum +
                ", realityNum=" + realityNum +
                ", delayNum=" + delayNum +
                ", missNum=" + missNum +
                ", cancelNum=" + cancelNum +
                ", midtermSco=" + midtermSco +
                ", homeworkSco=" + homeworkSco +
                ", quizSco=" + quizSco +
                ", largeSco=" + largeSco +
                ", experimentSco=" + experimentSco +
                ", replySco=" + replySco +
                ", thesisSco=" + thesisSco +
                ", finalSco=" + finalSco +
                ", other=" + other +
                ", highSco=" + highSco +
                ", lowSco=" + lowSco +
                ", passRatio=" + passRatio +
                ", ninNum1=" + ninNum1 +
                ", eigNum1=" + eigNum1 +
                ", sevNum1=" + sevNum1 +
                ", sixNum1=" + sixNum1 +
                ", lowNum1=" + lowNum1 +
                ", ninNum2=" + ninNum2 +
                ", eigNum2=" + eigNum2 +
                ", sevNum2=" + sevNum2 +
                ", sixNum2=" + sixNum2 +
                ", lowNum2=" + lowNum2 +
                ", ninRatio1=" + ninRatio1 +
                ", eigRatio1=" + eigRatio1 +
                ", sevRatio1=" + sevRatio1 +
                ", sixRatio1=" + sixRatio1 +
                ", lowRatio1=" + lowRatio1 +
                ", ninRatio2=" + ninRatio2 +
                ", eigRatio2=" + eigRatio2 +
                ", sevRatio2=" + sevRatio2 +
                ", sixRatio2=" + sixRatio2 +
                ", lowRatio2=" + lowRatio2 +
                '}';
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getRealityNum() {
        return realityNum;
    }

    public void setRealityNum(Integer realityNum) {
        this.realityNum = realityNum;
    }

    public Integer getDelayNum() {
        return delayNum;
    }

    public void setDelayNum(Integer delayNum) {
        this.delayNum = delayNum;
    }

    public Integer getMissNum() {
        return missNum;
    }

    public void setMissNum(Integer missNum) {
        this.missNum = missNum;
    }

    public Integer getCancelNum() {
        return cancelNum;
    }

    public void setCancelNum(Integer cancelNum) {
        this.cancelNum = cancelNum;
    }

    public BigDecimal getMidtermSco() {
        return midtermSco;
    }

    public void setMidtermSco(BigDecimal midtermSco) {
        this.midtermSco = midtermSco;
    }

    public BigDecimal getHomeworkSco() {
        return homeworkSco;
    }

    public void setHomeworkSco(BigDecimal homeworkSco) {
        this.homeworkSco = homeworkSco;
    }

    public BigDecimal getQuizSco() {
        return quizSco;
    }

    public void setQuizSco(BigDecimal quizSco) {
        this.quizSco = quizSco;
    }

    public BigDecimal getLargeSco() {
        return largeSco;
    }

    public void setLargeSco(BigDecimal largeSco) {
        this.largeSco = largeSco;
    }

    public BigDecimal getExperimentSco() {
        return experimentSco;
    }

    public void setExperimentSco(BigDecimal experimentSco) {
        this.experimentSco = experimentSco;
    }

    public BigDecimal getReplySco() {
        return replySco;
    }

    public void setReplySco(BigDecimal replySco) {
        this.replySco = replySco;
    }

    public BigDecimal getThesisSco() {
        return thesisSco;
    }

    public void setThesisSco(BigDecimal thesisSco) {
        this.thesisSco = thesisSco;
    }

    public BigDecimal getFinalSco() {
        return finalSco;
    }

    public void setFinalSco(BigDecimal finalSco) {
        this.finalSco = finalSco;
    }

    public BigDecimal getOther() {
        return other;
    }

    public void setOther(BigDecimal other) {
        this.other = other;
    }

    public BigDecimal getHighSco() {
        return highSco;
    }

    public void setHighSco(BigDecimal highSco) {
        this.highSco = highSco;
    }

    public BigDecimal getLowSco() {
        return lowSco;
    }

    public void setLowSco(BigDecimal lowSco) {
        this.lowSco = lowSco;
    }

    public BigDecimal getPassRatio() {
        return passRatio;
    }

    public void setPassRatio(BigDecimal passRatio) {
        this.passRatio = passRatio;
    }

    public Integer getNinNum1() {
        return ninNum1;
    }

    public void setNinNum1(Integer ninNum1) {
        this.ninNum1 = ninNum1;
    }

    public Integer getEigNum1() {
        return eigNum1;
    }

    public void setEigNum1(Integer eigNum1) {
        this.eigNum1 = eigNum1;
    }

    public Integer getSevNum1() {
        return sevNum1;
    }

    public void setSevNum1(Integer sevNum1) {
        this.sevNum1 = sevNum1;
    }

    public Integer getSixNum1() {
        return sixNum1;
    }

    public void setSixNum1(Integer sixNum1) {
        this.sixNum1 = sixNum1;
    }

    public Integer getLowNum1() {
        return lowNum1;
    }

    public void setLowNum1(Integer lowNum1) {
        this.lowNum1 = lowNum1;
    }

    public Integer getNinNum2() {
        return ninNum2;
    }

    public void setNinNum2(Integer ninNum2) {
        this.ninNum2 = ninNum2;
    }

    public Integer getEigNum2() {
        return eigNum2;
    }

    public void setEigNum2(Integer eigNum2) {
        this.eigNum2 = eigNum2;
    }

    public Integer getSevNum2() {
        return sevNum2;
    }

    public void setSevNum2(Integer sevNum2) {
        this.sevNum2 = sevNum2;
    }

    public Integer getSixNum2() {
        return sixNum2;
    }

    public void setSixNum2(Integer sixNum2) {
        this.sixNum2 = sixNum2;
    }

    public Integer getLowNum2() {
        return lowNum2;
    }

    public void setLowNum2(Integer lowNum2) {
        this.lowNum2 = lowNum2;
    }

    public BigDecimal getNinRatio1() {
        return ninRatio1;
    }

    public void setNinRatio1(BigDecimal ninRatio1) {
        this.ninRatio1 = ninRatio1;
    }

    public BigDecimal getEigRatio1() {
        return eigRatio1;
    }

    public void setEigRatio1(BigDecimal eigRatio1) {
        this.eigRatio1 = eigRatio1;
    }

    public BigDecimal getSevRatio1() {
        return sevRatio1;
    }

    public void setSevRatio1(BigDecimal sevRatio1) {
        this.sevRatio1 = sevRatio1;
    }

    public BigDecimal getSixRatio1() {
        return sixRatio1;
    }

    public void setSixRatio1(BigDecimal sixRatio1) {
        this.sixRatio1 = sixRatio1;
    }

    public BigDecimal getLowRatio1() {
        return lowRatio1;
    }

    public void setLowRatio1(BigDecimal lowRatio1) {
        this.lowRatio1 = lowRatio1;
    }

    public BigDecimal getNinRatio2() {
        return ninRatio2;
    }

    public void setNinRatio2(BigDecimal ninRatio2) {
        this.ninRatio2 = ninRatio2;
    }

    public BigDecimal getEigRatio2() {
        return eigRatio2;
    }

    public void setEigRatio2(BigDecimal eigRatio2) {
        this.eigRatio2 = eigRatio2;
    }

    public BigDecimal getSevRatio2() {
        return sevRatio2;
    }

    public void setSevRatio2(BigDecimal sevRatio2) {
        this.sevRatio2 = sevRatio2;
    }

    public BigDecimal getSixRatio2() {
        return sixRatio2;
    }

    public void setSixRatio2(BigDecimal sixRatio2) {
        this.sixRatio2 = sixRatio2;
    }

    public BigDecimal getLowRatio2() {
        return lowRatio2;
    }

    public void setLowRatio2(BigDecimal lowRatio2) {
        this.lowRatio2 = lowRatio2;
    }
}
