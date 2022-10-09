package uz.example.less66_retrofitrequest_java.model;

import com.google.gson.annotations.SerializedName;

public class EmplResp {

    @SerializedName("id")
    int id;
    @SerializedName("employee_name")
    String employee_name;
    @SerializedName("employee_salary")
    int employee_salary;
    @SerializedName("employee_age")
    int employee_age;

    @Override
    public String toString() {
        return "EmplResp{" +
                "id=" + id +
                ", name='" + employee_name + '\'' +
                ", salary=" + employee_salary +
                ", age=" + employee_age +
                '}';
    }
}
