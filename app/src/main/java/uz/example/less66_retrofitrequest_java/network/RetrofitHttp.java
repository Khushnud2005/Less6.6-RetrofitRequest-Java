package uz.example.less66_retrofitrequest_java.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uz.example.less66_retrofitrequest_java.network.service.EmployeeService;

public class RetrofitHttp {
public static boolean IS_TESTER = true;
    private static String  SERVER_DEVELOPMENT = "https://dummy.restapiexample.com/api/v1/";
    private static String SERVER_PRODUCTION = "https://dummy.restapiexample.com/api/v1/";
    static String server() {
        if (IS_TESTER)
            return SERVER_DEVELOPMENT;
        return SERVER_PRODUCTION;
    }
    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(server())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static EmployeeService employeeService = retrofit.create(EmployeeService.class);









}