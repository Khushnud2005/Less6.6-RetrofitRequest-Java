package uz.example.less66_retrofitrequest_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import uz.example.less66_retrofitrequest_java.model.BaseResponse;
import uz.example.less66_retrofitrequest_java.model.EmplResp;
import uz.example.less66_retrofitrequest_java.model.Employee;
import uz.example.less66_retrofitrequest_java.network.RetrofitHttp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView tv_res, tv_message;
    EditText et_get_id,et_delete_id;
    EditText et_create_name,et_create_salary,et_create_age;
    EditText et_update_id,et_update_name,et_update_salary,et_update_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    void initViews(){
        Button btn_list = findViewById(R.id.btn_list);
        Button btn_get_one = findViewById(R.id.btn_get_one);
        Button btn_create = findViewById(R.id.btn_create);
        Button btn_update = findViewById(R.id.btn_update);
        Button btn_delete = findViewById(R.id.btn_delete);
        et_get_id = findViewById(R.id.et_get_id);
        et_create_name = findViewById(R.id.et_create_name);
        et_create_salary = findViewById(R.id.et_create_salary);
        et_create_age = findViewById(R.id.et_create_age);
        et_update_name = findViewById(R.id.et_update_name);
        et_update_salary = findViewById(R.id.et_update_salary);
        et_update_age = findViewById(R.id.et_update_age);
        et_update_id = findViewById(R.id.et_update_id);
        et_delete_id = findViewById(R.id.et_delete_id);
        tv_res = findViewById(R.id.tv_res);
        tv_message = findViewById(R.id.tv_message);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmployeeList();
            }
        });
        btn_get_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et_get_id.getText().toString().trim();
                if (!id.isEmpty()){
                    getOne(Integer.parseInt(id));
                }

            }
        });
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_create_name.getText().toString().trim();
                String salary= et_create_salary.getText().toString().trim();
                String age = et_create_age.getText().toString().trim();

                if (!salary.isEmpty()&&!age.isEmpty()){
                    Employee emp = new  Employee(name,Integer.parseInt(salary),Integer.parseInt(age));
                    createEmployee(emp);
                }

            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = et_update_id.getText().toString().trim();
                String name = et_update_name.getText().toString().trim();
                String salary= et_update_salary.getText().toString().trim();
                String age = et_update_age.getText().toString().trim();

                if (!id.isEmpty()&&!salary.isEmpty()&&!age.isEmpty()){
                    Employee emp = new Employee(Integer.parseInt(id),name,Integer.parseInt(salary),Integer.parseInt(age));
                    updateEmployee(emp);
                }

            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et_delete_id.getText().toString().trim() ;
                if (!id.isEmpty()){
                    deleteEmployee(Integer.valueOf(id));
                }

            }
        });
    }
    void getEmployeeList(){
        RetrofitHttp.employeeService.listEmployee().enqueue(new Callback<BaseResponse<ArrayList<EmplResp>>>() {
            @Override
            public void onResponse(Call<BaseResponse<ArrayList<EmplResp>>> call, Response<BaseResponse<ArrayList<EmplResp>>> response) {

                if (response.isSuccessful()) {
                    tv_res.setText(response.body().getData().toString());
                    tv_message.setText(response.body().getMessage());
                }else{
                    tv_message.setText("Result");
                    tv_res.setText("Javob kelmadi. Boshqattan bosing!!!");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ArrayList<EmplResp>>> call, Throwable t) {

                tv_res.setText(t.getMessage());
            }
        });
        
    }
    void getOne(int id){
        RetrofitHttp.employeeService.singleEmployee(id).enqueue(new Callback<BaseResponse<EmplResp>>() {
            @Override
            public void onResponse(
                    Call<BaseResponse<EmplResp>> call,
                    Response<BaseResponse<EmplResp>> response
            ) {
                if (response.isSuccessful()) {
                    if (response.body().getData()!=null){
                        tv_res.setText(response.body().getData().toString());
                    }else{
                        tv_res.setText("null : To View Fully Response Input ID Less then 25");
                    }

                    tv_message.setText(response.body().getMessage());
                    et_get_id.setText("");
                }else{
                    tv_message.setText("Result");
                    tv_res.setText("Javob kelmadi. Boshqattan bosing!!!");
                }

            }
            @Override
            public void onFailure(@NonNull Call<BaseResponse<EmplResp>> call, @NonNull Throwable t) {
                tv_res.setText(t.getMessage());
            }
        });
    }
    void createEmployee(Employee employee ){
        RetrofitHttp.employeeService.createEmployee(employee).enqueue(new Callback<BaseResponse<EmplResp>>() {
            @Override
            public void onResponse(Call<BaseResponse<EmplResp>> call , Response<BaseResponse<EmplResp>> response ) {

                if (response.isSuccessful()){
                    tv_res.setText(response.body().getData().toString());
                    tv_message.setText(response.body().getMessage());
                    et_create_name.setText("");
                    et_create_salary.setText("");
                    et_create_age.setText("");
                }else{
                    tv_message.setText("Result");
                    tv_res.setText("Javob kelmadi. Boshqattan bosing!!!");
                }

            }
            @Override
            public void onFailure(Call<BaseResponse<EmplResp>> call,Throwable t ) {

                tv_res.setText(t.getMessage().toString());
            }
        });
    }
    void updateEmployee(Employee employee ){
        RetrofitHttp.employeeService.updateEmployee(employee.getId(),employee).enqueue(new Callback<BaseResponse<EmplResp>>() {
            @Override
            public void onResponse(Call<BaseResponse<EmplResp>> call,Response<BaseResponse<EmplResp>> response ) {
                if (response.isSuccessful()){
                    tv_res.setText(response.body().getData().toString());
                    tv_message.setText(response.body().getMessage());
                    et_update_name.setText("");
                    et_update_salary.setText("");
                    et_update_age.setText("");
                    et_update_id.setText("");
                }else{
                    tv_message.setText("Result");
                    tv_res.setText("Javob kelmadi. Boshqattan bosing!!!");
                }

            }
            @Override
            public void onFailure(Call<BaseResponse<EmplResp>> call ,Throwable t ) {

                tv_res.setText(t.getMessage().toString());
            }
        });
    }
    void deleteEmployee(int id){
        RetrofitHttp.employeeService.deleteEmployee(id).enqueue(new Callback<BaseResponse<String>>() {
            @Override
             public void onResponse(Call<BaseResponse<String>>call,Response<BaseResponse<String>> response ) {
                if (response.isSuccessful()){
                    tv_res.setText( response.body().getData().toString());
                    tv_message.setText(response.body().getMessage());
                    et_delete_id.setText("");
                }else{
                    tv_message.setText("Result");
                    tv_res.setText("Javob kelmadi. Boshqattan bosing!!!");
                }

            }
            @Override
            public void onFailure(Call<BaseResponse<String>>call ,Throwable t ) {

                tv_res.setText(t.getMessage().toString());
            }
        });
    }
}